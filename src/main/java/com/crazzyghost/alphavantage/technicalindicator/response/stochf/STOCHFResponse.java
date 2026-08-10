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
package com.crazzyghost.alphavantage.technicalindicator.response.stochf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for the stochastic fast oscillator ({@code STOCHF}), the
 * unsmoothed counterpart of {@code STOCH} that reports raw %K and a lightly
 * smoothed %D without the slow %K stage.
 * <p>
 * {@code STOCHF} does not extend one of the package's shared response
 * bases: its two-line reading needs {@link STOCHFIndicatorUnit} rather than
 * the single-value {@code SimpleTechnicalIndicatorUnit} the shared bases
 * use.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class STOCHFResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's %K/%D readings, one unit per date in the requested series. */
    private List<STOCHFIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed STOCHF readings
     * @param metaData       the parsed response metadata
     */
    private STOCHFResponse(List<STOCHFIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private STOCHFResponse(String errorMessage) {
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
     * Returns the indicator's %K/%D readings.
     *
     * @return the indicator values, one unit per date in the requested series
     */
    public List<STOCHFIndicatorUnit> getIndicatorUnits() {
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
     * Parses a raw API response into a {@link STOCHFResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static STOCHFResponse of(Map<String, Object> stringObjectMap) {
        Parser<STOCHFResponse> parser = new STOCHFParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link STOCHFResponse}.
     */
    public static class STOCHFParser extends DefaultParser<STOCHFResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public STOCHFResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                    String.valueOf(metaDataMap.get("1: Symbol")),
                    String.valueOf(metaDataMap.get("2: Indicator")),
                    String.valueOf(metaDataMap.get("3: Last Refreshed")),
                    String.valueOf(metaDataMap.get("4: Interval")),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.1: FastK Period"))),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.2: FastD Period"))),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.3: FastD MA Type"))),
                    String.valueOf(metaDataMap.get("6: Time Zone")));

            List<STOCHFIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                STOCHFIndicatorUnit indicatorUnit = new STOCHFIndicatorUnit(e.getKey(),
                        Double.parseDouble(m.get("FastK")), Double.parseDouble(m.get("FastD")));
                indicatorUnits.add(indicatorUnit);
            }
            return new STOCHFResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public STOCHFResponse onParseError(String error) {
            return new STOCHFResponse(error);
        }
    }

    @Override
    public String toString() {
        return "STOCHFResponse{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced a {@link
     * STOCHFResponse}, echoed back by the API alongside the indicator
     * values themselves.
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

        /** The requested raw %K look-back period. */
        private double fastKPeriod;

        /** The requested fast %D smoothing period. */
        private double fastDPeriod;

        /** The requested moving-average type used to smooth fast %D, as its wire value. */
        private double fastDMaType;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param fastKPeriod   the requested raw %K look-back period
         * @param fastDPeriod   the requested fast %D smoothing period
         * @param fastDMaType   the requested fast %D moving-average type's wire value
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                double fastKPeriod,
                double fastDPeriod,
                double fastDMaType,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastKPeriod = fastKPeriod;
            this.fastDPeriod = fastDPeriod;
            this.fastDMaType = fastDMaType;
            this.timeZone = timeZone;
        }

        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData() {
            this("", "", "", "", 5, 3, 0, "");
        }

        /**
         * Returns the requested fast %D moving-average type's wire value.
         *
         * @return the {@link com.crazzyghost.alphavantage.parameters.MAType} wire value
         */
        public double getFastDMaType() {
            return fastDMaType;
        }

        /**
         * Returns the requested fast %D smoothing period.
         *
         * @return the fast %D period
         */
        public double getFastDPeriod() {
            return fastDPeriod;
        }

        /**
         * Returns the requested raw %K look-back period.
         *
         * @return the fast %K period
         */
        public double getFastKPeriod() {
            return fastKPeriod;
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
         * Returns the requested time interval between data points.
         *
         * @return the interval
         */
        public String getInterval() {
            return interval;
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
         * Returns the requested symbol.
         *
         * @return the symbol
         */
        public String getSymbol() {
            return symbol;
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
            return "MetaData {fastDMaType=" + fastDMaType + ", fastDPeriod=" + fastDPeriod + ", fastKPeriod="
                    + fastKPeriod + ", indicator=" + indicator + ", interval=" + interval + ", lastRefreshed="
                    + lastRefreshed + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }
    }
}
