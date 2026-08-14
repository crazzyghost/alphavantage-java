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
package com.crazzyghost.alphavantage.technicalindicator.request;

import com.crazzyghost.alphavantage.UrlParameter;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Entitlement;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;

/**
 * Base request for every technical indicator endpoint, carrying the
 * parameters ({@code function}, {@code symbol}, {@code interval},
 * {@code datatype}) that all indicators share.
 * <p>
 * Concrete subclasses (for example {@link PeriodicRequest} or
 * {@link SeriesRequest}) add whatever further parameters their indicator
 * requires, such as {@code time_period} or {@code series_type}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public abstract class TechnicalIndicatorRequest {

    /** The Alpha Vantage function code identifying which indicator to request. */
    @UrlParameter("function")
    protected Function function;

    /** The equity, forex pair, or digital/crypto currency symbol to query. */
    @UrlParameter("symbol")
    protected String symbol;

    /** The time interval between two consecutive data points in the series. */
    @UrlParameter("interval")
    protected Interval interval;

    /** The response format, JSON or CSV. */
    @UrlParameter("datatype")
    protected DataType dataType;

    /** Data freshness tier for premium plans, realtime or delayed. */
    @UrlParameter("entitlement")
    protected Entitlement entitlement;

    /**
     * The {@code YYYY-MM} historical intraday window to request. Only
     * meaningful for intraday {@link Interval} values; {@code null} requests
     * the most recent window.
     */
    @UrlParameter("month")
    protected String month;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected TechnicalIndicatorRequest(Builder<?> builder) {
        this.function = builder.getFunction();
        this.symbol = builder.symbol;
        this.interval = builder.interval;
        this.dataType = builder.dataType;
        this.month = builder.month;
        this.entitlement = builder.entitlement;
    }

    /**
     * Base fluent builder shared by every technical indicator request.
     * <p>
     * {@code T} is the concrete builder subtype, letting each fluent setter
     * return the subclass's own type instead of {@code Builder} itself.
     *
     * @param <T> the concrete builder type returned by this builder's setters
     */
    public abstract static class Builder<T extends Builder<?>> {

        /** The Alpha Vantage function code identifying which indicator to request. */
        protected Function function;

        /**
         * Returns the endpoint this builder currently targets.
         *
         * @return the API function, or {@code null} before a subclass pins one
         */
        public Function getFunction() {
            return function;
        }

        /**
         * Sets the endpoint to call. Each subclass builder already pins the endpoint
         * matching its own cadence, so calling this directly overrides that choice and
         * is rarely what a caller wants.
         *
         * @param function the endpoint to call
         * @return this builder, for method chaining
         */
        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        /** The equity, forex pair, or digital/crypto currency symbol to query. */
        protected String symbol;

        /** The time interval between data points; defaults to {@link Interval#SIXTY_MIN}. */
        protected Interval interval = Interval.SIXTY_MIN;

        /** The response format; defaults to {@link DataType#JSON}. */
        protected DataType dataType = DataType.JSON;

        /**
         * The {@code YYYY-MM} historical intraday window to request; unset by
         * default, which requests the most recent window.
         */
        protected String month;

        /** Data freshness tier for premium plans, realtime or delayed. */
        protected Entitlement entitlement;

        /**
         * Sets the symbol to query.
         *
         * @param symbol the equity, forex pair, or digital/crypto currency symbol
         * @return this builder
         */
        public T forSymbol(String symbol) {
            this.symbol = symbol;
            return (T) this;
        }

        /**
         * Sets the time interval between data points.
         *
         * @param interval the interval
         * @return this builder
         */
        public T interval(Interval interval) {
            this.interval = interval;
            return (T) this;
        }

        /**
         * Sets the response format.
         *
         * @param dataType the response format
         * @return this builder
         */
        public T dataType(DataType dataType) {
            this.dataType = dataType;
            return (T) this;
        }

        /**
         * Sets the historical intraday window to request, in {@code YYYY-MM}
         * form. Only meaningful for intraday {@link Interval} values; the API
         * ignores it for {@code daily}, {@code weekly} and {@code monthly}
         * intervals.
         *
         * @param month the historical window, in {@code YYYY-MM} form
         * @return this builder
         */
        public T month(String month) {
            this.month = month;
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
         * @return this builder
         * @since 1.9.0
         */
        public T entitlement(Entitlement entitlement) {
            this.entitlement = entitlement;
            return (T) this;
        }

        /**
         * Builds the concrete request instance configured by this builder.
         *
         * @return the built request
         */
        public abstract TechnicalIndicatorRequest build();
    }

}
