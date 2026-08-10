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
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * A request to {@code FX_DAILY}, which returns one open-high-low-close bar per trading
 * day for a currency pair.
 * <p>
 * This is the finest cadence that still spans years of history, which is why it is the
 * first of the four to need a length limit. It accepts an {@link OutputSize}: the
 * default {@link OutputSize#COMPACT} trims the answer to the 100 most recent days,
 * while {@link OutputSize#FULL} returns the pair's entire daily history.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class DailyRequest extends ForexRequest {

    private Function function;
    private OutputSize outputSize;

    private DailyRequest(Builder builder){
        super(builder);
        this.function = Function.FX_DAILY;
        this.outputSize = builder.outputSize;
    }

    /**
     * Assembles a request for daily bars, adding the output size to the currency pair
     * and data type inherited from {@link ForexRequest.Builder}.
     */
    public static class Builder extends ForexRequest.Builder<Builder>{

        Function function;
        OutputSize outputSize = OutputSize.COMPACT;

        /**
         * Sets how much of the pair's daily history to ask for. Defaults to
         * {@link OutputSize#COMPACT}, the 100 most recent days.
         *
         * @param outputSize the length of the returned series
         * @return this builder, for method chaining
         */
        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        /**
         * Assembles the currency pair and output size set so far into a daily request.
         *
         * @return a request for the configured pair's daily bars
         */
        @Override
        public ForexRequest build() {
            return new DailyRequest(this);
        }
    }
}
