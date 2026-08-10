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

/**
 * Request for the stochastic oscillator ({@code STOCH}), which compares a
 * closing price to its recent high-low range and reports a smoothed
 * {@code %K} and {@code %D} line.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class STOCHRequest extends TechnicalIndicatorRequest {

    /** The look-back period used for the raw (fast) %K calculation. */
    private int fastKPeriod;

    /** The smoothing period applied to fast %K to produce slow %K. */
    private int slowKPeriod;

    /** The smoothing period applied to slow %K to produce slow %D. */
    private int slowDPeriod;

    /** The moving-average type used to smooth slow %K. */
    private MAType slowKMaType;

    /** The moving-average type used to smooth slow %D. */
    private MAType slowDMaType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected STOCHRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.slowKPeriod = builder.slowKPeriod;
        this.slowDPeriod = builder.slowDPeriod;
        this.slowDMaType = builder.slowDMaType;
        this.slowKMaType = builder.slowKMaType;
    }

    /**
     * Fluent builder for {@link STOCHRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The raw %K look-back period; defaults to 5. */
        private int fastKPeriod = 5;

        /** The slow %K smoothing period; defaults to 3. */
        private int slowKPeriod = 3;

        /** The slow %D smoothing period; defaults to 3. */
        private int slowDPeriod = 3;

        /** The moving-average type used to smooth slow %K; defaults to {@link MAType#SMA}. */
        private MAType slowKMaType = MAType.SMA;

        /** The moving-average type used to smooth slow %D; defaults to {@link MAType#SMA}. */
        private MAType slowDMaType = MAType.SMA;

        /**
         * Creates a builder pre-set to {@link Function#STOCH}.
         */
        public Builder() {
            this.function(Function.STOCH);
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
         * Sets the smoothing period applied to fast %K to produce slow %K.
         *
         * @param slowKPeriod the slow %K period
         * @return this builder
         */
        public Builder slowKPeriod(int slowKPeriod) {
            this.slowKPeriod = slowKPeriod;
            return this;
        }

        /**
         * Sets the smoothing period applied to slow %K to produce slow %D.
         *
         * @param slowDPeriod the slow %D period
         * @return this builder
         */
        public Builder slowDPeriod(int slowDPeriod) {
            this.slowDPeriod = slowDPeriod;
            return this;
        }

        /**
         * Sets the moving-average type used to smooth slow %K.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder slowKMaType(MAType type) {
            this.slowKMaType = type;
            return this;
        }

        /**
         * Sets the moving-average type used to smooth slow %D.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder slowDMaType(MAType type) {
            this.slowDMaType = type;
            return this;
        }

        /**
         * Builds the configured {@link STOCHRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new STOCHRequest(this);
        }

    }
}
