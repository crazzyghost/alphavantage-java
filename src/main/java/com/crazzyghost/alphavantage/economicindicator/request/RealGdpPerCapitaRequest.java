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

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * A request to {@code REAL_GDP_PER_CAPITA}, which returns the quarterly real GDP
 * per capita of the United States.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class RealGdpPerCapitaRequest extends EconomicIndicatorRequest {

    private RealGdpPerCapitaRequest(Builder builder) {
        super(builder);
    }

    /**
     * Assembles a request for the real GDP per capita series. The series is
     * fixed at quarterly cadence, so this builder carries no parameters beyond
     * those inherited from {@link EconomicIndicatorRequest.Builder}.
     */
    public static class Builder extends EconomicIndicatorRequest.Builder<Builder> {

        /**
         * Creates a builder for the {@code REAL_GDP_PER_CAPITA} endpoint.
         */
        public Builder() {
            super();
            this.function(Function.REAL_GDP_PER_CAPITA);
        }

        /**
         * Assembles the parameters set so far into a real GDP per capita request.
         *
         * @return a request for the real GDP per capita series
         */
        @Override
        public RealGdpPerCapitaRequest build() {
            return new RealGdpPerCapitaRequest(this);
        }
    }
}
