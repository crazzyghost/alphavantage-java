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
package com.crazzyghost.alphavantage.fundamentaldata;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.fundamentaldata.request.*;
import com.crazzyghost.alphavantage.fundamentaldata.response.*;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Access to Fundamental Data.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.6.0
 */
public final class FundamentalData implements Fetcher {

    private final Config config;
    private FundamentalDataRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    /**
     * Creates access to Fundamental Data using the given configuration.
     *
     * @param config the shared library configuration, providing the API key
     *               and HTTP client
     */
    public FundamentalData(Config config) { this.config = config; }


    /**
     * Starts building a request for the {@code INCOME_STATEMENT} endpoint.
     *
     * @return a new income statement request proxy
     */
    public IncomeStatementRequestProxy incomeStatement() {
        return new IncomeStatementRequestProxy();
    }

    /**
     * Starts building a request for the {@code BALANCE_SHEET} endpoint.
     *
     * @return a new balance sheet request proxy
     */
    public BalanceSheetRequestProxy balanceSheet() {
        return new BalanceSheetRequestProxy();
    }

    /**
     * Starts building a request for the {@code CASH_FLOW} endpoint.
     *
     * @return a new cash flow request proxy
     */
    public CashFlowRequestProxy cashFlow() {
        return new CashFlowRequestProxy();
    }

    /**
     * Starts building a request for the {@code EARNINGS} endpoint.
     *
     * @return a new earnings request proxy
     */
    public EarningsRequestProxy earnings() {
        return new EarningsRequestProxy();
    }

    /**
     * Starts building a request for the {@code OVERVIEW} endpoint.
     *
     * @return a new company overview request proxy
     */
    public CompanyOverViewRequestProxy companyOverview() {
        return new CompanyOverViewRequestProxy();
    }

