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
package com.crazzyghost.alphavantage.timeseries.response;

/**
 * The header Alpha Vantage returns above a stock time series: which ticker it covers,
 * how recently it was refreshed, and how it was sampled.
 * <p>
 * How much of this is filled in depends on the cadence requested, because the API only
 * echoes back the parameters that cadence accepts — an interval means nothing to a
 * monthly series, so no interval comes back. Only {@link #getInformation()},
 * {@link #getSymbol()} and {@link #getLastRefreshed()} are populated for every cadence;
 * the rest document their own gaps.
 * <p>
 * A {@link TimeSeriesResponse} that carries an error rather than a series still carries
 * a header, the {@link #empty()} placeholder, so reaching for a field never needs a
 * null check on the header itself.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class MetaData {

    private String information;
    private String symbol;
    private String lastRefreshed;
    private String timeZone;
    private String interval;
    private String outputSize;

    /**
     * Creates a metadata header from the values a response carried.
     * <p>
     * The parameters are not in the same order as the fields they set: the timezone
     * comes last here, after the two cadence-dependent values.
     *
     * @param information   a description of the series
     * @param symbol        the ticker the series covers
     * @param lastRefreshed the timestamp of the newest data point
     * @param interval      the intraday sampling interval, or {@code null} for cadences
     *                      that have none
     * @param outputSize    the length of the returned series, or {@code null} for
     *                      cadences that do not report one
     * @param timeZone      the timezone the timestamps are expressed in
     */
    public MetaData(String information, String symbol, String lastRefreshed, String interval, String outputSize, String timeZone) {
        this.information = information;
        this.symbol = symbol;
        this.lastRefreshed = lastRefreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }

    /**
     * Creates a placeholder header with every field {@code null}.
     * <p>
     * It fills the metadata slot of a {@link TimeSeriesResponse} that carries an error
     * instead of a series, so that {@link TimeSeriesResponse#getMetaData()} never
     * returns {@code null} and callers can reach for a field without a null check
     * first.
     *
     * @return a header holding no values
     */
    public static MetaData empty(){
        return new MetaData(null, null, null, null, null, null);
    }

    /**
     * Gets Alpha Vantage's own description of what the series contains, as in
     * {@code Daily Prices (open, high, low, close) and Volumes}. It names the cadence
     * and says whether the prices are adjusted, which makes it a useful check that the
     * request asked for what the caller intended.
     *
     * @return the series description, or {@code null} on an error response
     */
    public String getInformation() {
        return information;
    }

    /**
     * Gets the ticker the series covers, echoed back as the API resolved it rather than
     * as the request spelled it.
     *
     * @return the ticker symbol, for example {@code IBM}, or {@code null} on an error
     *         response
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the timestamp of the newest data point in the series, which is how fresh the
     * data is rather than when the request was made.
     *
     * @return the last-refreshed timestamp, or {@code null} on an error response
     */
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    /**
     * Gets the timezone the series timestamps are expressed in, which for these
     * endpoints is US Eastern.
     * <p>
     * Only the intraday cadence populates this in practice. The parser reads a
     * differently spelled metadata key for the daily, weekly and monthly cadences than
     * those endpoints actually return, so their headers leave it unset.
     *
     * @return the timezone name, as in {@code US/Eastern}; {@code null} on an error
     *         response and on every cadence but intraday
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Gets how much of the ticker's history the series covers, echoing back the output
     * size the request asked for.
     *
     * @return the output size, as in {@code Compact}; {@code null} for the weekly and
     *         monthly cadences, which always return a full history and so report no
     *         size, and on an error response
     */
    public String getOutputSize() {
        return outputSize;
    }

    /**
     * Gets how much time each bar in the series covers.
     *
     * @return the intraday sampling interval, as in {@code 5min}; {@code null} for the
     *         daily, weekly and monthly cadences, whose bar width is fixed by the
     *         endpoint and never reported, and on an error response
     */
    public String getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetaData{");
        sb.append("information=" + information);
        sb.append("symbol=" + symbol);
        sb.append("lastRefreshed=" + lastRefreshed);
        sb.append("timeZone=" + timeZone);
        if(interval != null) sb.append("interval=" + interval);
        if(outputSize != null) sb.append("outputSize=" + outputSize);
        sb.append("}");
        return sb.toString();
    }
}
