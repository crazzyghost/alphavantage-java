package com.crazzyghost.alphavantage.indicator.response.macd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for moving average convergence / divergence ({@code MACD}), the
 * difference between a fast and slow EMA of a price series, together with a
 * signal line that is itself an EMA of that difference.
 * <p>
 * {@code MACD} does not extend one of the package's shared response bases:
 * its three-value reading needs {@link MACDIndicatorUnit} rather than the
 * single-value {@code SimpleIndicatorUnit} the shared bases use.
 * {@link MACDEXTResponse} reuses this same unit type for its
 * configurable-moving-average variant.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDResponse}
 */
@Deprecated
public class MACDResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's MACD/signal/histogram readings, one unit per date in the requested series. */
    private List<MACDIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MACD readings
     * @param metaData       the parsed response metadata
     */
    private MACDResponse(List<MACDIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MACDResponse(String errorMessage){
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
     * Returns the indicator's MACD/signal/histogram readings.
     *
     * @return the indicator values, one unit per date in the requested series
     */
    public List<MACDIndicatorUnit> getIndicatorUnits() {
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
     * Parses a raw API response into a {@link MACDResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MACDResponse of(Map<String, Object> stringObjectMap){
        Parser<MACDResponse> parser = new MACDParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MACDResponse}.
     */
    public static class MACDParser extends DefaultParser<MACDResponse> {

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public MACDResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: Fast Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: Slow Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.3: Signal Period"))),
                String.valueOf(metaDataMap.get("6: Series Type")),
                String.valueOf(metaDataMap.get("7: Time Zone"))            
            );

            List<MACDIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                MACDIndicatorUnit indicatorUnit = new MACDIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("MACD_Hist")),
                    Double.parseDouble(m.get("MACD_Signal")),
                    Double.parseDouble(m.get("MACD"))
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new MACDResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public MACDResponse onParseError(String error) {
            return new MACDResponse(error);
        }
    }


    @Override
    public String toString() {
        return "MACDResponse{" +
            "metaData=" + metaData +
            ",indicatorUnits=" + indicatorUnits.size() +
            ", errorMessage='" + errorMessage + '\'' +
        '}';
    }

    /**
     * Metadata describing the request that produced a {@link MACDResponse},
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
        private double fastPeriod;

        /** The requested number of data points in the slow EMA. */
        private double slowPeriod;

        /** The requested number of data points in the signal line's EMA. */
        private double signalPeriod;

        /** The time zone the response's timestamps are expressed in. */
        private String timeZone;

        /** The requested price series field MACD is computed from. */
        private String seriesType;
        
        /**
         * Creates a populated metadata instance.
         *
         * @param symbol        the requested symbol
         * @param indicator     the indicator's name, as reported by the API
         * @param lastRefreshed the timestamp of the most recent data point
         * @param interval      the requested time interval between data points
         * @param fastPeriod    the requested number of data points in the fast EMA
         * @param slowPeriod    the requested number of data points in the slow EMA
         * @param signalPeriod  the requested number of data points in the signal line's EMA
         * @param seriesType    the requested price series field MACD is computed from
         * @param timeZone      the time zone the response's timestamps are expressed in
         */
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            double fastPeriod,
            double slowPeriod,
            double signalPeriod, 
            String seriesType,
            String timeZone 
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastPeriod = fastPeriod;
            this.slowPeriod = slowPeriod;
            this.signalPeriod = signalPeriod;
            this.seriesType = seriesType;
            this.timeZone = timeZone;
        }
        
        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData(){
            this("", "", "", "", 12, 26, 9, "", "");
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
         * Returns the requested price series field MACD is computed from.
         *
         * @return the series type
         */
        public String getSeriesType() {
            return seriesType;
        }

        /**
         * Returns the requested number of data points in the fast EMA.
         *
         * @return the fast period
         */
        public double getFastPeriod() {
            return fastPeriod;
        }

        /**
         * Returns the requested number of data points in the slow EMA.
         *
         * @return the slow period
         */
        public double getSlowPeriod() {
            return slowPeriod;
        }

        /**
         * Returns the requested number of data points in the signal line's EMA.
         *
         * @return the signal period
         */
        public double getSignalPeriod() {
            return signalPeriod;
        }

        @Override
        public String toString() {
            return "MetaData {fastPeriod=" + fastPeriod + ", indicator=" + indicator + ", interval=" + interval
            + ", lastRefreshed=" + lastRefreshed + ", seriesType=" + seriesType + ", signalPeriod="
            + signalPeriod + ", slowPeriod=" + slowPeriod + ", symbol=" + symbol + ", timeZone=" + timeZone
            + "}";
        }

    }
}