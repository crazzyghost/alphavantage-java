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

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * Request for the Chaikin A/D oscillator ({@code ADOSC}), the MACD of the
 * Chaikin accumulation/distribution line using a fast and slow EMA period.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class ADOSCRequest extends TechnicalIndicatorRequest {

    /** The number of data points in the fast EMA. */
    @UrlParameter("fastperiod")
    private int fastPeriod;

    /** The number of data points in the slow EMA. */
    @UrlParameter("slowperiod")
    private int slowPeriod;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private ADOSCRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
    }

    /**
     * Fluent builder for {@link ADOSCRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast EMA; defaults to 3. */
        private int fastPeriod = 3;

        /** The number of data points in the slow EMA; defaults to 10. */
        private int slowPeriod = 10;

        /**
         * Creates a builder pre-set to {@link Function#ADOSC}.
         */
        public Builder() {
            this.function(Function.ADOSC);
        }

        /**
         * Sets the number of data points in the fast EMA.
         *
         * @param fastPeriod the fast period
         * @return this builder
         */
        public Builder fastPeriod(int fastPeriod) {
            this.fastPeriod = fastPeriod;
            return this;
        }

        /**
         * Sets the number of data points in the slow EMA.
         *
         * @param slowPeriod the slow period
         * @return this builder
         */
        public Builder slowPeriod(int slowPeriod) {
            this.slowPeriod = slowPeriod;
            return this;
        }

        /**
         * Builds the configured {@link ADOSCRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new ADOSCRequest(this);
        }

    }
}
