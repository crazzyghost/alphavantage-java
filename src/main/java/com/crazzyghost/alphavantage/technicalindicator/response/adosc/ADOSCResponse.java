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
package com.crazzyghost.alphavantage.technicalindicator.response.adosc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for the Chaikin A/D oscillator ({@code ADOSC}), the MACD of the
 * Chaikin accumulation/distribution line using a fast and slow EMA period.
 * <p>
 * Unlike most single-output indicators, {@code ADOSC} does not extend one of
 * the package's shared response bases: its metadata carries a fast and slow
 * EMA period that none of the shared {@code MetaData} shapes accommodate, so
 * it defines its own {@link MetaData} and parser instead.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class ADOSCResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's values, one unit per date in the requested series. */
    private List<SimpleTechnicalIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ADOSC values
     * @param metaData       the parsed response metadata
     */
    private ADOSCResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ADOSCResponse(String errorMessage) {
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
     * Parses a raw API response into an {@link ADOSCResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ADOSCResponse of(Map<String, Object> stringObjectMap) {
        Parser<ADOSCResponse> parser = new ADOSCParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ADOSCResponse}.
     */
    public static class ADOSCParser extends DefaultParser<ADOSCResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public ADOSCResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                    metaDataMap.get("1: Symbol").toString(),
                    metaDataMap.get("2: Indicator").toString(),
                    metaDataMap.get("3: Last Refreshed").toString(),
                    metaDataMap.get("4: Interval").toString(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.1: FastK Period"))).intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.2: SlowK Period"))).intValue(),
                    metaDataMap.get("6: Time Zone").toString());

            List<SimpleTechnicalIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                SimpleTechnicalIndicatorUnit indicatorUnit = new SimpleTechnicalIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("ADOSC")),
                        "ADOSC");
                indicatorUnits.add(indicatorUnit);
            }
            return new ADOSCResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public ADOSCResponse onParseError(String error) {
            return new ADOSCResponse(error);
        }
    }

    @Override
    public String toString() {
        return "ADOSCResponse{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced an {@link ADOSCResponse},
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

        /** The requested number of data points in the fast EMA. */
        private int fastKPeriod;

        /** The requested number of data points in the slow EMA. */
        private int slowKPeriod;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;

        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData() {
            this("", "", "", "", 0, 0, "");
        }

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param fastPeriod    the requested number of data points in the fast EMA
         * @param slowPeriod    the requested number of data points in the slow EMA
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                int fastPeriod,
                int slowPeriod,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastKPeriod = fastPeriod;
            this.slowKPeriod = slowPeriod;
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
         * Returns the requested number of data points in the fast EMA.
         *
         * @return the fast period
         */
        public int getFastKPeriod() {
            return fastKPeriod;
        }

        /**
         * Returns the requested number of data points in the slow EMA.
         *
         * @return the slow period
         */
        public int getSlowKPeriod() {
            return slowKPeriod;
        }

        /**
         * Returns the time zone the response's timestamps are expressed in.
         *
         * @return the time zone
         */
        public String getTimeZone() {
            return timeZone;
        }

        @Override
        public String toString() {
            return "MetaData {fastKPeriod=" + fastKPeriod + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed
                    + ", slowKPeriod=" + slowKPeriod + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

    }

}
