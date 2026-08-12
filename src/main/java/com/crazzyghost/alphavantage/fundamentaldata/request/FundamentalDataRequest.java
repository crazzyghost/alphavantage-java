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
package com.crazzyghost.alphavantage.fundamentaldata.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * The parameters every fundamental data request carries: the ticker symbol to
 * report on, and the Alpha Vantage function that selects which report is returned.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.6.0
 */
public abstract class FundamentalDataRequest {

    /** The Alpha Vantage function that selects which report is returned. */
    protected Function function;
    /** The ticker symbol to request data for. */
    protected String symbol;

    /**
     * Creates a request from the given builder's current state.
     *
     * @param builder the builder to copy the symbol and function from
     */
    protected FundamentalDataRequest(Builder<?> builder) {
        this.function = builder.function;
        this.symbol = builder.symbol;
    }


    /**
     * Base builder shared by every fundamental data request, carrying the
     * ticker symbol and the Alpha Vantage function to request.
     *
     * @param <T> the concrete builder subtype, for fluent method chaining
     */
    public abstract static class Builder <T extends Builder<?>> {

        private String symbol;
        /** The Alpha Vantage function that selects which report is returned. */
        public Function function;

        /**
         * Sets the ticker symbol to request data for.
         *
         * @param  symbol the ticker symbol
         * @return this builder, for chaining
         */
        public T symbol(String symbol){
            this.symbol = symbol;
            return (T) this;
        }

        /**
         * Sets the Alpha Vantage function that selects which report is
         * returned. Concrete subclasses set this themselves and do not
         * expose it for further changes.
         *
         * @param  function the Alpha Vantage function code
         * @return this builder, for chaining
         */
        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        /**
         * Builds the request from this builder's current state.
         *
         * @return the built request
         */
        public abstract FundamentalDataRequest build();

    }
}
