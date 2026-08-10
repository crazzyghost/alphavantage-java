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
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * A request to {@code FX_INTRADAY}, which samples a currency pair repeatedly through
 * the trading day instead of collapsing each period into a single bar.
 * <p>
 * It is the only forex cadence whose bar width is a parameter rather than fixed by the
 * endpoint: the interval defaults to {@link Interval#ONE_MIN} and can be widened as far
 * as {@link Interval#SIXTY_MIN}. Because a minute-by-minute series grows quickly, it
 * also takes an {@link OutputSize}, defaulting to {@link OutputSize#COMPACT} — the 100
 * most recent bars.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class IntraDayRequest extends ForexRequest {

    private Interval interval;
    private Function function;
    private OutputSize outputsize;

    private IntraDayRequest(Builder builder){
        super(builder);
        this.function = Function.FX_INTRADAY;
        this.outputsize =  builder.outputsize;
        this.interval = builder.interval;
    }

    /**
     * Assembles a request for intraday bars, adding the interval and output size to the
     * currency pair and data type inherited from {@link ForexRequest.Builder}.
     */
    public static class Builder extends ForexRequest.Builder<Builder>{

        Interval interval = Interval.ONE_MIN;
        OutputSize outputsize = OutputSize.COMPACT;

        /**
         * Sets how much time each bar covers. Defaults to {@link Interval#ONE_MIN}.
         * <p>
         * Only the minute-based constants apply here — {@link Interval#ONE_MIN} through
         * {@link Interval#SIXTY_MIN}. The longer constants on {@link Interval} exist for
         * other endpoints and are rejected by {@code FX_INTRADAY}.
         *
         * @param interval the sampling interval
         * @return this builder, for method chaining
         */
        public Builder interval(Interval interval){
            this.interval = interval;
            return this;
        }

        /**
         * Sets how many of the pair's intraday bars to ask for. Defaults to
         * {@link OutputSize#COMPACT}, the 100 most recent bars.
         *
         * @param outputsize the length of the returned series
         * @return this builder, for method chaining
         */
        public Builder outputSize(OutputSize outputsize){
            this.outputsize = outputsize;
            return this;
        }

        /**
         * Assembles the currency pair, interval and output size set so far into an
         * intraday request.
         *
         * @return a request for the configured pair's intraday bars
         */
        @Override
        public ForexRequest build() {
            return new IntraDayRequest(this);
        }
    }
}
