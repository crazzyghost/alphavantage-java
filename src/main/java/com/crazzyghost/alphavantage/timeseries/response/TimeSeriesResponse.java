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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * A ticker's price series, or the message Alpha Vantage returned in place of one.
 * <p>
 * All four cadences answer with this same type, adjusted or not; what differs is the
 * span each {@link StockUnit} covers, which of its fields carry values, and how much of
 * the {@link MetaData} is filled in. The two snapshot endpoints in this package do not
 * — they answer with {@link QuoteResponse} and {@link RealtimeBulkQuoteResponse}.
 * <p>
 * A response carries results or an error, never both. On success
 * {@link #getErrorMessage()} is {@code null}, {@link #getMetaData()} describes the
 * series and {@link #getStockUnits()} holds its bars. On failure the message is set,
 * the bar list is empty, and the metadata is the {@link MetaData#empty()} placeholder
 * whose fields are all {@code null} — so checking the error message is what tells the
 * two apart, not checking for nulls.
 * <p>
 * The bars come out of the payload in the order its map iterated, which is not
 * guaranteed to be chronological. Sort on {@link StockUnit#getDate()} if order matters.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class TimeSeriesResponse {

    private final MetaData metaData;
    private final List<StockUnit> stockUnits;
    private final String errorMessage;

    private TimeSeriesResponse(MetaData metaData, List<StockUnit> stockUnits) {
        this.metaData = metaData;
        this.stockUnits = stockUnits;
        this.errorMessage = null;
    }

    private TimeSeriesResponse(String errorMessage){
        this.errorMessage = errorMessage;
        this.stockUnits = new ArrayList<>();
        this.metaData = MetaData.empty();
    }

    /**
     * Builds a response from a decoded stock time series payload, whichever cadence
     * produced it.
     * <p>
     * The adjusted flag has to be passed in rather than inferred, because the adjusted
     * and unadjusted payloads number their per-bar keys differently and nothing in the
     * payload itself says which numbering is in use. Passing {@code false} for an
     * adjusted payload reads its adjusted close as a volume; passing {@code true} for
     * an unadjusted one fails to find keys that are not there.
     *
     * @param stringObjectMap the response body, already decoded from JSON into a map
     * @param adjusted        whether the payload came from an adjusted cadence, and so
     *                        carries an adjusted close, a dividend and possibly a split
     *                        coefficient on each bar
     * @return a response holding the parsed series, or one holding an error message if
     *         the payload was empty or was not a stock time series
     */
    public static TimeSeriesResponse of(Map<String, Object> stringObjectMap, boolean adjusted){
        Parser<TimeSeriesResponse> parser = new TimeSeriesParser(adjusted);
        return parser.parse(stringObjectMap);
    }

    /**
     * Gets the reason no series was returned, as reported by the API. Covers both
     * API-level rejections, such as an unknown ticker or an exhausted rate limit, and a
     * response body this library could not read as a stock time series.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Gets the header describing the series: its ticker, freshness and sampling.
     *
     * @return the series metadata; on an error response, a {@link MetaData#empty()}
     *         placeholder rather than {@code null}
     */
    public MetaData getMetaData() {
        return metaData;
    }

    /**
     * Gets the price bars making up the series, each carrying its own timestamp in
     * {@link StockUnit#getDate()}.
     *
     * @return the bars, in the payload's own order; empty, never {@code null}, on an
     *         error response
     */
    public List<StockUnit> getStockUnits() {
        return stockUnits;
    }

    /**
     * Turns a decoded stock time series payload into a {@link TimeSeriesResponse}.
     * <p>
     * The cadences number their metadata keys differently — only intraday reports an
     * interval, and the weekly and monthly headers are shorter — so the parser reads
     * the header positionally, deciding which numbering is in use by which of the
     * fourth-position keys is present.
     * <p>
     * Per-bar keys are numbered by cadence too, which is what the adjusted flag
     * resolves: in an unadjusted payload the fifth key is the volume, while in an
     * adjusted one it is the adjusted close and the volume moves to sixth.
     */
    public static class TimeSeriesParser extends DefaultParser<TimeSeriesResponse> {

        private boolean adjusted;

        /**
         * Creates a parser for one numbering of the per-bar keys.
         *
         * @param adjusted whether the payloads this parser will read come from an
         *                 adjusted cadence
         */
        public TimeSeriesParser(boolean adjusted){
            this.adjusted = adjusted;
        }

        /**
         * Reads the metadata header and the price bars into a response.
         *
         * @param metaDataMap the payload's metadata block, keyed by the API's numbered
         *                    field names
         * @param dataMap     the payload's time series block, keyed by timestamp
         * @return a response holding the parsed metadata and bars
         */
        @Override
        public TimeSeriesResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> dataMap) {

            MetaData metaData;
            String information = metaDataMap.get("1. Information");
            String symbol = metaDataMap.get("2. Symbol");
            String lastRefreshed = metaDataMap.get("3. Last Refreshed");
            String interval = null;
            String outputSize = null;
            String timeZone;

            if(metaDataMap.get("4. Interval") == null && metaDataMap.get("4. Output Size") == null){
                timeZone = metaDataMap.get("4. timeZone");
            }else if(metaDataMap.get("4. Interval") == null && metaDataMap.get("4. Output Size") != null){
                outputSize = metaDataMap.get("4. Output Size");
                timeZone = metaDataMap.get("5. Output Size");
            }else {
                interval = metaDataMap.get("4. Interval");
                outputSize = metaDataMap.get("5. Output Size");
                timeZone = metaDataMap.get("6. Time Zone");
            }

            metaData = new MetaData(information, symbol, lastRefreshed, interval, outputSize, timeZone);

            List<StockUnit> stockUnits =  new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : dataMap.entrySet()) {
                Map<String, String> m = e.getValue();
                StockUnit.Builder stockUnit = new StockUnit.Builder();
                stockUnit.time(e.getKey());
                stockUnit.open(Double.parseDouble(m.get("1. open")));
                stockUnit.high(Double.parseDouble(m.get("2. high")));
                stockUnit.low(Double.parseDouble(m.get("3. low")));
                stockUnit.close(Double.parseDouble(m.get("4. close")));
                if (!adjusted) {
                    stockUnit.volume(Long.parseLong(m.get("5. volume")));
                } else {
                    stockUnit.adjustedClose(Double.parseDouble(m.get("5. adjusted close")));
                    stockUnit.volume(Long.parseLong(m.get("6. volume")));
                    stockUnit.dividendAmount(Double.parseDouble(m.get("7. dividend amount")));
                    if (m.get("8. split coefficient") != null){
                        stockUnit.splitCoefficient(Double.parseDouble(m.get("8. split coefficient")));
                    }
                }
                stockUnits.add(stockUnit.build());
            }
            return  new TimeSeriesResponse(metaData, stockUnits);
        }

        /**
         * Wraps a parse failure or an API error message in a response.
         *
         * @param error the message describing what went wrong
         * @return a response carrying the message, with no bars and empty metadata
         */
        @Override
        public TimeSeriesResponse onParseError(String error) {
            return new TimeSeriesResponse(error);
        }

    }


    @Override
    public String toString() {
        return "TimeSeriesResponse{" +
                "metaData=" + metaData +
                ", stockUnits=" + stockUnits +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
