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
package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;

/**
 * The parameters every stock time series request carries: which endpoint to call, which
 * ticker to call it for, and the format the API should answer in.
 * <p>
 * Seven subclasses extend it, and they do not all return series data. Four sample a
 * ticker's price history at a fixed cadence — {@link IntraDayRequest},
 * {@link DailyRequest}, {@link WeeklyRequest} and {@link MonthlyRequest} — while
 * {@link QuoteRequest} and {@link RealtimeBulkQuoteRequest} ask for a single current
 * snapshot, for one ticker and for many respectively. What they share is this class's
 * three parameters, which is why they share a base type at all.
 * <p>
 * Unlike its sibling request hierarchies, this one lets the subclass choose the
 * endpoint at build time rather than pinning it in a field: {@link Builder#function}
 * stays writable so that {@link DailyRequest.Builder#adjusted()} and its counterparts
 * can swap in the split and dividend adjusted variant of a cadence part-way through a
 * chain.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public abstract class TimeSeriesRequest {

    private Function function;
    private String symbol;
    private DataType dataType;


    /**
     * Copies the shared parameters out of a builder.
     *
     * @param builder the builder holding the parameters to copy
     */
    protected TimeSeriesRequest(Builder<?> builder){
        this.symbol = builder.symbol;
        this.dataType = builder.dataType;
        this.function  = builder.function;
    }

    /**
     * Collects the parameters shared by every stock time series endpoint.
     * <p>
     * Each setter returns the concrete subclass builder rather than this base type, so
     * that setting a shared parameter part-way through a chain does not cut off access
     * to the endpoint-specific setters that follow it.
     *
     * @param <T> the concrete builder type the shared setters return
     */
    public static abstract class Builder<T extends Builder<T>>{

        /** The format the API replies in, sent as the {@code datatype} parameter. */
        protected DataType dataType = DataType.JSON;

        /** The ticker being requested, sent as the {@code symbol} parameter. */
        protected String symbol;

        /**
         * The endpoint being called, sent as the {@code function} parameter.
         * <p>
         * Every subclass builder pins this in its own constructor, so callers never
         * need to set it. It is left writable, and public, because the cadence builders
         * rewrite it in place when {@code adjusted()} switches a request over to the
         * adjusted variant of the same cadence.
         */
        public Function function;

        /**
         * Creates a builder with no ticker set and {@link DataType#JSON} as the reply
         * format. Subclasses call it before pinning their own endpoint.
         */
        public Builder(){

        }

        /**
         * Sets the format the API replies in. Defaults to {@link DataType#JSON}, which
         * is what this library's response parsers read.
         *
         * @param dataType the response format
         * @return this builder, for method chaining
         */
        public T dataType(DataType dataType){
            this.dataType = dataType;
            return (T) this;
        }

        /**
         * Sets the ticker to request data for.
         *
         * @param symbol the ticker symbol, for example {@code IBM}
         * @return this builder, for method chaining
         */
        public T forSymbol(String symbol){
            this.symbol = symbol;
            return (T) this;
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

        /**
         * Assembles the parameters set so far into a request for this builder's
         * endpoint.
         *
         * @return a request carrying this builder's parameters
         */
        public abstract TimeSeriesRequest build();

    }
}
