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

/**
 * Request for the parabolic SAR ({@code SAR}), a trend-following stop and
 * reversal indicator that trails price and accelerates toward it over time.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class SARRequest extends TechnicalIndicatorRequest {

    /** The acceleration factor step applied on each new extreme point. */
    private double acceleration;

    /** The acceleration factor's upper bound. */
    private double maximum;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private SARRequest(Builder builder) {
        super(builder);
        this.acceleration = builder.acceleration;
        this.maximum = builder.maximum;
    }

    /**
     * Fluent builder for {@link SARRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The acceleration factor step; defaults to 0.01. */
        private double acceleration = 0.01;

        /** The acceleration factor's upper bound; defaults to 0.20. */
        private double maximum = 0.20;

        /**
         * Creates a builder pre-set to {@link Function#SAR}.
         */
        public Builder() {
            this.function(Function.SAR);
        }

        /**
         * Sets the acceleration factor step applied on each new extreme point.
         *
         * @param acceleration the acceleration step
         * @return this builder
         */
        public Builder acceleration(double acceleration) {
            this.acceleration = acceleration;
            return this;
        }

        /**
         * Sets the acceleration factor's upper bound.
         *
         * @param maximum the maximum acceleration
         * @return this builder
         */
        public Builder maximum(double maximum) {
            this.maximum = maximum;
            return this;
        }

        /**
         * Builds the configured {@link SARRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new SARRequest(this);
        }

    }
}
