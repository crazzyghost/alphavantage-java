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
package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * A request to {@code FX_MONTHLY}, which condenses a currency pair's history into one
 * open-high-low-close bar per calendar month.
 * <p>
 * This is the coarsest of the four forex cadences, and the one that reaches furthest
 * back for a given number of data points. The newest bar covers the month still in
 * progress, so its close is the pair's latest price rather than a settled month-end
 * value.
 * <p>
 * {@code FX_MONTHLY} accepts no parameters beyond the currency pair and data type
 * inherited from {@link ForexRequest}, and always returns the pair's whole monthly
 * history.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class MonthlyRequest extends ForexRequest{

    private Function function;

    private MonthlyRequest(Builder builder){
        super(builder);
        this.function = Function.FX_MONTHLY;
    }

    /**
     * Assembles a request for monthly bars.
     * <p>
     * It adds no setters of its own, since the currency pair and data type inherited
     * from {@link ForexRequest.Builder} are everything {@code FX_MONTHLY} accepts.
     */
    public static class Builder extends ForexRequest.Builder<Builder> {

        /**
         * Creates a builder for the {@code FX_MONTHLY} endpoint.
         */
        public Builder(){
            super();
        }

        /**
         * Assembles the currency pair set so far into a monthly request.
         *
         * @return a request for the configured pair's monthly bars
         */
        @Override
        public MonthlyRequest build() {
            return new MonthlyRequest(this);
        }
    }
}
