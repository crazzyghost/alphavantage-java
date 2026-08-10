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

import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for indicators computed over a rolling time period applied to a
 * chosen price series field, such as {@code SMA}, {@code EMA}, or
 * {@code RSI}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class PeriodicSeriesRequest extends TechnicalIndicatorRequest {

    /** The price series field the indicator is computed from. */
    private SeriesType series_type;

    /** The number of data points used to calculate each indicator value. */
    private int time_period;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private PeriodicSeriesRequest(Builder builder) {
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link PeriodicSeriesRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The price series field the indicator is computed from. */
        private SeriesType seriesType;

        /** The number of data points used to calculate each indicator value. */
        private int timePeriod;

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
         * Sets the price series field the indicator is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link PeriodicSeriesRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {

            return new PeriodicSeriesRequest(this);
        }

    }
}
