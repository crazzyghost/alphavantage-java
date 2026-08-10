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

import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for the two price-oscillator indicators, {@code APO} and
 * {@code PPO}, which measure the difference between a fast and a slow
 * moving average of a price series.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class PriceOscillatorRequest extends TechnicalIndicatorRequest {

    /** The price series field the oscillator is computed from. */
    private SeriesType series_type;

    /** The number of data points in the fast moving average. */
    private int fastPeriod;

    /** The number of data points in the slow moving average. */
    private int slowPeriod;

    /** The moving-average type used for both the fast and slow averages. */
    private MAType maType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private PriceOscillatorRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
        this.maType = builder.maType;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link PriceOscillatorRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast moving average; defaults to 12. */
        private int fastPeriod = 12;

        /** The number of data points in the slow moving average; defaults to 26. */
        private int slowPeriod = 26;

        /** The moving-average type used for both averages; defaults to {@link MAType#SMA}. */
        private MAType maType = MAType.SMA;

        /** The price series field the oscillator is computed from. */
        private SeriesType seriesType;

        /**
         * Sets the number of data points in the fast moving average.
         *
         * @param fastPeriod the fast period
         * @return this builder
         */
        public Builder fastPeriod(int fastPeriod) {
            this.fastPeriod = fastPeriod;
            return this;
        }

        /**
         * Sets the number of data points in the slow moving average.
         *
         * @param slowPeriod the slow period
         * @return this builder
         */
        public Builder slowPeriod(int slowPeriod) {
            this.slowPeriod = slowPeriod;
            return this;
        }

        /**
         * Sets the price series field the oscillator is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Sets the moving-average type used for both the fast and slow averages.
         *
         * @param maType the moving-average type
         * @return this builder
         */
        public Builder maType(MAType maType) {
            this.maType = maType;
            return this;
        }

        /**
         * Builds the configured {@link PriceOscillatorRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new PriceOscillatorRequest(this);
        }

    }

}
