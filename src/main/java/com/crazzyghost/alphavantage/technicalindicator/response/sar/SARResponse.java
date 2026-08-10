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
package com.crazzyghost.alphavantage.technicalindicator.response.sar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for the parabolic SAR ({@code SAR}), a trend-following stop and
 * reversal indicator that trails price and accelerates toward it over time.
 * <p>
 * Unlike most single-output indicators, {@code SAR} does not extend one of
 * the package's shared response bases: its metadata carries an acceleration
 * factor and a maximum that none of the shared {@code MetaData} shapes
 * accommodate, so it defines its own {@link MetaData} and parser instead.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class SARResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's values, one unit per date in the requested series. */
    private List<SimpleTechnicalIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed SAR values
     * @param metaData       the parsed response metadata
     */
    private SARResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private SARResponse(String errorMessage) {
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
     * Parses a raw API response into a {@link SARResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SARResponse of(Map<String, Object> stringObjectMap) {
        Parser<SARResponse> parser = new SARParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link SARResponse}.
     */
    public static class SARParser extends DefaultParser<SARResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public SARResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                    String.valueOf(metaDataMap.get("1: Symbol")),
                    String.valueOf(metaDataMap.get("2: Indicator")),
                    String.valueOf(metaDataMap.get("3: Last Refreshed")),
                    String.valueOf(metaDataMap.get("4: Interval")),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.1: Acceleration"))),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.2: Maximum"))),
                    String.valueOf(metaDataMap.get("6: Time Zone")));

            List<SimpleTechnicalIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                SimpleTechnicalIndicatorUnit indicatorUnit = new SimpleTechnicalIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("SAR")),
                        "SAR");
                indicatorUnits.add(indicatorUnit);
            }
            return new SARResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public SARResponse onParseError(String error) {
            return new SARResponse(error);
        }

    }

    @Override
    public String toString() {
        return "SARResponse{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced a {@link SARResponse},
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

        /** The requested acceleration factor step. */
        private double acceleration;

        /** The requested acceleration factor's upper bound. */
        private double maximum;

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
         * @param acceleration  the requested acceleration factor step
         * @param maximum       the requested acceleration factor's upper bound
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                double acceleration,
                double maximum,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.acceleration = acceleration;
            this.maximum = maximum;
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
         * Returns the requested acceleration factor step.
         *
         * @return the acceleration step
         */
        public double getAcceleration() {
            return acceleration;
        }

        /**
         * Returns the requested acceleration factor's upper bound.
         *
         * @return the maximum acceleration
         */
        public double getMaximum() {
            return maximum;
        }

        @Override
        public String toString() {
            return "MetaData {acceleration=" + acceleration + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed + ", maximum=" + maximum + ", symbol=" + symbol + ", timeZone="
                    + timeZone + "}";
        }

    }

}
