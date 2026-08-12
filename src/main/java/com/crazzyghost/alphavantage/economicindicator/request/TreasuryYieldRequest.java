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
package com.crazzyghost.alphavantage.economicindicator.request;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.Maturity;

import java.util.Arrays;

/**
 * A request to {@code TREASURY_YIELD}, which returns the daily, weekly, and
 * monthly US Treasury yield for a given {@link Maturity}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class TreasuryYieldRequest extends EconomicIndicatorRequest {
    private Interval interval;
    private Maturity maturity;

    private TreasuryYieldRequest(Builder builder) {
        super(builder);
        this.interval = builder.interval;
        this.maturity = builder.maturity;
    }

    /**
     * Assembles a request for the Treasury yield series, adding the reporting
     * interval and bond maturity to the parameters inherited from
     * {@link EconomicIndicatorRequest.Builder}.
     */
    public static class Builder extends EconomicIndicatorRequest.Builder<Builder> {
        Interval interval;
        Maturity maturity;

        /**
         * Creates a builder for the {@code TREASURY_YIELD} endpoint.
         */
        public Builder() {
            super();
            this.function(Function.TREASURY_YIELD);
        }

        /**
         * Sets the reporting interval for the series.
         *
         * @param interval the reporting interval; must be {@link Interval#DAILY},
         *                 {@link Interval#WEEKLY}, or {@link Interval#MONTHLY}
         * @return this builder, for method chaining
         * @throws AlphaVantageException if {@code interval} is not
         *                                {@link Interval#DAILY}, {@link Interval#WEEKLY},
         *                                or {@link Interval#MONTHLY}
         */
        public Builder interval(Interval interval) {
            if (!Arrays.asList(Interval.DAILY, Interval.WEEKLY, Interval.MONTHLY).contains(interval)) {
                throw new AlphaVantageException("accepted interval values for TREASURY_YIELD are Interval.DAILY, Interval.WEEKLY, Interval.MONTHLY");
            }
            this.interval = interval;
            return this;
        }

        /**
         * Sets the bond maturity the yield is reported for.
         *
         * @param maturity the Treasury bond maturity
         * @return this builder, for method chaining
         */
        public Builder maturity(Maturity maturity) {
            this.maturity = maturity;
            return this;
        }

        /**
         * Assembles the parameters set so far into a Treasury yield request.
         *
         * @return a request for the Treasury yield series
         */
        @Override
        public TreasuryYieldRequest build() {
            return new TreasuryYieldRequest(this);
        }
    }
}
