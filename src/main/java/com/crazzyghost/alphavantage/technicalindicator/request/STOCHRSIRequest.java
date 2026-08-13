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
 * Request for the stochastic relative strength index ({@code STOCHRSI}),
 * which applies the {@link STOCHRequest stochastic oscillator}'s %K/%D
 * calculation to RSI values instead of price, producing a more sensitive
 * overbought/oversold reading than RSI alone.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class STOCHRSIRequest extends TechnicalIndicatorRequest {

    /** The number of data points used to calculate the underlying RSI. */
    @UrlParameter("time_period")
    private int timePeriod;

    /** The price series field the underlying RSI is computed from. */
    @UrlParameter("series_type")
    private SeriesType seriesType;

    /** The look-back period used for the raw (fast) %K calculation over RSI. */
    @UrlParameter("fastkperiod")
    private int fastKPeriod;

    /** The smoothing period applied to fast %K to produce fast %D. */
    @UrlParameter("fastdperiod")
    private int fastDPeriod;

    /** The moving-average type used to smooth fast %D. */
    @UrlParameter("fastdmatype")
    private MAType fastDMaType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected STOCHRSIRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.fastDPeriod = builder.fastDPeriod;
        this.fastDMaType = builder.fastDMaType;
        this.timePeriod = builder.timePeriod;
        this.seriesType = builder.seriesType;
    }

    /**
     * Fluent builder for {@link STOCHRSIRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The raw %K look-back period; defaults to 5. */
        private int fastKPeriod = 5;

        /** The fast %D smoothing period; defaults to 3. */
        private int fastDPeriod = 3;

        /** The moving-average type used to smooth fast %D; defaults to {@link MAType#SMA}. */
        private MAType fastDMaType = MAType.SMA;

        /** The number of data points used to calculate the underlying RSI. */
        private int timePeriod;

        /** The price series field the underlying RSI is computed from. */
        private SeriesType seriesType;

        /**
         * Creates a builder pre-set to {@link Function#STOCHRSI}.
         */
        public Builder() {
            this.function(Function.STOCHRSI);
        }

        /**
         * Sets the look-back period used for the raw (fast) %K calculation.
         *
         * @param fastKPeriod the fast %K period
         * @return this builder
         */
        public Builder fastKPeriod(int fastKPeriod) {
            this.fastKPeriod = fastKPeriod;
            return this;
        }

        /**
         * Sets the smoothing period applied to fast %K to produce fast %D.
         *
         * @param fastDPeriod the fast %D period
         * @return this builder
         */
        public Builder fastDPeriod(int fastDPeriod) {
            this.fastDPeriod = fastDPeriod;
            return this;
        }

        /**
         * Sets the moving-average type used to smooth fast %D.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder fastDMaType(MAType type) {
            this.fastDMaType = type;
            return this;
        }

        /**
         * Sets the number of data points used to calculate the underlying RSI.
         *
         * @param timePeriod the time period
         * @return this builder
         */
        public Builder timePeriod(int timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * Sets the price series field the underlying RSI is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link STOCHRSIRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new STOCHRSIRequest(this);
        }

    }
}
