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

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for Bollinger Bands ({@code BBANDS}), an upper and lower volatility
 * band plotted a configurable number of standard deviations above and below
 * a moving average of a price series.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class BBANDSRequest extends TechnicalIndicatorRequest {

    /** The price series field the bands are computed from. */
    private SeriesType series_type;

    /** The number of data points in the middle moving average. */
    private int time_period;

    /** The number of standard deviations above the middle band for the upper band. */
    private int nbdevup;

    /** The number of standard deviations below the middle band for the lower band. */
    private int nbdevdn;

    /** The moving-average type used for the middle band. */
    private MAType maType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private BBANDSRequest(Builder builder) {
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
        this.nbdevdn = builder.nbdevdn;
        this.nbdevup = builder.nbdevup;
        this.maType = builder.maType;
    }

    /**
     * Fluent builder for {@link BBANDSRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The price series field the bands are computed from. */
        private SeriesType seriesType;

        /** The number of data points in the middle moving average. */
        private int timePeriod;

        /** The number of standard deviations above the middle band; defaults to 2. */
        private int nbdevup = 2;

        /** The number of standard deviations below the middle band; defaults to 2. */
        private int nbdevdn = 2;

        /** The moving-average type used for the middle band; defaults to {@link MAType#SMA}. */
        private MAType maType = MAType.SMA;

        /**
         * Creates a builder pre-set to {@link Function#BBANDS}.
         */
        public Builder() {
            this.function(Function.BBANDS);
        }

        /**
         * Sets the number of data points in the middle moving average.
         *
         * @param timePeriod the time period
         * @return this builder
         */
        public Builder timePeriod(int timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * Sets the price series field the bands are computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Sets the number of standard deviations above the middle band.
         *
         * @param nbdevup the upper-band standard deviation multiplier
         * @return this builder
         */
        public Builder nbdevup(int nbdevup) {
            this.nbdevup = nbdevup;
            return this;
        }

        /**
         * Sets the number of standard deviations below the middle band.
         *
         * @param nbdevdn the lower-band standard deviation multiplier
         * @return this builder
         */
        public Builder nbdevdn(int nbdevdn) {
            this.nbdevdn = nbdevdn;
            return this;
        }

        /**
         * Sets the moving-average type used for the middle band.
         *
         * @param maType the moving-average type
         * @return this builder
         */
        public Builder maType(MAType maType) {
            this.maType = maType;
            return this;
        }

        /**
         * Builds the configured {@link BBANDSRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new BBANDSRequest(this);
        }

    }
}
