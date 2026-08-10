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
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * A request to {@code TIME_SERIES_INTRADAY}, which returns open-high-low-close-volume
 * bars sampled within the trading day at an {@link Interval} the caller picks.
 * <p>
 * It is the most heavily parameterised of the cadences, because a bar narrow enough to
 * sample intraday raises questions the wider cadences never face: how narrow
 * ({@link Builder#interval(Interval)}), whether the pre-market and after-hours sessions
 * count ({@link Builder#extendedHours()}), whether prices are adjusted for splits and
 * dividends ({@link Builder#adjusted()}), and which slice of history to read, since the
 * full archive reaches back to 2000 and is far too large to return at once
 * ({@link Builder#month(String)} and {@link Builder#outputSize(OutputSize)}).
 * <p>
 * The two flags default to off here, which is the opposite of what the endpoint assumes
 * when they are omitted — and they are never omitted, since both are primitive
 * {@code boolean} fields and so always reach the query string. A request built without
 * touching them therefore asks for unadjusted regular-session bars.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class IntraDayRequest extends TimeSeriesRequest {

    private Interval interval;
    private OutputSize outputSize;
    private boolean adjusted;
    private boolean extended_hours;
    private String month;

    private IntraDayRequest(Builder builder){
        super(builder);
        this.interval = builder.interval;
        this.outputSize = builder.outputSize;
        this.adjusted = builder.adjusted;
        this.extended_hours = builder.extendedHours;
        this.month = builder.month;
    }

    /**
     * Assembles a request for intraday bars, adding the sampling interval, the session
     * and adjustment flags and the history window to the ticker and data type inherited
     * from {@link TimeSeriesRequest.Builder}.
     */
    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        private Interval interval = Interval.ONE_MIN;
        private OutputSize outputSize = OutputSize.COMPACT;
        private boolean adjusted = false;
        private boolean extendedHours = false;
        private String month;

        /**
         * Creates a builder pinned to {@link Function#TIME_SERIES_INTRADAY}, sampling
         * every minute of the regular session, unadjusted, over the 100 most recent
         * bars.
         */
        public Builder(){
            super();
            this.function(Function.TIME_SERIES_INTRADAY);
        }

        /**
         * Sets how much time each bar covers. Defaults to {@link Interval#ONE_MIN}.
         * <p>
         * Only the five intraday members of {@link Interval} are accepted here —
         * {@link Interval#ONE_MIN}, {@link Interval#FIVE_MIN},
         * {@link Interval#FIFTEEN_MIN}, {@link Interval#THIRTY_MIN} and
         * {@link Interval#SIXTY_MIN}. The enum's wider members exist for the technical
         * indicator and economic indicator endpoints, and this endpoint rejects them.
         *
         * @param interval the width of each bar
         * @return this builder, for method chaining
         */
        public Builder interval(Interval interval){
            this.interval = interval;
            return this;
        }


        /**
         * Sets how much of the window to ask for. Defaults to
         * {@link OutputSize#COMPACT}, the 100 most recent bars.
         * <p>
         * What {@link OutputSize#FULL} widens to depends on
         * {@link #month(String)}: with a month set it is that month's bars, and without
         * one it is the trailing thirty days.
         *
         * @param outputSize the length of the returned series
         * @return this builder, for method chaining
         */
        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        /**
         * Asks for prices adjusted for splits and dividends, rather than the raw prices
         * quoted at the time. Unlike the daily, weekly and monthly cadences, intraday
         * adjustment is a parameter rather than a separate endpoint, and it changes the
         * price values in place rather than adding fields to each bar.
         *
         * @return this builder, for method chaining
         * @since 1.8.0
         */
        public Builder adjusted(){
            this.adjusted = true;
            return this;
        }

        /**
         * Widens the request to the pre-market and after-hours sessions as well as
         * regular trading hours, so a day's bars span 04:00 to 20:00 US Eastern rather
         * than 09:30 to 16:00.
         *
         * @return this builder, for method chaining
         * @since 1.8.0
         */
        public Builder extendedHours(){
            this.extendedHours = true;
            return this;
        }

        /**
         * Pins the request to one specific calendar month of the intraday archive,
         * which reaches back to January 2000, instead of the trailing window ending at
         * the most recent trading day.
         * <p>
         * This is how historical intraday data is read at all: without it there is no
         * way to reach past the trailing window, however large an
         * {@link #outputSize(OutputSize)} is asked for.
         *
         * @param month the month to query, formatted {@code YYYY-MM}
         * @return this builder, for method chaining
         * @since 1.8.0
         */
        public Builder month(String month){
            this.month = month;
            return this;
        }

        /**
         * Assembles the ticker, interval, flags and history window set so far into an
         * intraday request.
         *
         * @return a request for the configured ticker's intraday bars
         */
        @Override
        public IntraDayRequest build() {
            return new IntraDayRequest(this);
        }
    }


}
