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
package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * The parameters every crypto currency request carries: the digital currency
 * symbol, the market to price it in, and the Alpha Vantage function to call.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public abstract class CryptoRequest {

    private final String market;
    private final Function function;
    private final String symbol;

    protected CryptoRequest(Builder<?> builder) {
        this.function = builder.function;
        this.symbol = builder.symbol;
        this.market = builder.market;
    }

    /**
     * Collects the parameters shared by every crypto currency request and
     * assembles them into a {@link CryptoRequest}.
     *
     * @param <T> the concrete builder subtype, so setters can be chained without
     *            casting
     */
    public abstract static class Builder<T extends Builder<?>> {

        /** The Alpha Vantage function this request calls, fixed by each subclass. */
        public Function function;
        /** The digital currency symbol to fetch, for example {@code BTC}. */
        protected String symbol;
        /** The market to price {@link #symbol} in, for example {@code USD} or {@code CNY}. */
        protected String market;

        /**
         * Sets the Alpha Vantage function this request calls.
         *
         * @param function the function code
         * @return this builder, for method chaining
         */
        public T function(Function function) {
            this.function = function;
            return (T) this;
        }

        /**
         * Sets the digital currency to fetch data for.
         *
         * @param symbol the digital currency symbol, for example {@code BTC}
         * @return this builder, for method chaining
         */
        public T symbol(String symbol) {
            this.symbol = symbol;
            return (T) this;
        }

        /**
         * Sets the market to price the digital currency in.
         *
         * @param market the market currency code, for example {@code USD} or {@code CNY}
         * @return this builder, for method chaining
         */
        public T market(String market) {
            this.market = market;
            return (T) this;
        }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's parameters
         */
        public abstract CryptoRequest build();
    }

}
