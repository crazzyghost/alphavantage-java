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
package com.crazzyghost.alphavantage.technicalindicator.response.bbands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for Bollinger Bands ({@code BBANDS}), an upper and lower
 * volatility band plotted a configurable number of standard deviations
 * above and below a moving average of a price series.
 * <p>
 * {@code BBANDS} does not extend one of the package's shared response
 * bases: its three-band reading needs {@link BBANDSIndicatorUnit} rather
 * than the single-value {@code SimpleTechnicalIndicatorUnit} the shared
 * bases use.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class BBANDSResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's upper/middle/lower band readings, one unit per date in the requested series. */
    private List<BBANDSIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed BBANDS readings
     * @param metaData       the parsed response metadata
     */
    private BBANDSResponse(List<BBANDSIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private BBANDSResponse(String errorMessage) {
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
     * Returns the indicator's upper/middle/lower band readings.
     *
     * @return the indicator values, one unit per date in the requested series
     */
    public List<BBANDSIndicatorUnit> getIndicatorUnits() {
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
     * Parses a raw API response into a {@link BBANDSResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static BBANDSResponse of(Map<String, Object> stringObjectMap) {
        Parser<BBANDSResponse> parser = new BBANDSParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link BBANDSResponse}.
     */
    public static class BBANDSParser extends DefaultParser<BBANDSResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public BBANDSResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                    String.valueOf(metaDataMap.get("1: Symbol")),
                    String.valueOf(metaDataMap.get("2: Indicator")),
                    String.valueOf(metaDataMap.get("3: Last Refreshed")),
                    String.valueOf(metaDataMap.get("4: Interval")),
                    Double.valueOf(String.valueOf(metaDataMap.get("5: Time Period"))).intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("6.1: Deviation multiplier for upper band")))
                            .intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("6.2: Deviation multiplier for lower band")))
                            .intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("6.3: MA Type"))).intValue(),
                    String.valueOf(metaDataMap.get("7: Series Type")),
                    String.valueOf(metaDataMap.get("8: Time Zone")));

            List<BBANDSIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                BBANDSIndicatorUnit indicatorUnit = new BBANDSIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("Real Upper Band").toString()),
                        Double.parseDouble(m.get("Real Lower Band").toString()),
                        Double.parseDouble(m.get("Real Middle Band").toString()));
                indicatorUnits.add(indicatorUnit);
            }
            return new BBANDSResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public BBANDSResponse onParseError(String error) {
            return new BBANDSResponse(error);
        }
    }

    @Override
    public String toString() {
        return metaData.indicator.replaceAll("\\s+", "") + "Response{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced a {@link BBANDSResponse},
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

        /** The requested number of data points in the middle moving average. */
        private int timePeriod;

        /** The requested number of standard deviations above the middle band. */
        private int nbdevup;

        /** The requested number of standard deviations below the middle band. */
        private int nbdevdn;

        /** The requested moving-average type, as its {@link com.crazzyghost.alphavantage.parameters.MAType} wire value. */
        private int maType;

        /** The requested price series field the bands are computed from. */
        private String seriesType;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;

        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData() {
            this("", "", "", "", 0, 0, 0, 0, "", "");
        }

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param timePeriod    the requested number of data points in the middle moving average
         * @param nbdevup       the requested upper-band standard deviation multiplier
         * @param nbdevdn       the requested lower-band standard deviation multiplier
         * @param maType        the requested moving-average type's wire value
         * @param seriesType    the requested price series field the bands are computed from
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                int timePeriod,
                int nbdevup,
                int nbdevdn,
                int maType,
                String seriesType,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.timePeriod = timePeriod;
            this.nbdevup = nbdevup;
            this.nbdevdn = nbdevdn;
            this.maType = maType;
            this.seriesType = seriesType;
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
         * Returns the requested price series field the bands are computed from.
         *
         * @return the series type
         */
        public String getSeriesType() {
            return seriesType;
        }

        /**
         * Returns the requested number of data points in the middle moving average.
         *
         * @return the time period
         */
        public int getTimePeriod() {
            return timePeriod;
        }

        /**
         * Returns the requested moving-average type's wire value.
         *
         * @return the {@link com.crazzyghost.alphavantage.parameters.MAType} wire value
         */
        public int getMaType() {
            return maType;
        }

        /**
         * Returns the requested upper-band standard deviation multiplier.
         *
         * @return the upper-band multiplier
         */
        public int getNbdevup() {
            return nbdevup;
        }

        /**
         * Returns the requested lower-band standard deviation multiplier.
         *
         * @return the lower-band multiplier
         */
        public int getNbdevdn() {
            return nbdevdn;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", maType=" + maType + ", nbdevdn=" + nbdevdn + ", nbdevup=" + nbdevup + ", seriesType="
                    + seriesType + ", symbol=" + symbol + ", timePeriod=" + timePeriod + ", timeZone=" + timeZone + "}";
        }

    }

}
