package com.crazzyghost.alphavantage.indicator.response.htphasor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for the Hilbert transform phasor components ({@code HT_PHASOR}),
 * which decomposes the price series into in-phase and quadrature components
 * using the Hilbert transform.
 * <p>
 * {@code HT_PHASOR} does not extend one of the package's shared response
 * bases: its two-component reading needs {@link HTPHASORIndicatorUnit}
 * rather than the single-value {@code SimpleIndicatorUnit} the
 * shared bases use.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.htphasor.HTPHASORResponse}
 */
@Deprecated
public class HTPHASORResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's phase/quadrature readings, one unit per date in the requested series. */
    private List<HTPHASORIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_PHASOR readings
     * @param metaData       the parsed response metadata
     */
    private HTPHASORResponse(List<HTPHASORIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTPHASORResponse(String errorMessage){
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
     * Returns the indicator's phase/quadrature readings.
     *
     * @return the indicator values, one unit per date in the requested series
     */
    public List<HTPHASORIndicatorUnit> getIndicatorUnits() {
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
     * Parses a raw API response into an {@link HTPHASORResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static HTPHASORResponse of(Map<String, Object> stringObjectMap){
        Parser<HTPHASORResponse> parser = new HTPHASORParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link HTPHASORResponse}.
     */
    public static class HTPHASORParser extends DefaultParser<HTPHASORResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public HTPHASORResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                String.valueOf(metaDataMap.get("5: Series Type")),
                String.valueOf(metaDataMap.get("6: Time Zone"))
            );

            List<HTPHASORIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                HTPHASORIndicatorUnit indicatorUnit = new HTPHASORIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("PHASE")),
                    Double.parseDouble(m.get("QUADRATURE"))
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new HTPHASORResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public HTPHASORResponse onParseError(String error) {
            return new HTPHASORResponse(error);
        }
    }


    @Override
    public String toString() {
        return "HTPHASORResponse{" +
            "metaData=" + metaData +
            ",indicatorUnits=" + indicatorUnits.size() +
            ", errorMessage='" + errorMessage + '\'' +
        '}';
    }

    /**
     * Metadata describing the request that produced an {@link
     * HTPHASORResponse}, echoed back by the API alongside the indicator
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

        /** The requested price series field the indicator is computed from. */
        private String seriesType;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;
     
        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData(){
            this("", "", "", "", "", "");
        }

        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param seriesType    the requested price series field the indicator is computed from
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            String seriesType, 
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
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
         * Returns the requested price series field the indicator is computed from.
         *
         * @return the series type
         */
        public String getSeriesType() {
            return seriesType;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", seriesType=" + seriesType + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

        
    }
}



