/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.timeseries;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.RequestExecutor;
import com.crazzyghost.alphavantage.ResponseDispatcher;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Entitlement;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.request.*;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.RealtimeBulkQuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import java.util.Map;

/**
 * Access to Stock Time Series Data.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public final class TimeSeries implements Fetcher {

    private final Config config;
    private TimeSeriesRequest.Builder<?> builder;
    private boolean adjusted = false;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public TimeSeries(Config config) {
        this.config = config;
    }

    /**
     * Accesses monthly stock time series data.
     *
     * @return a {@link MonthlyRequestProxy} instance
     */
    public MonthlyRequestProxy monthly() {
        this.adjusted = false;
        return new MonthlyRequestProxy();
    }

    /**
     * Accesses weekly stock time series data.
     *
     * @return a {@link WeeklyRequestProxy} instance
     */
    public WeeklyRequestProxy weekly() {
        this.adjusted = false;
        return new WeeklyRequestProxy();
    }

    /**
     * Accesses daily stock time series data.
     *
     * @return a {@link DailyRequestProxy} instance
     */
    public DailyRequestProxy daily() {
        this.adjusted = false;
        return new DailyRequestProxy();
    }

    /**
     * Accesses intraday stock time series data.
     *
     * @return an {@link IntraDayRequestProxy} instance
     */
    public IntraDayRequestProxy intraday() {
        return new IntraDayRequestProxy();
    }

    /**
     * Accesses global quote data.
     *
     * @return a {@link GlobalQuoteRequestProxy} instance
     */
    public GlobalQuoteRequestProxy quote() {
        return new GlobalQuoteRequestProxy();
    }

    /**
     * Accesses realtime bulk quote data.
     *
     * @return a {@link RealtimeBulkQuoteRequestProxy} instance
     */
    public RealtimeBulkQuoteRequestProxy realtimeBulkQuote() {
        return new RealtimeBulkQuoteRequestProxy();
    }

    /**
     * Fetches stock time series data asynchronously, dispatching the parsed response to the
     * callback registered on the request proxy.
     */
    @Override
    public void fetch() {
        RequestExecutor.fetchAsync(config, builder.build(), this::parseResponse, failureCallback);
    }

    /**
     * Makes a blocking synchronous http request to fetch the data. This is called by {@link
     * RequestProxy#fetchSync()}.
     *
     * <p>On Android this will throw {@code NetworkOnMainThreadException}. In that case the call
     * should be made on another thread.
     *
     * <p>Using this method will overwrite any async callback.
     *
     * @param successCallback internally used {@link SuccessCallback} that receives the parsed
     *     response
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.4.1
     */
    private void fetchSync(SuccessCallback<?> successCallback) throws AlphaVantageException {
        this.successCallback = successCallback;
        this.failureCallback = null;
        parseResponse(RequestExecutor.fetchSync(config, builder.build()));
    }

    /**
     * Parses a {@link TimeSeriesResponse} and dispatches it to the registered callback.
     *
     * @param data parsed JSON data
     */
    private void parseTimeSeriesResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(
                TimeSeriesResponse.of(data, adjusted), successCallback, failureCallback);
    }

    /**
     * Parses a {@link QuoteResponse} and dispatches it to the registered callback.
     *
     * @param data parsed JSON data
     */
    private void parseGlobalQuoteResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(QuoteResponse.of(data), successCallback, failureCallback);
    }

    /**
     * Parses a {@link RealtimeBulkQuoteResponse} and dispatches it to the registered callback.
     *
     * @param data parsed JSON data
     */
    private void parseRealtimeBulkQuoteResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(
                RealtimeBulkQuoteResponse.of(data), successCallback, failureCallback);
    }

    /**
     * Parses a JSON response into a {@link TimeSeriesResponse}, a {@link QuoteResponse} or a {@link
     * RealtimeBulkQuoteResponse}, depending on the function the request was built for.
     *
     * @param data parsed JSON response
     */
    private void parseResponse(Map<String, Object> data) {
        switch (builder.getFunction()) {
            case TIME_SERIES_DAILY:
            case TIME_SERIES_DAILY_ADJUSTED:
            case TIME_SERIES_MONTHLY:
            case TIME_SERIES_MONTHLY_ADJUSTED:
            case TIME_SERIES_WEEKLY:
            case TIME_SERIES_WEEKLY_ADJUSTED:
            case TIME_SERIES_INTRADAY:
                parseTimeSeriesResponse(data);
                break;
            case GLOBAL_QUOTE:
                parseGlobalQuoteResponse(data);
                break;
            case REALTIME_BULK_QUOTES:
                parseRealtimeBulkQuoteResponse(data);
                break;
            default:
                break;
        }
    }

    /**
     * An abstract proxy for building requests. Adds the functionality of adding callbacks and a
     * terminal method for fetching data.
     *
     * @param <T> a concrete {@link RequestProxy} implementation
     * @param <U> the response type returned during a synchronous call
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?, U>, U> {

        protected TimeSeriesRequest.Builder<?> builder;
        protected U syncResponse;

        private RequestProxy() {}

        /**
         * Sets the ticker symbol the request reports on.
         *
         * @param symbol the ticker symbol, for example {@code IBM}
         * @return this proxy, for method chaining
         */
        public T forSymbol(String symbol) {
            this.builder.forSymbol(symbol);
            return (T) this;
        }

        /**
         * Sets the format the API returns the series in.
         *
         * @param type the datatype {@link DataType}
         * @return this proxy, for method chaining
         */
        public T dataType(DataType type) {
            this.builder.dataType(type);
            return (T) this;
        }

        /**
         * Sets the data freshness tier for premium Alpha Vantage plans. Controls
         * whether the request fetches realtime or fifteen-minute-delayed data.
         * <p>
         * Requires a premium API key to have any effect. Free keys ignore or reject
         * this parameter. When unset, the parameter is omitted from the request
         * entirely.
         *
         * @param entitlement the freshness tier, {@link Entitlement#REALTIME} or
         *     {@link Entitlement#DELAYED}
         * @return this proxy, for method chaining
         * @since 1.9.0
         */
        public T entitlement(Entitlement entitlement) {
            this.builder.entitlement(entitlement);
            return (T) this;
        }

        /**
         * Sets the callback invoked with the parsed response when an async call succeeds.
         *
         * @param callback successful fetch handler
         * @return this proxy, for method chaining
         */
        public T onSuccess(SuccessCallback<?> callback) {
            TimeSeries.this.successCallback = callback;
            return (T) this;
        }

        /**
         * Sets the callback invoked with the cause when an async call fails.
         *
         * @param callback failed fetch handler
         * @return this proxy, for method chaining
         */
        public T onFailure(FailureCallback callback) {
            TimeSeries.this.failureCallback = callback;
            return (T) this;
        }

        /**
         * Sets the right builder and makes an async http request using {@link TimeSeries#fetch()}.
         */
        public void fetch() {
            TimeSeries.this.builder = this.builder;
            TimeSeries.this.fetch();
        }

        /**
         * Sets the response received during a synchronous call.
         *
         * @param response the parsed response to hand back to {@link #fetchSync()}
         */
        public void setSyncResponse(U response) {
            this.syncResponse = response;
        }

        /**
         * Sets the right builder and makes a synchronous request using {@link TimeSeries#fetch()}.
         *
         * <p>When calling this method, any async callbacks will be overwritten.
         *
         * @return the api response
         * @throws AlphaVantageException if the request fails or the response cannot be read
         */
        public U fetchSync() throws AlphaVantageException {
            SuccessCallback<U> callback = this::setSyncResponse;
            TimeSeries.this.builder = this.builder;
            TimeSeries.this.fetchSync(callback);
            return this.syncResponse;
        }
    }

    /** Proxy for building a {@link DailyRequest}. */
    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy, TimeSeriesResponse> {

        DailyRequestProxy() {
            super();
            this.builder = new DailyRequest.Builder();
        }

        /**
         * Sets how much history the request asks for.
         *
         * @param size the output size {@link OutputSize}
         * @return this proxy, for method chaining
         */
        public DailyRequestProxy outputSize(OutputSize size) {
            ((DailyRequest.Builder) this.builder).outputSize(size);
            return this;
        }

        /**
         * Switches the request to the split and dividend adjusted daily series.
         *
         * @return this proxy, for method chaining
         */
        public DailyRequestProxy adjusted() {
            TimeSeries.this.adjusted = true;
            ((DailyRequest.Builder) this.builder).adjusted();
            return this;
        }
    }

    /** Proxy for building an {@link IntraDayRequest}. */
    public class IntraDayRequestProxy
            extends RequestProxy<IntraDayRequestProxy, TimeSeriesResponse> {

        IntraDayRequestProxy() {
            super();
            this.builder = new IntraDayRequest.Builder();
        }

        /**
         * Sets how much history the request asks for.
         *
         * @param size the output size {@link OutputSize}
         * @return this proxy, for method chaining
         */
        public IntraDayRequestProxy outputSize(OutputSize size) {
            ((IntraDayRequest.Builder) this.builder).outputSize(size);
            return this;
        }

        /**
         * Sets the spacing between intraday observations.
         *
         * @param interval the interval {@link Interval}
         * @return this proxy, for method chaining
         */
        public IntraDayRequestProxy interval(Interval interval) {
            ((IntraDayRequest.Builder) this.builder).interval(interval);
            return this;
        }

        /**
         * Adjusts the timeseries output by historical split and dividend events.
         *
         * @return this proxy, for method chaining
         */
        public IntraDayRequestProxy adjusted() {
            ((IntraDayRequest.Builder) this.builder).adjusted();
            return this;
        }

        /**
         * Includes extended trading hours alongside regular ones.
         *
         * @return this proxy, for method chaining
         */
        public IntraDayRequestProxy extendedHours() {
            ((IntraDayRequest.Builder) this.builder).extendedHours();
            return this;
        }

        /**
         * Queries data for one specific month rather than the trailing window.
         *
         * @param month the month to query, as {@code YYYY-MM}
         * @return this proxy, for method chaining
         */
        public IntraDayRequestProxy month(String month) {
            ((IntraDayRequest.Builder) this.builder).month(month);
            return this;
        }
    }

    /** Proxy for building a {@link WeeklyRequest}. */
    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy, TimeSeriesResponse> {

        WeeklyRequestProxy() {
            super();
            this.builder = new WeeklyRequest.Builder();
        }

        /**
         * Switches the request to the split and dividend adjusted weekly series.
         *
         * @return this proxy, for method chaining
         */
        public WeeklyRequestProxy adjusted() {
            TimeSeries.this.adjusted = true;
            ((WeeklyRequest.Builder) this.builder).adjusted();
            return this;
        }
    }

    /** Proxy for building a {@link MonthlyRequest}. */
    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy, TimeSeriesResponse> {

        MonthlyRequestProxy() {
            super();
            this.builder = new MonthlyRequest.Builder();
        }

        /**
         * Switches the request to the split and dividend adjusted monthly series.
         *
         * @return this proxy, for method chaining
         */
        public MonthlyRequestProxy adjusted() {
            TimeSeries.this.adjusted = true;
            ((MonthlyRequest.Builder) this.builder).adjusted();
            return this;
        }
    }

    /** Proxy for building a {@link QuoteRequest}. */
    public class GlobalQuoteRequestProxy
            extends RequestProxy<GlobalQuoteRequestProxy, QuoteResponse> {

        GlobalQuoteRequestProxy() {
            super();
            this.builder = new QuoteRequest.Builder();
        }
    }

    /** Proxy for building a {@link RealtimeBulkQuoteRequest}. */
    public class RealtimeBulkQuoteRequestProxy
            extends RequestProxy<RealtimeBulkQuoteRequestProxy, RealtimeBulkQuoteResponse> {
        RealtimeBulkQuoteRequestProxy() {
            super();
            this.builder = new RealtimeBulkQuoteRequest.Builder();
        }
    }
}
