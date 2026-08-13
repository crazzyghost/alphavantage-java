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
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for MACD with controllable moving-average type ({@code MACDEXT}),
 * a variant of {@link MACDRequest MACD} that lets the fast, slow, and signal
 * components each use a different moving-average type instead of the fixed
 * EMA the plain {@code MACD} function uses.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class MACDEXTRequest extends TechnicalIndicatorRequest {

    /** The price series field MACD is computed from. */
    @UrlParameter("series_type")
    private SeriesType seriesType;

    /** The number of data points in the fast moving average. */
    @UrlParameter("fastperiod")
    private int fastPeriod;

    /** The number of data points in the slow moving average. */
    @UrlParameter("slowperiod")
    private int slowPeriod;

    /** The number of data points in the signal line's moving average. */
    @UrlParameter("signalperiod")
    private int signalPeriod;

    /** The moving-average type used for the fast component. */
    @UrlParameter("fastmatype")
    private MAType fastMaType;

    /** The moving-average type used for the slow component. */
    @UrlParameter("slowmatype")
    private MAType slowMaType;

    /** The moving-average type used for the signal line. */
    @UrlParameter("signalmatype")
    private MAType signalMaType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private MACDEXTRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
        this.signalPeriod = builder.signalPeriod;
        this.fastMaType = builder.fastMaType;
        this.slowMaType = builder.slowMaType;
        this.signalMaType = builder.signalMaType;
        this.seriesType = builder.seriesType;
    }

    /**
     * Fluent builder for {@link MACDEXTRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast moving average; defaults to 12. */
        private int fastPeriod = 12;

        /** The number of data points in the slow moving average; defaults to 26. */
        private int slowPeriod = 26;

        /** The number of data points in the signal line's moving average; defaults to 9. */
        private int signalPeriod = 9;

        /** The moving-average type used for the fast component; defaults to {@link MAType#SMA}. */
        private MAType fastMaType = MAType.SMA;

        /** The moving-average type used for the slow component; defaults to {@link MAType#SMA}. */
        private MAType slowMaType = MAType.SMA;

        /** The moving-average type used for the signal line; defaults to {@link MAType#SMA}. */
        private MAType signalMaType = MAType.SMA;

        /** The price series field MACD is computed from. */
        private SeriesType seriesType;

        /**
         * Creates a builder pre-set to {@link Function#MACDEXT}.
         */
        public Builder() {
            this.function(Function.MACDEXT);
        }

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
         * Sets the number of data points in the signal line's moving average.
         *
         * @param signalPeriod the signal period
         * @return this builder
         */
        public Builder signalPeriod(int signalPeriod) {
            this.signalPeriod = signalPeriod;
            return this;
        }

        /**
         * Sets the moving-average type used for the fast component.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder fastMaType(MAType type) {
            this.fastMaType = type;
            return this;
        }

        /**
         * Sets the moving-average type used for the slow component.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder slowMaType(MAType type) {
            this.slowMaType = type;
            return this;
        }

        /**
         * Sets the moving-average type used for the signal line.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder signalMaType(MAType type) {
            this.signalMaType = type;
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
         * Builds the configured {@link MACDEXTRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new MACDEXTRequest(this);
        }

    }
}
