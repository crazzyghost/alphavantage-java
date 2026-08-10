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
package com.crazzyghost.alphavantage.marketstatus.response;

import com.squareup.moshi.Json;

/**
 * The trading-hours status of one financial market, as reported by a
 * {@code MARKET_STATUS} call.
 * <p>
 * Alpha Vantage reports every field of a market as plain text rather than a fixed
 * set of codes; {@link #getCurrentStatus()} in particular is a {@code String}, not
 * an enum, and the API has been observed to use {@code open} and {@code closed} as
 * its wire values.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class Market {
    @Json(name = "market_type")
    private String marketType;
    @Json(name = "region")
    private String region;
    @Json(name = "primary_exchanges")
    private String primaryExchanges;
    @Json(name = "local_open")
    private String localOpen;
    @Json(name = "local_close")
    private String localClose;
    @Json(name = "current_status")
    private String currentStatus;
    @Json(name = "notes")
    private String notes;

    /**
     * Gets the kind of market this is, such as {@code Equity}, {@code Forex} or
     * {@code Cryptocurrency}.
     *
     * @return the market type, from the API's {@code market_type} field
     */
    public String getMarketType() {
        return marketType;
    }

    /**
     * Gets the geographic region this market covers, as in {@code United States} or
     * {@code Australia}.
     *
     * @return the market region, from the API's {@code region} field
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets the exchanges this entry aggregates, as a comma-separated list such as
     * {@code NASDAQ, NYSE, CBOE, IEX}.
     *
     * @return the primary exchanges, from the API's {@code primary_exchanges} field
     */
    public String getPrimaryExchanges() {
        return primaryExchanges;
    }

    /**
     * Gets the time this market opens for trading, in its own local time.
     *
     * @return the local opening time, from the API's {@code local_open} field
     */
    public String getLocalOpen() {
        return localOpen;
    }

    /**
     * Gets the time this market closes for trading, in its own local time.
     *
     * @return the local closing time, from the API's {@code local_close} field
     */
    public String getLocalClose() {
        return localClose;
    }

    /**
     * Gets whether this market is trading right now. Observed values are
     * {@code open} and {@code closed}; see the class documentation for why this is
     * a plain string rather than an enum.
     *
     * @return the current trading status, from the API's {@code current_status}
     *         field
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Gets supplementary information about this market's status, such as an
     * upcoming holiday closure. Empty when the API has nothing to add.
     *
     * @return notes about this market, from the API's {@code notes} field
     */
    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "GlobalMarketStatus{" +
                "marketType='" + marketType + '\'' +
                ", region='" + region + '\'' +
                ", primaryExchanges='" + primaryExchanges + '\'' +
                ", localOpen='" + localOpen + '\'' +
                ", localClose='" + localClose + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
