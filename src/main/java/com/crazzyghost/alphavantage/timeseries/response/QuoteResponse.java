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

import com.crazzyghost.alphavantage.Response;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A single ticker's latest quote, or the message Alpha Vantage returned in place of one.
 *
 * <p>This is the answer to a {@link com.crazzyghost.alphavantage.timeseries.request.QuoteRequest} —
 * one snapshot rather than a series, so it is a flat set of fields with no metadata header and no
 * unit list. The prices describe the most recent trading day, which is not necessarily today:
 * {@link #getLatestTradingDay()} says which day they belong to, and over a weekend or holiday it
 * lags.
 *
 * <p>A response carries a quote or an error, never both. On failure {@link #getErrorMessage()} is
 * set and every price is left at {@code 0.0}, which is indistinguishable from a genuine zero — so
 * the error message is what tells the two apart.
 *
 * <p>For the same snapshot across many tickers in one call, see {@link RealtimeBulkQuoteResponse}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.3.0
 */
public class QuoteResponse implements Response {
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double price;
    private double volume;
    private String latestTradingDay;
    private double previousClose;
    private double change;
    private double changePercent;

    private String errorMessage;

    /**
     * Creates a successful quote from the values a response carried.
     *
     * @param symbol the ticker the quote covers
     * @param open the latest trading day's opening price
     * @param high the latest trading day's highest price
     * @param low the latest trading day's lowest price
     * @param price the latest traded price
     * @param volume the latest trading day's volume, in shares
     * @param latestTradingDay the day the quote covers, formatted {@code yyyy-MM-dd}
     * @param previousClose the previous trading day's closing price
     * @param change the move from the previous close, in the listing currency
     * @param changePercent the move from the previous close, in percentage points
     */
    public QuoteResponse(
            String symbol,
            double open,
            double high,
            double low,
            double price,
            double volume,
            String latestTradingDay,
            double previousClose,
            double change,
            double changePercent) {
        this.symbol = symbol;
        this.open = open;
        this.high = high;
        this.low = low;
        this.price = price;
        this.volume = volume;
        this.latestTradingDay = latestTradingDay;
        this.previousClose = previousClose;
        this.change = change;
        this.changePercent = changePercent;
    }

    /**
     * Gets the ticker this quote covers, echoed back as the API resolved it rather than as the
     * request spelled it.
     *
     * @return the ticker symbol, for example {@code IBM}, or {@code null} on an error response
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Gets the price at which the latest trading day opened.
     *
     * @return the opening price, in the ticker's listing currency
     */
    public double getOpen() {
        return this.open;
    }

    /**
     * Gets the highest price reached during the latest trading day.
     *
     * @return the high price, in the ticker's listing currency
     */
    public double getHigh() {
        return this.high;
    }

    /**
     * Gets the lowest price reached during the latest trading day.
     *
     * @return the low price, in the ticker's listing currency
     */
    public double getLow() {
        return this.low;
    }

    /**
     * Gets the latest traded price, which is what the quote is normally read for. While the market
     * is open this is the current price; once it closes, the day's closing price.
     *
     * @return the latest price, in the ticker's listing currency
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Gets how many shares changed hands over the latest trading day. The count is a whole number
     * despite the {@code double} type — unlike {@link StockUnit#getVolume()}, which types the same
     * quantity as a {@code long}.
     *
     * @return the traded volume, in shares
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     * Gets the day the quote's prices belong to, which lags the calendar day over weekends,
     * holidays and before the session opens.
     *
     * @return the trading day, formatted {@code yyyy-MM-dd}, or {@code null} on an error response
     */
    public String getLatestTradingDay() {
        return this.latestTradingDay;
    }

    /**
     * Gets the price at which the trading day before the latest one closed. It is the baseline both
     * {@link #getChange()} and {@link #getChangePercent()} are measured against.
     *
     * @return the previous closing price, in the ticker's listing currency
     */
    public double getPreviousClose() {
        return this.previousClose;
    }

    /**
     * Gets how far the latest price has moved from the previous close, signed so that a negative
     * value is a fall.
     *
     * @return the change, in the ticker's listing currency
     */
    public double getChange() {
        return this.change;
    }

    /**
     * Gets the same move as {@link #getChange()} expressed against the previous close, in
     * percentage points rather than as a fraction: a value of {@code 1.5} means the price rose
     * 1.5%. The API sends this with a trailing percent sign, which the parser strips before
     * converting.
     *
     * @return the change, in percentage points
     */
    public double getChangePercent() {
        return this.changePercent;
    }

    /**
     * Gets the reason no quote was returned, as reported by the API. Covers both API-level
     * rejections, such as an unknown ticker or an exhausted rate limit, and a response body this
     * library could not read as a quote.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Creates a failed quote carrying only a message, with every price left at {@code 0.0} and the
     * ticker and trading day left {@code null}.
     *
     * @param errorMessage the message describing what went wrong
     */
    public QuoteResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Builds a response from a decoded {@code GLOBAL_QUOTE} payload.
     *
     * @param stringObjectMap the response body, already decoded from JSON into a map
     * @return a response holding the parsed quote, or one holding an error message if the payload
     *     was empty or was not a quote
     */
    public static QuoteResponse of(Map<String, Object> stringObjectMap) {
        Parser<QuoteResponse> parser = new QuoteParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Turns a decoded {@code GLOBAL_QUOTE} payload into a {@link QuoteResponse}.
     *
     * <p>The quote arrives nested one level down, under a single top-level key, and its own fields
     * are numbered rather than named. The parser reads whatever the first top-level key holds
     * rather than matching that key by name, so an error payload — whose one key holds a message
     * string instead of an object — falls out as a {@code ClassCastException} and is turned into an
     * error response.
     */
    public static class QuoteParser extends Parser<QuoteResponse> {

        /**
         * Reads the nested quote block into a response.
         *
         * @param stringObjectMap the response body, already decoded from JSON into a map
         * @return a response holding the parsed quote, or one holding an error message if the
         *     payload was empty or held a message rather than a quote
         */
        @SuppressWarnings("unchecked")
        @Override
        public QuoteResponse parse(Map<String, Object> stringObjectMap) {
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
            if (keys.isEmpty()) {
                return onParseError(
                        "Empty JSON returned by the API, the symbol might not be supported.");
            } else {

                Map<String, String> data;
                try {
                    data = (Map<String, String>) stringObjectMap.get(keys.get(0));
                } catch (ClassCastException e) {
                    return onParseError((String) stringObjectMap.get(keys.get(0)));
                }

                String changePercentage = data.get("10. change percent");
                changePercentage = changePercentage.substring(0, changePercentage.length() - 1);
                return new QuoteResponse(
                        data.get("01. symbol"),
                        Double.parseDouble(data.get("02. open")),
                        Double.parseDouble(data.get("03. high")),
                        Double.parseDouble(data.get("04. low")),
                        Double.parseDouble(data.get("05. price")),
                        Double.parseDouble(data.get("06. volume")),
                        data.get("07. latest trading day"),
                        Double.parseDouble(data.get("08. previous close")),
                        Double.parseDouble(data.get("09. change")),
                        Double.parseDouble(changePercentage));
            }
        }

        /**
         * Wraps a parse failure or an API error message in a response.
         *
         * @param error the message describing what went wrong
         * @return a response carrying the message, with no quote values
         */
        @Override
        public QuoteResponse onParseError(String error) {
            return new QuoteResponse(error);
        }
    }
}
