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
package com.crazzyghost.alphavantage.technicalindicator.response.mama;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for the MESA adaptive moving average ({@code MAMA}), an adaptive
 * moving average that adjusts its own smoothing speed to price movement
 * using the Hilbert transform, reported alongside its slower-following
 * companion, FAMA.
 * <p>
 * {@code MAMA} does not extend one of the package's shared response bases:
 * its two-value reading needs {@link MAMAIndicatorUnit} rather than the
 * single-value {@code SimpleTechnicalIndicatorUnit} the shared bases use.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class MAMAResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's MAMA/FAMA readings, one unit per date in the requested series. */
    private List<MAMAIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MAMA readings
     * @param metaData       the parsed response metadata
     */
    private MAMAResponse(List<MAMAIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MAMAResponse(String errorMessage) {
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
     * Returns the indicator's MAMA/FAMA readings.
     *
     * @return the indicator values, one unit per date in the requested series
     */
    public List<MAMAIndicatorUnit> getIndicatorUnits() {
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
     * Parses a raw API response into a {@link MAMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MAMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<MAMAResponse> parser = new MAMAParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MAMAResponse}.
     */
    public static class MAMAParser extends DefaultParser<MAMAResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public MAMAResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                    String.valueOf(metaDataMap.get("1: Symbol")),
                    String.valueOf(metaDataMap.get("2: Indicator")),
                    String.valueOf(metaDataMap.get("3: Last Refreshed")),
                    String.valueOf(metaDataMap.get("4: Interval")),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.1: Fast Limit"))),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.2: Slow Limit"))),
                    String.valueOf(metaDataMap.get("6: Series Type")),
                    String.valueOf(metaDataMap.get("7: Time Zone")));

            List<MAMAIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                MAMAIndicatorUnit indicatorUnit = new MAMAIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("FAMA")),
                        Double.parseDouble(m.get("MAMA")));
                indicatorUnits.add(indicatorUnit);
            }
            return new MAMAResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public MAMAResponse onParseError(String error) {
            return new MAMAResponse(error);
        }
    }

    @Override
    public String toString() {
        return "MAMAResponse{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced a {@link MAMAResponse},
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

        /** The requested upper bound on how fast MAMA can adapt. */
        private double fastLimit;

        /** The requested lower bound on how fast MAMA can adapt. */
        private double slowLimit;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;

        /** The requested price series field the average is computed from. */
        private String seriesType;

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param fastLimit     the requested upper adaptation-speed bound
         * @param slowLimit     the requested lower adaptation-speed bound
         * @param seriesType    the requested price series field the average is computed from
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                double fastLimit,
                double slowLimit,
                String seriesType,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastLimit = fastLimit;
            this.slowLimit = slowLimit;
            this.timeZone = timeZone;
            this.seriesType = seriesType;
        }

        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData() {
            this("", "", "", "", 0.1, 0.1, "", "");
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
         * Returns the requested price series field the average is computed from.
         *
         * @return the series type
         */
        public String getSeriesType() {
            return seriesType;
        }

        /**
         * Returns the requested upper adaptation-speed bound.
         *
         * @return the fast limit
         */
        public double getFastLimit() {
            return fastLimit;
        }

        /**
         * Returns the requested lower adaptation-speed bound.
         *
         * @return the slow limit
         */
        public double getSlowLimit() {
            return slowLimit;
        }

        @Override
        public String toString() {
            return "MetaData {fastLimit=" + fastLimit + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed + ", seriesType=" + seriesType + ", slowLimit=" + slowLimit
                    + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }
    }

}
