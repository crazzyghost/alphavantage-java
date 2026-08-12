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
 * A request for a digital currency time series. The caller picks the cadence by
 * setting the function on the builder, so the same request type serves the daily,
 * weekly, monthly and intraday endpoints.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class DigitalCurrencyRequest extends CryptoRequest {

    private DigitalCurrencyRequest(Builder builder) {
        super(builder);
    }


    /**
     * Collects a caller's digital currency symbol, market and cadence and
     * assembles them into a {@link DigitalCurrencyRequest}.
     * <p>
     * The cadence is set through the inherited {@link #function(Function)},
     * choosing among {@link Function#DIGITAL_CURRENCY_DAILY},
     * {@link Function#DIGITAL_CURRENCY_WEEKLY} and
     * {@link Function#DIGITAL_CURRENCY_MONTHLY}.
     */
    public static class Builder extends CryptoRequest.Builder<Builder> {

        /** Creates a builder with no function, symbol or market set yet. */
        public Builder() {
        }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's parameters
         */
        @Override
        public CryptoRequest build() {
            return new DigitalCurrencyRequest(this);
        }
    }

}