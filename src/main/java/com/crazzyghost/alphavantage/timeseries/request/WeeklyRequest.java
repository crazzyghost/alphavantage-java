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

/**
 * A request to {@code TIME_SERIES_WEEKLY}, which returns one
 * open-high-low-close-volume bar per trading week for a ticker.
 * <p>
 * Each bar is stamped with the last trading day of the week it covers, and its close is
 * that day's close rather than an average across the week. Volume is the week's total.
 * <p>
 * There is no output size to set: the endpoint always returns the ticker's entire
 * weekly history, which spans twenty years in a few hundred bars rather than the few
 * thousand the same span costs daily.
 * <p>
 * {@link Builder#adjusted()} switches the request over to
 * {@link Function#TIME_SERIES_WEEKLY_ADJUSTED}, which adds an adjusted close and a
 * dividend amount to every bar. Unlike the daily variant it reports no split
 * coefficient.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class WeeklyRequest extends TimeSeriesRequest {

    /**
     * Copies the ticker, data type and endpoint out of a builder.
     *
     * @param builder the builder holding the parameters to copy
     */
   public WeeklyRequest(Builder builder){
        super(builder);
    }

    /**
     * Assembles a request for weekly bars, adding the choice of adjusted or raw prices
     * to the ticker and data type inherited from {@link TimeSeriesRequest.Builder}.
     */
    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        /**
         * Creates a builder pinned to {@link Function#TIME_SERIES_WEEKLY}, asking for
         * raw prices.
         */
        public Builder(){
            super();
            this.function(Function.TIME_SERIES_WEEKLY);
        }

        /**
         * Switches the request to {@link Function#TIME_SERIES_WEEKLY_ADJUSTED}, whose
         * bars carry an adjusted close and a dividend amount alongside the raw prices.
         * <p>
         * This sets the endpoint only. Reading the extra fields back also needs the
         * response parser to be told the payload is adjusted, which
         * {@code TimeSeries.weekly().adjusted()} does on the caller's behalf — a
         * builder used directly returns a payload the plain
         * {@link com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse}
         * parser will not read the adjusted fields out of.
         *
         * @return this builder, for method chaining
         */
        public Builder adjusted(){
            this.function(Function.TIME_SERIES_WEEKLY_ADJUSTED);
            return this;
        }

        /**
         * Assembles the ticker and adjusted-or-raw choice set so far into a weekly
         * request.
         *
         * @return a request for the configured ticker's weekly bars
         */
        @Override
        public WeeklyRequest build() {
            return new WeeklyRequest(this);
        }
    }
}
