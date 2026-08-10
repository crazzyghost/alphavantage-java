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
 * A request to {@code TIME_SERIES_MONTHLY}, which returns one
 * open-high-low-close-volume bar per month for a ticker.
 * <p>
 * This is the widest cadence the library offers, and the one that reduces a ticker's
 * whole history to a few hundred points — each bar stamped with the last trading day of
 * the month it covers, closing at that day's close and totalling the month's volume.
 * <p>
 * Like the weekly cadence and unlike the daily one, it takes no output size: the
 * endpoint always returns the full history.
 * <p>
 * {@link Builder#adjusted()} switches the request over to
 * {@link Function#TIME_SERIES_MONTHLY_ADJUSTED}, which adds an adjusted close and a
 * dividend amount to every bar. Since the dividend is aggregated per bar, a month
 * paying more than one dividend reports their sum rather than each separately.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class MonthlyRequest extends TimeSeriesRequest {


    /**
     * Copies the ticker, data type and endpoint out of a builder.
     *
     * @param builder the builder holding the parameters to copy
     */
    public MonthlyRequest(Builder builder){
        super(builder);
    }


    /**
     * Assembles a request for monthly bars, adding the choice of adjusted or raw prices
     * to the ticker and data type inherited from {@link TimeSeriesRequest.Builder}.
     */
    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        /**
         * Creates a builder pinned to {@link Function#TIME_SERIES_MONTHLY}, asking for
         * raw prices.
         */
        public Builder(){
            super();
            this.function(Function.TIME_SERIES_MONTHLY);
        }

        /**
         * Switches the request to {@link Function#TIME_SERIES_MONTHLY_ADJUSTED}, whose
         * bars carry an adjusted close and a dividend amount alongside the raw prices.
         * <p>
         * This sets the endpoint only. Reading the extra fields back also needs the
         * response parser to be told the payload is adjusted, which
         * {@code TimeSeries.monthly().adjusted()} does on the caller's behalf — a
         * builder used directly returns a payload the plain
         * {@link com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse}
         * parser will not read the adjusted fields out of.
         *
         * @return this builder, for method chaining
         */
        public Builder adjusted(){
            this.function(Function.TIME_SERIES_MONTHLY_ADJUSTED);
            return this;
        }

        /**
         * Assembles the ticker and adjusted-or-raw choice set so far into a monthly
         * request.
         *
         * @return a request for the configured ticker's monthly bars
         */
        @Override
        public MonthlyRequest build() {
            return new MonthlyRequest(this);
        }
    }
}
