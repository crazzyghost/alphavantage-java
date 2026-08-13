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

import com.crazzyghost.alphavantage.Response;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Snapshots for the tickers a bulk quote request asked about, or the message Alpha Vantage returned
 * in place of them.
 *
 * <p>The results are a flat {@link List} rather than a map keyed by ticker, and nothing guarantees
 * it holds one entry per ticker requested or holds them in the order they were added. A ticker the
 * API has no data for simply contributes no entry, so a request for five tickers can answer with
 * four snapshots and no indication of which one is missing. Match on {@link
 * RealtimeBulkQuote#getSymbol()} rather than by position, and treat a ticker absent from the list
 * as unquoted.
 *
 * <p>A response carries results or an error, never both. On success {@link #getErrorMessage()} is
 * {@code null} and {@link #getData()} holds the snapshots; on failure the message is set and the
 * list is empty rather than {@code null}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class RealtimeBulkQuoteResponse implements Response {
    private final List<RealtimeBulkQuote> data;
    private final String errorMessage;

    private RealtimeBulkQuoteResponse(List<RealtimeBulkQuote> data) {
        this.data = data;
        this.errorMessage = null;
    }

    private RealtimeBulkQuoteResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.data = new ArrayList<>();
    }

    /**
     * Builds a response from a decoded {@code REALTIME_BULK_QUOTES} payload.
     *
     * @param objectMap the response body, already decoded from JSON into a map
     * @return a response holding the parsed snapshots, or one holding an error message if the
     *     payload was empty or was not a bulk quote answer
     */
    public static RealtimeBulkQuoteResponse of(Map<String, Object> objectMap) {
        Parser<RealtimeBulkQuoteResponse> parser = new RealtimeBulkQuoteParser();
        return parser.parse(objectMap);
    }

    /**
     * Gets the snapshots the API returned, each naming its own ticker in {@link
     * RealtimeBulkQuote#getSymbol()}.
     *
     * @return the snapshots, which need not correspond one-to-one, or in order, with the tickers
     *     requested; empty, never {@code null}, on an error response
     */
    public List<RealtimeBulkQuote> getData() {
        return data;
    }

    /**
     * Gets the reason no snapshots were returned, as reported by the API. Covers API-level
     * rejections such as an exhausted rate limit or a plan without access to this premium endpoint,
     * and a response body this library could not read as a bulk quote answer.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Turns a decoded {@code REALTIME_BULK_QUOTES} payload into a {@link
     * RealtimeBulkQuoteResponse}.
     *
     * <p>The snapshots sit under the payload's third top-level key, after the endpoint name and a
     * status message, and the parser reaches for that position rather than matching the key by
     * name. An error payload has no third key, so failing to find one is itself the signal to fall
     * back to reading the first key's value as a message.
     */
    public static class RealtimeBulkQuoteParser extends Parser<RealtimeBulkQuoteResponse> {
        /**
         * Wraps a parse failure or an API error message in a response.
         *
         * @param error the message describing what went wrong
         * @return a response carrying the message, with no snapshots
         */
        @Override
        public RealtimeBulkQuoteResponse onParseError(String error) {
            return new RealtimeBulkQuoteResponse(error);
        }

        /**
         * Reads the snapshot array into a response.
         *
         * @param object the response body, already decoded from JSON into a map
         * @return a response holding the parsed snapshots, or one holding an error message if the
         *     payload was empty or carried no snapshot array
         */
        @Override
        public RealtimeBulkQuoteResponse parse(Map<String, Object> object) {
            List<String> keys = new ArrayList<>(object.keySet());
            if (keys.isEmpty()) {
                return onParseError(
                        "Empty JSON returned by the API, the symbol might not be supported.");
            }
            try {
                int dataIndex = 2;
                String dataKey = keys.get(dataIndex);
                List<RealtimeBulkQuote> data =
                        Parser.parseJSONList(object.get(dataKey), RealtimeBulkQuote.class);
                return new RealtimeBulkQuoteResponse(data);
            } catch (ClassCastException | IndexOutOfBoundsException e) {
                return onParseError(object.get(keys.get(0)).toString());
            }
        }
    }

    @Override
    public String toString() {
        return "RealtimeBulkQuoteResponse{"
                + "data="
                + data
                + ", errorMessage='"
                + errorMessage
                + '\''
                + '}';
    }
}
