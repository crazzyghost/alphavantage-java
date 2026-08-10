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
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for moving average convergence / divergence ({@code MACD}), the
 * difference between a fast and slow EMA of a price series, together with a
 * signal line that is itself an EMA of that difference.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class MACDRequest extends TechnicalIndicatorRequest {

    /** The price series field MACD is computed from. */
    private SeriesType series_type;

    /** The number of data points in the fast EMA. */
    private int fastPeriod;

    /** The number of data points in the slow EMA. */
    private int slowPeriod;

    /** The number of data points in the signal line's EMA. */
    private int signalPeriod;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private MACDRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
        this.signalPeriod = builder.signalPeriod;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link MACDRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast EMA; defaults to 12. */
        private int fastPeriod = 12;

        /** The number of data points in the slow EMA; defaults to 26. */
        private int slowPeriod = 26;

        /** The number of data points in the signal line's EMA; defaults to 9. */
        private int signalPeriod = 9;

        /** The price series field MACD is computed from. */
        private SeriesType seriesType;

        /**
         * Creates a builder pre-set to {@link Function#MACD}.
         */
        public Builder() {
            this.function(Function.MACD);
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
         * Sets the number of data points in the signal line's EMA.
         *
         * @param signalPeriod the signal period
         * @return this builder
         */
        public Builder signalPeriod(int signalPeriod) {
            this.signalPeriod = signalPeriod;
            return this;
        }

        /**
         * Sets the price series field MACD is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link MACDRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new MACDRequest(this);
        }

    }
}
