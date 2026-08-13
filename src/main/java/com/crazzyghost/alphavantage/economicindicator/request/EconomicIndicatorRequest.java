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
package com.crazzyghost.alphavantage.economicindicator.request;

import com.crazzyghost.alphavantage.UrlParameter;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;

/**
 * The parameters every economic indicator request carries: the Alpha Vantage
 * function that selects the indicator, and the data type the series is returned in.
 * Subclasses add the parameters their indicator supports, such as interval or
 * maturity.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public abstract class EconomicIndicatorRequest {
    /** The Alpha Vantage function selecting which economic indicator this request targets. */
    @UrlParameter("function")
    protected Function function;
    /** The format the API replies in, sent as the {@code datatype} parameter. */
    @UrlParameter("datatype")
    protected DataType dataType;

    /**
     * Copies the shared parameters out of a builder.
     *
     * @param builder the builder holding the parameters to copy
     */
    protected EconomicIndicatorRequest(Builder<?> builder) {
        this.function = builder.getFunction();
        this.dataType = builder.dataType;
    }

    /**
     * Collects the parameters shared by every economic indicator request.
     * <p>
     * Each setter returns the concrete subclass builder rather than this base type, so
     * that setting a shared parameter part-way through a chain does not cut off access
     * to the indicator-specific setters that follow it.
     *
     * @param <T> the concrete builder type the shared setters return
     */
    public abstract static class Builder <T extends Builder<?>> {
        /** The Alpha Vantage function this request calls, set via {@link #function(Function)}. */
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

        /** The response format, set via {@link #dataType(DataType)}. Defaults to {@link DataType#JSON}. */
        public DataType dataType = DataType.JSON;

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
         * Assembles the parameters set so far into a request for this builder's
         * indicator.
         *
         * @return a request carrying this builder's parameters
         */
        public abstract EconomicIndicatorRequest build();

    }
}
