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

/**
 * Request for indicators that operate on a rolling time period but not on a
 * specific price series field, such as {@code WILLR}, {@code ADX}, or
 * {@code CCI}, which are computed directly from an instrument's high, low,
 * and close rather than from a chosen {@code series_type}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class PeriodicRequest extends TechnicalIndicatorRequest {

    /** The number of data points used to calculate each indicator value. */
    private int time_period;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private PeriodicRequest(Builder builder) {
        super(builder);
        this.time_period = builder.timePeriod;
    }

    /**
     * Fluent builder for {@link PeriodicRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The number of data points used to calculate each indicator value; defaults to 60. */
        private int timePeriod = 60;

        /**
         * Sets the number of data points used to calculate each indicator value.
         *
         * @param timePeriod the time period
         * @return this builder
         */
        public Builder timePeriod(int timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * Builds the configured {@link PeriodicRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new PeriodicRequest(this);
        }

    }

}
