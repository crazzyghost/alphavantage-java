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
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for the MESA adaptive moving average ({@code MAMA}), an adaptive
 * moving average that adjusts its own smoothing speed to price movement
 * using the Hilbert transform, reported alongside its slower-following
 * companion, FAMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class MAMARequest extends TechnicalIndicatorRequest {

    /** The price series field the average is computed from. */
    @UrlParameter("series_type")
    private SeriesType seriesType;

    /** The upper bound on how fast MAMA can adapt during strongly trending price movement. */
    @UrlParameter("fastlimit")
    private double fastLimit;

    /** The lower bound on how fast MAMA can adapt during sideways price movement. */
    @UrlParameter("slowlimit")
    private double slowLimit;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private MAMARequest(Builder builder) {
        super(builder);
        this.fastLimit = builder.fastLimit;
        this.slowLimit = builder.slowLimit;
        this.seriesType = builder.seriesType;
    }

    /**
     * Fluent builder for {@link MAMARequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The upper adaptation-speed bound; defaults to 0.1. */
        private double fastLimit = 0.1;

        /** The lower adaptation-speed bound; defaults to 0.1. */
        private double slowLimit = 0.1;

        /** The price series field the average is computed from. */
        private SeriesType seriesType;

        /**
         * Creates a builder pre-set to {@link Function#MAMA}.
         */
        public Builder() {
            this.function(Function.MAMA);
        }

        /**
         * Sets the upper bound on how fast MAMA can adapt.
         *
         * @param fastLimit the fast limit
         * @return this builder
         */
        public Builder fastLimit(double fastLimit) {
            this.fastLimit = fastLimit;
            return this;
        }

        /**
         * Sets the lower bound on how fast MAMA can adapt.
         *
         * @param slowLimit the slow limit
         * @return this builder
         */
        public Builder slowLimit(double slowLimit) {
            this.slowLimit = slowLimit;
            return this;
        }

        /**
         * Sets the price series field the average is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link MAMARequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new MAMARequest(this);
        }

    }

}
