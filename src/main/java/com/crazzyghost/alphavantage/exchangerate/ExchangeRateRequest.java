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
package com.crazzyghost.alphavantage.exchangerate;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * Exchange Rate Request
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class ExchangeRateRequest {

    private final Function function;
    private final String from_currency;
    private final String to_currency;

    private ExchangeRateRequest(Builder builder){
        this.function = builder.function;
        this.from_currency = builder.fromCurrency;
        this.to_currency = builder.toCurrency;
    }

    /**
     * Collects the currency pair to quote and assembles it into an
     * {@link ExchangeRateRequest}.
     * <p>
     * The function is pinned to {@link Function#CURRENCY_EXCHANGE_RATE} on
     * construction, so the pair is the only thing a caller supplies. Both physical
     * and digital currencies are accepted on either side of the pair.
     */
    public static class Builder{
        private Function function;
        private String fromCurrency;
        private String toCurrency;

        /** Creates a builder for the {@code CURRENCY_EXCHANGE_RATE} endpoint. */
        public Builder(){
            this.function = Function.CURRENCY_EXCHANGE_RATE;
        }

        /**
         * Sets the base currency, the one being converted from.
         *
         * @param fromCurrency the base currency's code, for example {@code USD} or
         *                     {@code BTC}
         * @return this builder, for method chaining
         */
        public Builder fromCurrency(String fromCurrency){
            this.fromCurrency = fromCurrency;
            return this;
        }

        /**
         * Sets the quote currency, the one being converted to. The rate returned is
         * how many units of this currency one unit of the base currency buys.
         *
         * @param toCurrency the quote currency's code, for example {@code EUR} or
         *                   {@code BTC}
         * @return this builder, for method chaining
         */
        public Builder toCurrency(String toCurrency){
            this.toCurrency = toCurrency;
            return this;
        }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's currency pair
         */
        public ExchangeRateRequest build(){
            return new ExchangeRateRequest(this);
        }

    }
}
