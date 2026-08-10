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
package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * A request to {@code TIME_SERIES_DAILY}, which returns one open-high-low-close-volume
 * bar per trading day for a ticker, going back twenty years or more.
 * <p>
 * It is the only cadence in this package with a length control, because it is the only
 * one whose full history is long enough for the distinction to matter. The default
 * {@link OutputSize#COMPACT} trims the answer to the 100 most recent trading days;
 * {@link OutputSize#FULL} returns the whole series.
 * <p>
 * {@link Builder#adjusted()} switches the request over to
 * {@link Function#TIME_SERIES_DAILY_ADJUSTED}, which adds an adjusted close, a dividend
 * amount and a split coefficient to every bar. That is the only cadence whose adjusted
 * variant reports a split coefficient.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class DailyRequest extends TimeSeriesRequest{

    private OutputSize outputSize;

    private DailyRequest(Builder builder){
        super(builder);
        this.outputSize = builder.outputSize;
    }


    /**
     * Assembles a request for daily bars, adding the output size and the choice of
     * adjusted or raw prices to the ticker and data type inherited from
     * {@link TimeSeriesRequest.Builder}.
     */
    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        Function function;
        OutputSize outputSize = OutputSize.COMPACT;

        /**
         * Creates a builder pinned to {@link Function#TIME_SERIES_DAILY}, asking for
         * raw prices and the 100 most recent trading days.
         */
        public Builder(){
            super();
            this.function(Function.TIME_SERIES_DAILY);
        }

        /**
         * Switches the request to {@link Function#TIME_SERIES_DAILY_ADJUSTED}, whose
         * bars carry an adjusted close, a dividend amount and a split coefficient
         * alongside the raw prices.
         * <p>
         * This sets the endpoint only. Reading the extra fields back also needs the
         * response parser to be told the payload is adjusted, which
         * {@code TimeSeries.daily().adjusted()} does on the caller's behalf — a builder
         * used directly returns a payload the plain
         * {@link com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse}
         * parser will not read the adjusted fields out of.
         *
         * @return this builder, for method chaining
         */
        public Builder adjusted(){
            this.function(Function.TIME_SERIES_DAILY_ADJUSTED);
            return this;
        }

        /**
         * Sets how much of the ticker's daily history to ask for. Defaults to
         * {@link OutputSize#COMPACT}, the 100 most recent trading days.
         *
         * @param outputSize the length of the returned series
         * @return this builder, for method chaining
         */
        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        /**
         * Assembles the ticker, output size and adjusted-or-raw choice set so far into
         * a daily request.
         *
         * @return a request for the configured ticker's daily bars
         */
        @Override
        public DailyRequest build() {
            return new DailyRequest(this);
        }
    }
}
