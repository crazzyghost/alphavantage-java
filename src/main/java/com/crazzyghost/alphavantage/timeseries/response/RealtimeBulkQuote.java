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

    public String getSymbol() {
        return symbol;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Double getOpen() {
        return open;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getClose() {
        return close;
    }

    public Long getVolume() {
        return volume;
    }

    public Double getPreviousClose() {
        return previousClose;
    }

    public Double getChange() {
        return change;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public Double getExtendedHoursQuote() {
        return extendedHoursQuote;
    }

    public Double getExtendedHoursChange() {
        return extendedHoursChange;
    }

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
