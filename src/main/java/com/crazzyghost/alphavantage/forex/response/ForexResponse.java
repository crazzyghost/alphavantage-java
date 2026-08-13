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

import com.crazzyghost.alphavantage.Response;
import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A currency pair's exchange rate series, or the message Alpha Vantage returned in place of one.
 *
 * <p>All four forex cadences answer with this same type; what differs is the span each {@link
 * ForexUnit} covers and how much of the {@link MetaData} is filled in.
 *
 * <p>A response carries results or an error, never both. On success {@link #getErrorMessage()} is
 * {@code null}, {@link #getMetaData()} describes the series and {@link #getForexUnits()} holds its
 * bars. On failure the message is set, the bar list is empty, and the metadata is the {@link
 * MetaData#empty()} placeholder whose fields are all {@code null} — so checking the error message
 * is what tells the two apart, not checking for nulls.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class ForexResponse implements Response {

    private MetaData metaData;
    private List<ForexUnit> forexUnits;
    private String errorMessage;

    private ForexResponse(MetaData metaData, List<ForexUnit> forexUnits) {
        this.metaData = metaData;
        this.forexUnits = forexUnits;
        this.errorMessage = null;
    }

    private ForexResponse(String errorMessage) {
        this.metaData = MetaData.empty();
        this.forexUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the reason no series was returned, as reported by the API. Covers both API-level
     * rejections, such as an unsupported currency pair or an exhausted rate limit, and a response
     * body this library could not read as a forex series.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets the header describing the series: its currency pair, freshness and sampling.
     *
     * @return the series metadata; on an error response, a {@link MetaData#empty()} placeholder
     *     rather than {@code null}
     */
    public MetaData getMetaData() {
        return metaData;
    }

    /**
     * Gets the exchange rate bars making up the series, each carrying its own timestamp in {@link
     * ForexUnit#getDate()}.
     *
     * @return the bars; empty, never {@code null}, on an error response
     */
    public List<ForexUnit> getForexUnits() {
        return forexUnits;
    }

    /**
     * Builds a response from a decoded forex payload, whichever cadence produced it.
     *
     * @param stringObjectMap the response body, already decoded from JSON into a map
     * @return a response holding the parsed series, or one holding an error message if the payload
     *     was empty or was not a forex series
     */
    public static ForexResponse of(Map<String, Object> stringObjectMap) {
        Parser<ForexResponse> parser = new ForexParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Turns a decoded forex payload into a {@link ForexResponse}.
     *
     * <p>The four cadences number their metadata keys differently — only intraday reports an
     * interval, and the weekly and monthly headers are shorter — so the parser reads the metadata
     * positionally and falls back to the shorter numbering when the longer one does not match.
     */
    public static class ForexParser extends DefaultParser<ForexResponse> {

        /**
         * Wraps a parse failure or an API error message in a response.
         *
         * @param error the message describing what went wrong
         * @return a response carrying the message, with no bars and empty metadata
         */
        @Override
        public ForexResponse onParseError(String error) {
            return new ForexResponse(error);
        }

        /**
         * Reads the metadata header and the rate bars into a response.
         *
         * @param metaDataMap the payload's metadata block, keyed by the API's numbered field names
         * @param units the payload's time series block, keyed by timestamp
         * @return a response holding the parsed metadata and bars
         */
        @Override
        public ForexResponse parse(
                Map<String, String> metaDataMap, Map<String, Map<String, String>> units) {

            String information = metaDataMap.get("1. Information");
            String fromSymbol = metaDataMap.get("2. From Symbol");
            String toSymbol = metaDataMap.get("3. To Symbol");
            String lastRefreshed = metaDataMap.getOrDefault("4. Last Refreshed", null);
            String interval = metaDataMap.getOrDefault("5. Interval", null);
            String outputSize = metaDataMap.getOrDefault("6. Output Size", null);
            String timeZone = metaDataMap.getOrDefault("7. Time Zone", null);

            if (metaDataMap.get("4. Last Refreshed") == null) {
                outputSize = metaDataMap.get("4. Output Size");
                lastRefreshed = metaDataMap.get("5. Last Refreshed");
                timeZone = metaDataMap.get("6. Time Zone");
            }

            MetaData metaData =
                    new MetaData(
                            information,
                            fromSymbol,
                            toSymbol,
                            lastRefreshed,
                            interval,
                            outputSize,
                            timeZone);

            List<ForexUnit> forexUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : units.entrySet()) {
                ForexUnit.Builder forexUnit = new ForexUnit.Builder();
                Map<String, String> m = e.getValue();
                forexUnit.date(e.getKey());
                forexUnit.open(Double.parseDouble(m.get("1. open")));
                forexUnit.high(Double.parseDouble(m.get("2. high")));
                forexUnit.low(Double.parseDouble(m.get("3. low")));
                forexUnit.close(Double.parseDouble(m.get("4. close")));
                forexUnits.add(forexUnit.build());
            }
            return new ForexResponse(metaData, forexUnits);
        }
    }

    @Override
    public String toString() {
        return "ForexResponse{"
                + "metaData="
                + metaData
                + ", forexUnits="
                + forexUnits.size()
                + ", errorMessage='"
                + errorMessage
                + '\''
                + '}';
    }
}
