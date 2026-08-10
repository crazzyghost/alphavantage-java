/*
 *
 * Copyright (c) 2025 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage.marketstatus.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * The endpoint parameters of a {@code MARKET_STATUS} call: just the fixed function
 * name, since the endpoint reports every tracked market rather than data scoped to
 * a symbol.
 * <p>
 * Instances are immutable and built through {@link Builder}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class MarketStatusRequest {
    private final Function function;

    private MarketStatusRequest(Builder builder) {
        this.function = builder.function;
    }

    /**
     * Assembles the parameters of a {@code MARKET_STATUS} call into a
     * {@link MarketStatusRequest}.
     */
    public static class Builder {
        private final Function function;

        /**
         * Creates a builder for the {@code MARKET_STATUS} endpoint.
         */
        public Builder() {
            this.function = Function.MARKET_STATUS;
        }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's parameters
         */
        public MarketStatusRequest build() {
            return new MarketStatusRequest(this);
        }
    }
}
