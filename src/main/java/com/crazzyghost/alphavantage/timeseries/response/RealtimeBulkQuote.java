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
package com.crazzyghost.alphavantage.timeseries.response;

import com.squareup.moshi.Json;


/**
 * One ticker's snapshot within a bulk quote answer.
 * <p>
 * It reports the same latest trading day as {@link QuoteResponse} does for a single
 * ticker, and adds what that endpoint has no equivalent of: an extended-hours quote and
 * the move it represents, covering the pre-market and after-hours sessions.
 * <p>
 * Every numeric field is a boxed type, which matters. Alpha Vantage does not pad its
 * answer out to the tickers that were asked for, and a snapshot it has only partial
 * data for comes back with the missing fields absent rather than zeroed — so any of
 * these getters can return {@code null}, and the extended-hours three routinely do
 * outside those sessions. Read them defensively rather than unboxing straight into a
 * primitive.
 * <p>
 * Unlike the fields of the older response types, these are mapped by name from the
 * payload's snake_case keys rather than read positionally out of a numbered map.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class RealtimeBulkQuote {
    @Json(name = "symbol")
    private String symbol;
    @Json(name = "timestamp")
    private String timestamp;
    @Json(name = "open")
    private Double open;
    @Json(name = "high")
    private Double high;
    @Json(name = "low")
    private Double low;
    @Json(name = "close")
    private Double close;
    @Json(name = "volume")
    private Long volume;
    @Json(name = "previous_close")
    private Double previousClose;
    @Json(name = "change")
    private Double change;
    @Json(name = "change_percent")
    private Double changePercent;
    @Json(name = "extended_hours_quote")
    private Double extendedHoursQuote;
    @Json(name = "extended_hours_change")
    private Double extendedHoursChange;
    @Json(name = "extended_hours_change_percent")
    private Double extendedHoursChangePercent;

    /**
     * Gets the ticker this snapshot covers. Since
     * {@link RealtimeBulkQuoteResponse#getData()} is a list rather than a map, this is
     * how a snapshot is matched back to the ticker that was asked for.
     *
     * @return the ticker symbol, for example {@code IBM}
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the moment this snapshot was taken, which is the freshness of the quote
     * rather than the day it covers.
     *
     * @return the timestamp, as the API formatted it
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the price at which the latest trading day opened.
     *
     * @return the opening price, in the ticker's listing currency, or {@code null} if
     *         the API did not report one
     */
    public Double getOpen() {
        return open;
    }

    /**
     * Gets the highest price reached during the latest trading day.
     *
     * @return the high price, in the ticker's listing currency, or {@code null} if the
     *         API did not report one
     */
    public Double getHigh() {
        return high;
    }

    /**
     * Gets the lowest price reached during the latest trading day.
     *
     * @return the low price, in the ticker's listing currency, or {@code null} if the
     *         API did not report one
     */
    public Double getLow() {
        return low;
    }

    /**
     * Gets the latest regular-session price. While the market is open this moves with
     * the last trade; once it closes, it is the day's closing price and stays put even
     * as {@link #getExtendedHoursQuote()} carries on moving.
     *
     * @return the closing price, in the ticker's listing currency, or {@code null} if
     *         the API did not report one
     */
    public Double getClose() {
        return close;
    }

    /**
     * Gets how many shares changed hands over the latest trading day.
     *
     * @return the traded volume, in shares, or {@code null} if the API did not report
     *         one
     */
    public Long getVolume() {
        return volume;
    }

    /**
     * Gets the price at which the trading day before the latest one closed. It is the
     * baseline both {@link #getChange()} and {@link #getChangePercent()} are measured
     * against.
     *
     * @return the previous closing price, in the ticker's listing currency, or
     *         {@code null} if the API did not report one
     */
    public Double getPreviousClose() {
        return previousClose;
    }

    /**
     * Gets how far {@link #getClose()} has moved from the previous close, signed so
     * that a negative value is a fall.
     *
     * @return the change, in the ticker's listing currency, or {@code null} if the API
     *         did not report one
     */
    public Double getChange() {
        return change;
    }

    /**
     * Gets the same move as {@link #getChange()} expressed against the previous close,
     * in percentage points rather than as a fraction: a value of {@code 1.5} means the
     * price rose 1.5%.
     *
     * @return the change, in percentage points, or {@code null} if the API did not
     *         report one
     */
    public Double getChangePercent() {
        return changePercent;
    }

    /**
     * Gets the latest price from the pre-market or after-hours session, which is what
     * a ticker is trading at outside regular hours while {@link #getClose()} sits
     * still.
     *
     * @return the extended-hours price, in the ticker's listing currency, or
     *         {@code null} when no extended-hours session is in progress or the API did
     *         not report one
     */
    public Double getExtendedHoursQuote() {
        return extendedHoursQuote;
    }

    /**
     * Gets how far the extended-hours price has moved from the regular session's close,
     * signed so that a negative value is a fall. This is measured against
     * {@link #getClose()}, not the previous close that {@link #getChange()} uses.
     *
     * @return the extended-hours change, in the ticker's listing currency, or
     *         {@code null} when no extended-hours session is in progress or the API did
     *         not report one
     */
    public Double getExtendedHoursChange() {
        return extendedHoursChange;
    }

    /**
     * Gets the same move as {@link #getExtendedHoursChange()} expressed against the
     * regular session's close, in percentage points rather than as a fraction.
     *
     * @return the extended-hours change, in percentage points, or {@code null} when no
     *         extended-hours session is in progress or the API did not report one
     */
    public Double getExtendedHoursChangePercent() {
        return extendedHoursChangePercent;
    }

    @Override
    public String toString() {
        return "RealtimeBulkQuote{" +
                "symbol='" + symbol + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", previousClose=" + previousClose +
                ", change=" + change +
                ", changePercent=" + changePercent +
                ", extendedHoursQuote=" + extendedHoursQuote +
                ", extendedHoursChange=" + extendedHoursChange +
                ", extendedHoursChangePercent=" + extendedHoursChangePercent +
                '}';
    }
}