    /**
     * Makes an asynchronous http request to fetch the data, using the
     * builder and callbacks most recently set through a
     * {@link RequestProxy}.
     * <p>
     * The {@link SuccessCallback} or {@link FailureCallback} previously
     * registered on the proxy is invoked once the response arrives.
     */
    @Override
    public void fetch() {
        Config.checkNotNullOrKeyEmpty(config);

        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(Call call,  Response response) throws IOException {
                if(!response.isSuccessful()){
                    if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                } else {
                    try(ResponseBody body = response.body()){
                        parseFundamentalDataResponse(Parser.parseJSON(body.string()));
                    }
                }
            }
        });
    }

    /**
     * Makes a blocking synchronous http request to fetch the data.
     * This is called by {@link FundamentalData.RequestProxy#fetchSync()}.
     * <p>
     * Using this method will overwrite any async callback.
     *
     * @param successCallback internally used {@link SuccessCallback} that receives the parsed response
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.6.0
     */
    private void fetchSync(SuccessCallback<?> successCallback) throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);

        this.successCallback = successCallback;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()) {
            parseFundamentalDataResponse(Parser.parseJSON(response.body().string()));
        } catch(IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }
    }

    private void parseFundamentalDataResponse(Map<String, Object> data) {
        switch (builder.function) {
            case OVERVIEW:
                parseCompanyOverviewResponse(data);
                break;
            case BALANCE_SHEET:
                parseBalanceSheetResponse(data);
                break;
            case INCOME_STATEMENT:
                parseIncomeStatementResponse(data);
                break;
            case CASH_FLOW:
                parseCashFlowResponse(data);
                break;
            case EARNINGS:
                parseEarningsResponse(data);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private void parseCompanyOverviewResponse(Map<String, Object> data/*Object data*/) {
        CompanyOverviewResponse response = CompanyOverviewResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<CompanyOverviewResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseBalanceSheetResponse(Map<String, Object> data) {
        BalanceSheetResponse response = BalanceSheetResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<BalanceSheetResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseIncomeStatementResponse(Map<String, Object> data) {
        IncomeStatementResponse response = IncomeStatementResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<IncomeStatementResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseCashFlowResponse(Map<String, Object> data) {
        CashFlowResponse response = CashFlowResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<CashFlowResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseEarningsResponse(Map<String, Object> data) {
        EarningsResponse response = EarningsResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<EarningsResponse>)successCallback).onSuccess(response);
        }
    }

    /**
     * Base class for the fluent, per-endpoint request proxies returned by
     * {@link FundamentalData}'s accessor methods (for example
     * {@link #incomeStatement()}), sharing the symbol/callback wiring and
     * fetch logic common to all of them.
     *
     * @param <Proxy>         the concrete proxy subtype, for fluent method
     *                        chaining
     * @param <ProxyResponse> the response type this proxy's endpoint returns
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<Proxy extends RequestProxy<?, ProxyResponse>, ProxyResponse> {
        /** The request builder this proxy delegates symbol/parameter changes to. */
        protected FundamentalDataRequest.Builder<?> builder;
        /** The response received by the most recent {@link #fetchSync()} call. */
        protected ProxyResponse syncResponse;

        private RequestProxy() {}

        /**
         * Sets the ticker symbol to request data for.
         *
         * @param  symbol the ticker symbol
         * @return this proxy, for chaining
         */
        public Proxy forSymbol(String symbol) {
            this.builder.symbol(symbol);
            return (Proxy) this;
        }

        /**
         * Registers the callback invoked with the parsed response when an
         * asynchronous {@link #fetch()} succeeds.
         *
         * @param  callback the success callback
         * @return this proxy, for chaining
         */
        public Proxy onSuccess(SuccessCallback<?> callback) {
            FundamentalData.this.successCallback = callback;
            return (Proxy)this;
        }

        /**
         * Registers the callback invoked when an asynchronous {@link #fetch()}
         * fails.
         *
         * @param  callback the failure callback
         * @return this proxy, for chaining
         */
        public Proxy onFailure(FailureCallback callback) {
            FundamentalData.this.failureCallback = callback;
            return (Proxy)this;
        }

        /**
         * Makes an asynchronous http request to fetch the data built by this
         * proxy, invoking whichever of {@link #onSuccess(SuccessCallback)}
         * and {@link #onFailure(FailureCallback)} was registered.
         */
        public void fetch() {
            FundamentalData.this.builder = this.builder;
            FundamentalData.this.fetch();
        }

        /**
         * Stores the response received by {@link #fetchSync()}'s internal
         * callback, so it can be returned to the caller.
         *
         * @param response the parsed synchronous response
         */
        public void setSyncResponse(ProxyResponse response) {
            this.syncResponse = response;
        }


        /**
         * Sets the right builder and makes a synchronous request using
         * {@link FundamentalData#fetch()}.
         * <p>
         * When calling this method, any async callbacks will be overwritten.
         *
         * @return the api response
         * @throws AlphaVantageException if the request fails or the response cannot be read
         */
        public ProxyResponse fetchSync() throws AlphaVantageException {
            SuccessCallback<ProxyResponse> callback = this::setSyncResponse;
            FundamentalData.this.builder = this.builder;
            FundamentalData.this.fetchSync(callback);
            return this.syncResponse;
        }

    }

    /** Proxy class for building an {@link IncomeStatementRequest}. */
    public class IncomeStatementRequestProxy extends RequestProxy<IncomeStatementRequestProxy, IncomeStatementResponse> {
        /** Creates a proxy backed by a new {@link IncomeStatementRequest.Builder}. */
        public IncomeStatementRequestProxy() {
            builder = new IncomeStatementRequest.Builder();
        }
    }

    /** Proxy class for building a {@link BalanceSheetRequest}. */
    public class BalanceSheetRequestProxy extends RequestProxy<BalanceSheetRequestProxy, BalanceSheetResponse> {
        /** Creates a proxy backed by a new {@link BalanceSheetRequest.Builder}. */
        public BalanceSheetRequestProxy() {
            builder = new BalanceSheetRequest.Builder();
        }
    }

    /** Proxy class for building a {@link CashFlowRequest}. */
    public class CashFlowRequestProxy extends RequestProxy<CashFlowRequestProxy, CashFlowResponse> {
        /** Creates a proxy backed by a new {@link CashFlowRequest.Builder}. */
        public CashFlowRequestProxy() {
            builder = new CashFlowRequest.Builder();
        }
    }

    /** Proxy class for building an {@link EarningsRequest}. */
    public class EarningsRequestProxy extends RequestProxy<EarningsRequestProxy, EarningsResponse> {
        /** Creates a proxy backed by a new {@link EarningsRequest.Builder}. */
        public EarningsRequestProxy() {
            builder = new EarningsRequest.Builder();
        }
    }

    /** Proxy class for building a {@link CompanyOverviewRequest}. */
    public class CompanyOverViewRequestProxy extends RequestProxy<CompanyOverViewRequestProxy, CompanyOverviewResponse> {
        /** Creates a proxy backed by a new {@link CompanyOverviewRequest.Builder}. */
        public CompanyOverViewRequestProxy() {
            builder = new CompanyOverviewRequest.Builder();
        }
    }
}
