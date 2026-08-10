package com.crazzyghost.alphavantage.indicator.response.macd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Response for MACD with controllable moving-average type
 * ({@code MACDEXT}), a variant of {@link MACDResponse MACD} that lets the
 * fast, slow, and signal components each use a different moving-average
 * type instead of the fixed EMA the plain {@code MACD} function uses.
 * <p>
 * {@code MACDEXT} does not extend one of the package's shared response
 * bases: like {@code MACD}, its three-value reading needs {@link
 * MACDIndicatorUnit} rather than the single-value
 * {@code SimpleIndicatorUnit} the shared bases use. Its extra
 * moving-average-type parameters live only in {@link MACDEXTResponse.MetaData},
 * not in the shared {@link MACDIndicatorUnit}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDEXTResponse}
 */
@Deprecated
public class MACDEXTResponse {

    /** The response's metadata, echoing the request's parameters. */
    private MetaData metaData;

    /** The indicator's MACD/signal/histogram readings, one unit per date in the requested series. */
    private List<MACDIndicatorUnit> indicatorUnits;

    /** The API's error message, or {@code null} if the request succeeded. */
    private String errorMessage;

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MACDEXT readings
     * @param metaData       the parsed response metadata
     */
    private MACDEXTResponse(List<MACDIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MACDEXTResponse(String errorMessage){
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
     * Parses a raw API response into a {@link MACDEXTResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MACDEXTResponse of(Map<String, Object> stringObjectMap){
        Parser<MACDEXTResponse> parser = new MACDEXTParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MACDEXTResponse}.
     */
    public static class MACDEXTParser extends DefaultParser<MACDEXTResponse>{

        /**
         * Parses the API's raw metadata and per-date indicator maps into a
         * successful response.
         *
         * @param metaDataMap   the raw {@code "Meta Data"} entries
         * @param indicatorData the raw per-date indicator value entries
         * @return the parsed response
         */
        @Override
        public MACDEXTResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: Fast Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: Slow Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.3: Signal Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.4: Fast MA Type"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.5: Slow MA Type"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.6: Signal MA Type"))),
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
            return new MACDEXTResponse(indicatorUnits, metaData);
        }

        /**
         * Builds a failed response from a parse error.
         *
         * @param error the error message
         * @return the failed response
         */
        @Override
        public MACDEXTResponse onParseError(String error) {
            return new MACDEXTResponse(error);
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
     * Metadata describing the request that produced a {@link
     * MACDEXTResponse}, echoed back by the API alongside the indicator
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

        /** The requested number of data points in the fast moving average. */
        private double fastPeriod;

        /** The requested number of data points in the slow moving average. */
        private double slowPeriod;

        /** The requested number of data points in the signal line's moving average. */
        private double signalPeriod;

        /** The requested moving-average type used for the fast component, as its wire value. */
        private double fastMaType;

        /** The requested moving-average type used for the slow component, as its wire value. */
        private double slowMaType;

        /** The requested moving-average type used for the signal line, as its wire value. */
        private double signalMaType;

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
         * @param fastPeriod    the requested number of data points in the fast moving average
         * @param slowPeriod    the requested number of data points in the slow moving average
         * @param signalPeriod  the requested number of data points in the signal line's moving average
         * @param fastMaType    the requested fast component's moving-average type wire value
         * @param slowMaType    the requested slow component's moving-average type wire value
         * @param signalMaType  the requested signal line's moving-average type wire value
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
            double fastMaType,
            double slowMaType,
            double signalMaType,
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
            this.fastMaType = fastMaType;
            this.slowMaType = slowMaType;
            this.signalMaType = signalMaType;
            this.seriesType = seriesType;
            this.timeZone = timeZone;
        }
        
        /**
         * Creates an empty metadata instance, used for failed responses.
         */
        public MetaData(){
            this("", "", "", "", 12, 26, 9, 0, 0, 0,"", "");
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
         * Returns the requested number of data points in the fast moving average.
         *
         * @return the fast period
         */
        public double getFastPeriod() {
            return fastPeriod;
        }

        /**
         * Returns the requested number of data points in the slow moving average.
         *
         * @return the slow period
         */
        public double getSlowPeriod() {
            return slowPeriod;
        }

        /**
         * Returns the requested number of data points in the signal line's moving average.
         *
         * @return the signal period
         */
        public double getSignalPeriod() {
            return signalPeriod;
        }

        /**
         * Returns the requested fast component's moving-average type wire value.
         *
         * @return the {@link com.crazzyghost.alphavantage.parameters.MAType} wire value
         */
        public double getFastMaType() {
            return fastMaType;
        }

        /**
         * Returns the requested slow component's moving-average type wire value.
         *
         * @return the {@link com.crazzyghost.alphavantage.parameters.MAType} wire value
         */
        public double getSlowMaType() {
            return slowMaType;
        }

        /**
         * Returns the requested signal line's moving-average type wire value.
         *
         * @return the {@link com.crazzyghost.alphavantage.parameters.MAType} wire value
         */
        public double getSignalMaType() {
            return signalMaType;
        }

        @Override
        public String toString() {
            return "MetaData {fastMaType=" + fastMaType + ", fastPeriod=" + fastPeriod + ", indicator=" + indicator
                    + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed + ", seriesType=" + seriesType
                    + ", signalMaType=" + signalMaType + ", signalPeriod=" + signalPeriod + ", slowMaType=" + slowMaType
                    + ", slowPeriod=" + slowPeriod + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }
        
    }
}