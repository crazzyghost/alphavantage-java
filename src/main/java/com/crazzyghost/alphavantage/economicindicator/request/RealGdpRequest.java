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

public class RealGdpRequest extends EconomicIndicatorRequest {
    private Interval interval;

    private RealGdpRequest(Builder builder) {
        super(builder);
        this.interval = builder.interval;
    }

    public static class Builder extends EconomicIndicatorRequest.Builder<Builder> {
        Interval interval;

        public Builder() {
            super();
            this.function(Function.REAL_GDP);
        }

        public Builder interval(Interval interval) {
            if (!Arrays.asList(Interval.QUARTERLY, Interval.ANNUAL).contains(interval)) {
                throw new AlphaVantageException("accepted interval values for REAL_GDP are Interval.QUARTERLY, Interval.ANNUAL");
            }
            this.interval = interval;
            return this;
        }

        @Override
        public RealGdpRequest build() {
            return new RealGdpRequest(this);
        }
    }
}
