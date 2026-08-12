package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Base response for indicators driven by {@link
 * com.crazzyghost.alphavantage.indicator.request.SimpleIndicatorRequest},
 * which take no parameters beyond {@code symbol} and {@code interval} —
 * {@code VWAP}, {@code BOP}, {@code TRANGE}, {@code AD}, and {@code OBV}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse}
 */
@Deprecated
public abstract class SimpleIndicatorResponse {

    /** The response's metadata, echoing the request's parameters. */
    protected MetaData metaData;

    /** The indicator's values, one unit per date in the requested series. */
    protected List<SimpleIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    protected String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed indicator values
     * @param metaData       the parsed response metadata
     */
    protected SimpleIndicatorResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    protected SimpleIndicatorResponse(String errorMessage) {
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
    public List<SimpleIndicatorUnit> getIndicatorUnits() {
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

//    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap, String indicatorKey) {
//        Parser<SimpleIndicatorResponse> parser = new SimpleIndicatorParser(indicatorKey);
//        return parser.parse(stringObjectMap);
//    }

    /**
     * Base parser for {@link SimpleIndicatorResponse} subclasses,
     * translating the raw metadata and indicator maps returned by {@link
     * com.crazzyghost.alphavantage.parser.Parser} into typed {@link
     * MetaData} and {@link SimpleIndicatorUnit} values.
     *
     * @param <T> the concrete {@link SimpleIndicatorResponse} subtype this parser produces
     */
    public static abstract class SimpleIndicatorParser<T> extends DefaultParser<T> {

        /**
         * Creates a parser.
         */
        protected SimpleIndicatorParser() { }

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public T parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                String.valueOf(metaDataMap.get("5: Time Zone"))
            );

            List<SimpleIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                SimpleIndicatorUnit indicatorUnit = new SimpleIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get(getIndicatorKey())),
                    getIndicatorKey()
                );
                indicatorUnits.add(indicatorUnit);
            }
            return get(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public T onParseError(String error) {
            return get(error);
        }

        /**
         * Builds a successful response.
         *
         * @param indicatorUnits the parsed indicator values
         * @param metaData       the parsed response metadata
         * @return the built response
         */
        public abstract T get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData);

        /**
         * Builds a failed response.
         *
         * @param error the API's error message
         * @return the built response
         */
        public abstract T get(String error);

        /**
         * Returns the JSON key under which this indicator's value is nested
         * in the API's per-date response object.
         *
         * @return the indicator's JSON key
         */
        public abstract String getIndicatorKey();
    }


    @Override
    public String toString() {
        return metaData.indicator.replaceAll("\\s+","") +"Response{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    /**
     * Metadata describing the request that produced a {@link
     * SimpleIndicatorResponse}, echoed back by the API alongside
     * the indicator values themselves.
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

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;
        
        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData(){
            this("", "", "", "", "");
        }

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
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

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator +     
                ", interval=" + interval + 
                ", lastRefreshed=" + lastRefreshed + 
                ", symbol=" + symbol + 
                ", timeZone=" + timeZone +
            "}";
        }
        
    }

}



