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
package com.crazzyghost.alphavantage.search.response;

import com.squareup.moshi.Json;

/**
 * One instrument matched by a {@code SYMBOL_SEARCH} keyword, together with the
 * trading-venue details and the relevance score that decided its rank.
 * <p>
 * Alpha Vantage returns every field of a match as a string, and this class keeps them
 * that way — nothing here is parsed into a number or a time. Callers that need
 * {@link #getMatchScore()} or {@link #getMarketOpen()} as a typed value convert it
 * themselves.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class Match {
    @Json(name = "1. symbol")
    private String symbol;
    @Json(name = "2. name")
    private String name;
    @Json(name = "3. type")
    private String type;
    @Json(name = "4. region")
    private String region;
    @Json(name = "5. marketOpen")
    private String marketOpen;
    @Json(name = "6. marketClose")
    private String marketClose;
    @Json(name = "7. timezone")
    private String timezone;
    @Json(name = "8. currency")
    private String currency;
    @Json(name = "9. matchScore")
    private String matchScore;

    /**
     * Gets the ticker symbol to pass to the other endpoints when requesting data for
     * this instrument. Non-US listings carry an exchange suffix, as in
     * {@code TSCO.LON}.
     *
     * @return the ticker symbol, from the API's {@code 1. symbol} field
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the full name of the instrument, as in {@code Tesco PLC}.
     *
     * @return the instrument name, from the API's {@code 2. name} field
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the kind of instrument this is, such as {@code Equity}, {@code ETF} or
     * {@code Mutual Fund}.
     *
     * @return the instrument type, from the API's {@code 3. type} field
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the market the instrument is listed on, named as a place rather than an
     * exchange code, as in {@code United States} or {@code United Kingdom}.
     *
     * @return the listing region, from the API's {@code 4. region} field
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets the time the listing venue opens, as {@code HH:mm} in the timezone reported
     * by {@link #getTimezone()}.
     *
     * @return the local opening time, from the API's {@code 5. marketOpen} field
     */
    public String getMarketOpen() {
        return marketOpen;
    }

    /**
     * Gets the time the listing venue closes, as {@code HH:mm} in the timezone reported
     * by {@link #getTimezone()}.
     *
     * @return the local closing time, from the API's {@code 6. marketClose} field
     */
    public String getMarketClose() {
        return marketClose;
    }

    /**
     * Gets the timezone the opening and closing times are expressed in, as a UTC offset
     * such as {@code UTC-04} rather than a region name.
     *
     * @return the venue timezone, from the API's {@code 7. timezone} field
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Gets the currency the instrument trades in, as a three-letter code such as
     * {@code GBP}.
     *
     * @return the trading currency, from the API's {@code 8. currency} field
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Gets how closely this instrument matched the searched keywords. The score runs
     * from {@code 0.0000} to {@code 1.0000}, and is what orders the matches within a
     * response.
     *
     * @return the relevance score, from the API's {@code 9. matchScore} field
     */
    public String getMatchScore() {
        return matchScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", marketOpen='" + marketOpen + '\'' +
                ", marketClose='" + marketClose + '\'' +
                ", timezone='" + timezone + '\'' +
                ", currency='" + currency + '\'' +
                ", matchScore='" + matchScore + '\'' +
                '}';
    }
}
