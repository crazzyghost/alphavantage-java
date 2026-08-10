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
package com.crazzyghost.alphavantage.technicalindicator.response.ultosc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for the ultimate oscillator ({@code ULTOSC}), a momentum
 * oscillator that combines buying pressure across three time periods to
 * reduce the false-divergence signals a single-period oscillator produces.
 * <p>
 * Unlike most single-output indicators, {@code ULTOSC} does not extend one
 * of the package's shared response bases: its metadata carries three
 * separate time periods that none of the shared {@code MetaData} shapes
 * accommodate, so it defines its own {@link MetaData} and parser instead.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class ULTOSCResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's values, one unit per date in the requested series. */
    private List<SimpleTechnicalIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ULTOSC values
     * @param metaData       the parsed response metadata
     */
    private ULTOSCResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ULTOSCResponse(String errorMessage) {
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the API's error message.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the indicator's values.
     *
     * @return the indicator values, one unit per date in the requested series
     */
    public List<SimpleTechnicalIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    /**
     * Returns the response's metadata.
     *
     * @return the response metadata
     */
    public MetaData getMetaData() {
        return metaData;
    }

    /**
     * Parses a raw API response into a {@link ULTOSCResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ULTOSCResponse of(Map<String, Object> stringObjectMap) {
        Parser<ULTOSCResponse> parser = new ULTOSCParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ULTOSCResponse}.
     */
    public static class ULTOSCParser extends DefaultParser<ULTOSCResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public ULTOSCResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                    metaDataMap.get("1: Symbol").toString(),
                    metaDataMap.get("2: Indicator").toString(),
                    metaDataMap.get("3: Last Refreshed").toString(),
                    metaDataMap.get("4: Interval").toString(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.1: Time Period 1"))).intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.2: Time Period 2"))).intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.3: Time Period 3"))).intValue(),
                    metaDataMap.get("6: Time Zone").toString());

            List<SimpleTechnicalIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                SimpleTechnicalIndicatorUnit indicatorUnit = new SimpleTechnicalIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("ULTOSC")),
                        "ULTOSC");
                indicatorUnits.add(indicatorUnit);
            }
            return new ULTOSCResponse(indicatorUnits, metaData);

        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public ULTOSCResponse onParseError(String error) {
            return new ULTOSCResponse(error);
        }

    }

    @Override
    public String toString() {
        return "ULTOSCResponse{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced a {@link ULTOSCResponse},
     * echoed back by the API alongside the indicator values themselves.
     */
    public static class MetaData {

        /** The requested symbol. */
        private String symbol;

        /** The name of the indicator, as reported by the API. */
        private String indicator;

        /** The timestamp of the most recent data point. */
        private String lastRefreshed;

        /** The requested time interval between data points. */
        private String interval;

        /** The requested first, shortest look-back period. */
        private int timePeriod1;

        /** The requested second, medium look-back period. */
        private int timePeriod2;

        /** The requested third, longest look-back period. */
        private int timePeriod3;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;

        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData() {
            this("", "", "", "", 0, 0, 0, "");
        }

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param timePeriod1   the requested first, shortest look-back period
         * @param timePeriod2   the requested second, medium look-back period
         * @param timePeriod3   the requested third, longest look-back period
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                int timePeriod1,
                int timePeriod2,
                int timePeriod3,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.timePeriod1 = timePeriod1;
            this.timePeriod2 = timePeriod2;
            this.timePeriod3 = timePeriod3;
            this.timeZone = timeZone;
        }

        /**
         * Returns the requested symbol.
         *
         * @return the symbol
         */
        public String getSymbol() {
            return symbol;
        }

        /**
         * Returns the indicator's name, as reported by the API.
         *
         * @return the indicator name
         */
        public String getIndicator() {
            return indicator;
        }

        /**
         * Returns the timestamp of the most recent data point.
         *
         * @return the last-refreshed timestamp
         */
        public String getLastRefreshed() {
            return lastRefreshed;
        }

        /**
         * Returns the requested time interval between data points.
         *
         * @return the interval
         */
        public String getInterval() {
            return interval;
        }

        /**
         * Returns the time zone the response's timestamps are expressed in.
         *
         * @return the time zone
         */
        public String getTimeZone() {
            return timeZone;
        }

        /**
         * Returns the requested first, shortest look-back period.
         *
         * @return the first time period
         */
        public int getTimePeriod1() {
            return timePeriod1;
        }

        /**
         * Returns the requested second, medium look-back period.
         *
         * @return the second time period
         */
        public int getTimePeriod2() {
            return timePeriod2;
        }

        /**
         * Returns the requested third, longest look-back period.
         *
         * @return the third time period
         */
        public int getTimePeriod3() {
            return timePeriod3;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", symbol=" + symbol + ", timePeriod1=" + timePeriod1 + ", timePeriod2=" + timePeriod2
                    + ", timePeriod3=" + timePeriod3 + ", timeZone=" + timeZone + "}";
        }

    }

}
