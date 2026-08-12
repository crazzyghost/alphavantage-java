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
package com.crazzyghost.alphavantage.forex.response;

/**
 * The header Alpha Vantage returns above a foreign exchange series: which pair it
 * covers, how recently it was refreshed, and how it was sampled.
 * <p>
 * How much of this is filled in depends on the cadence requested, because the API only
 * echoes back the parameters that cadence accepts — an interval means nothing to a
 * monthly series, so no interval comes back. Only
 * {@link #getInformation()}, {@link #getFromSymbol()}, {@link #getToSymbol()} and
 * {@link #getLastRefreshed()} are populated for every cadence; the rest document their
 * own gaps.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class MetaData {

    private String information;
    private String fromSymbol;
    private String toSymbol;
    private String lastRefreshed;
    private String interval;
    private String outputSize;
    private String timeZone;

    /**
     * Creates a metadata header from the values a response carried.
     *
     * @param information   a description of the series
     * @param fromSymbol    the base currency code
     * @param toSymbol      the quote currency code
     * @param lastRefreshed the timestamp of the newest data point
     * @param interval      the intraday sampling interval, or {@code null} for cadences
     *                      that have none
     * @param outputSize    the length of the returned series, or {@code null} for
     *                      cadences that do not report one
     * @param timeZone      the timezone the timestamps are expressed in
     */
    public MetaData(
        String information,
        String fromSymbol,
        String toSymbol,
        String lastRefreshed,
        String interval,
        String outputSize,
        String timeZone
    ) {
        this.information = information;
        this.fromSymbol = fromSymbol;
        this.toSymbol = toSymbol;
        this.lastRefreshed = lastRefreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }

    /**
     * Creates a placeholder header with every field {@code null}.
     * <p>
     * It fills the metadata slot of a {@link ForexResponse} that carries an error
     * instead of a series, so that {@link ForexResponse#getMetaData()} never returns
     * {@code null} and callers can reach for a field without a null check first.
     *
     * @return a header holding no values
     */
    public static MetaData empty(){
        return new MetaData(null, null, null, null, null, null, null);
    }

    /**
     * Gets Alpha Vantage's own description of what the series contains, as in
     * {@code Forex Daily Prices (open, high, low, close)}. It names the cadence, which
     * makes it a useful check that the request asked for what the caller intended.
     *
     * @return the series description, or {@code null} on an error response
     */
    public String getInformation() {
        return information;
    }

    /**
     * Gets the base currency of the pair — the one being priced.
     *
     * @return the base currency code, for example {@code EUR}, or {@code null} on an
     *         error response
     */
    public String getFromSymbol() {
        return fromSymbol;
    }

    /**
     * Gets the quote currency of the pair — the one the rates are expressed in.
     *
     * @return the quote currency code, for example {@code USD}, or {@code null} on an
     *         error response
     */
    public String getToSymbol() {
        return toSymbol;
    }

    /**
     * Gets the timestamp of the newest data point in the series, which is how fresh the
     * data is rather than when the request was made.
     *
     * @return the last-refreshed timestamp, in the timezone {@link #getTimeZone()}
     *         reports, or {@code null} on an error response
     */
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    /**
     * Gets how much time each bar in the series covers.
     *
     * @return the intraday sampling interval, as in {@code 5min}; {@code null} for the
     *         daily, weekly and monthly cadences, whose bar width is fixed by the
     *         endpoint and never reported
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Gets how much of the pair's history the series spans, echoing back the requested
     * {@link com.crazzyghost.alphavantage.parameters.OutputSize}.
     *
     * @return the series length, as in {@code Full size} or {@code Compact};
     *         {@code null} for the weekly and monthly cadences, which take no output
     *         size and always return the full history
     */
    public String getOutputSize() {
        return outputSize;
    }

    /**
     * Gets the timezone that {@link #getLastRefreshed()} and every
     * {@link ForexUnit#getDate()} in the series are expressed in.
     * <p>
     * Known gap: the weekly and monthly cadences return this under a different key than
     * the response parser reads, so it comes back {@code null} for them even though
     * Alpha Vantage supplied a value. Those series are stamped {@code UTC}, as the
     * others are.
     *
     * @return the timezone, as in {@code UTC}; {@code null} on an error response, and
     *         for the weekly and monthly cadences
     */
    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetaData{");
        sb.append("information=" + information);
        sb.append("fromSymbol=" + fromSymbol);
        sb.append("toSymbol=" + toSymbol);
        sb.append("lastRefreshed=" + lastRefreshed);
        sb.append("timeZone=" + timeZone);
        if (outputSize != null) sb.append("outputSize=" + outputSize);
        if (interval != null) sb.append("interval=" + interval);
        sb.append("lastRefreshed=" + information);
        return sb.toString();
    }
}
