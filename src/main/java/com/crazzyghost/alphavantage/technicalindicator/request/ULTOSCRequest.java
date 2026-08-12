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
 * Request for the ultimate oscillator ({@code ULTOSC}), a momentum
 * oscillator that combines buying pressure across three time periods to
 * reduce the false-divergence signals a single-period oscillator produces.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class ULTOSCRequest extends TechnicalIndicatorRequest {

    /** The first, shortest look-back period. */
    private int timePeriod1;

    /** The second, medium look-back period. */
    private int timePeriod2;

    /** The third, longest look-back period. */
    private int timePeriod3;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected ULTOSCRequest(Builder builder) {
        super(builder);
        this.timePeriod1 = builder.timePeriod1;
        this.timePeriod2 = builder.timePeriod2;
        this.timePeriod3 = builder.timePeriod3;
    }

    /**
     * Fluent builder for {@link ULTOSCRequest}.
     */
    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        /** The first, shortest look-back period; defaults to 7. */
        public int timePeriod1 = 7;

        /** The second, medium look-back period; defaults to 14. */
        public int timePeriod2 = 14;

        /** The third, longest look-back period; defaults to 28. */
        public int timePeriod3 = 28;

        /**
         * Creates a builder pre-set to {@link Function#ULTOSC}.
         */
        public Builder() {
            this.function(Function.ULTOSC);
        }

        /**
         * Sets the first, shortest look-back period.
         *
         * @param period the first time period
         * @return this builder
         */
        public Builder timePeriod1(int period) {
            this.timePeriod1 = period;
            return this;
        }

        /**
         * Sets the second, medium look-back period.
         *
         * @param period the second time period
         * @return this builder
         */
        public Builder timePeriod2(int period) {
            this.timePeriod2 = period;
            return this;
        }

        /**
         * Sets the third, longest look-back period.
         *
         * @param period the third time period
         * @return this builder
         */
        public Builder timePeriod3(int period) {
            this.timePeriod3 = period;
            return this;
        }

        /**
         * Builds the configured {@link ULTOSCRequest}.
         *
         * @return the built request
         */
        @Override
        public TechnicalIndicatorRequest build() {
            return new ULTOSCRequest(this);
        }
    }

}
