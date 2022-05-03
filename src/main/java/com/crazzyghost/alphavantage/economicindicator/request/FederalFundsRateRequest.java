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

import java.util.Arrays;

public class FederalFundsRateRequest extends EconomicIndicatorRequest {

    private FederalFundsRateRequest(Builder builder) {
        super(builder);
    }

    public static class Builder extends EconomicIndicatorRequest.Builder<Builder> {
        Interval interval;

        public Builder() {
            super();
            this.function(Function.FEDERAL_FUNDS_RATE);
        }

        public Builder interval(Interval interval) {
            if (!Arrays.asList(Interval.DAILY, Interval.WEEKLY, Interval.MONTHLY).contains(interval)) {
                throw new AlphaVantageException("accepted interval values for FEDERAL_FUNDS_RATE are Interval.DAILY, Interval.WEEKLY, Interval.MONTHLY");
            }
            this.interval = interval;
            return this;
        }

        @Override
        public FederalFundsRateRequest build() {
            return new FederalFundsRateRequest(this);
        }
    }
}
