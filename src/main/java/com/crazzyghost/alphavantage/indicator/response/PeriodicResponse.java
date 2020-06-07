package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class PeriodicResponse {

    private MetaData metaData;
    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private PeriodicResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private PeriodicResponse(String errorMessage){
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<SimpleIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static PeriodicResponse of(Map<String, Object> stringObjectMap, String indicatorKey){
        Parser<PeriodicResponse> parser = new PeriodicParser(indicatorKey);
        return parser.parse(stringObjectMap);
    }

    public static class PeriodicParser extends DefaultParser<PeriodicResponse> {

        private String indicatorKey;

        public PeriodicParser(String indicatorKey){
            this.indicatorKey = indicatorKey;
        }

        @Override
        public PeriodicResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                (int)Double.parseDouble(String.valueOf(metaDataMap.get("5: Time Period"))),
                String.valueOf(metaDataMap.get("6: Time Zone"))
            );

            List<SimpleIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                SimpleIndicatorUnit indicatorUnit = new SimpleIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get(indicatorKey)),
                    indicatorKey
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new PeriodicResponse(indicatorUnits, metaData);
        }

        @Override
        public PeriodicResponse onParseError(String error) {
            return new PeriodicResponse(error);
        }
    }

    @Override
    public String toString() {
        return metaData.indicator.replaceAll("\\s+","") +"Response{" +
            "metaData=" + metaData +
            ",indicatorUnits=" + indicatorUnits.size() +
            ", errorMessage='" + errorMessage + '\'' +
        '}';
    }

    public static class MetaData {

        private String symbol;
        private String indicator;
        private String lastRefreshed;
        private String interval;
        private int timePeriod;
        private String timeZone;
        
        public MetaData(){
            this("", "", "", "", 0, "");
        }

        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            int timePeriod,
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.timePeriod = timePeriod;
            this.timeZone = timeZone;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getIndicator() {
            return indicator;
        }

        public String getLastRefreshed() {
            return lastRefreshed;
        }

        public String getInterval() {
            return interval;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public int getTimePeriod() {
            return timePeriod;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator +     
                ", interval=" + interval + 
                ", lastRefreshed=" + lastRefreshed + 
                ", symbol=" + symbol + 
                ", timePeriod=" + timePeriod + 
                ", timeZone=" + timeZone +
                 "}";
        }
        
    }

}



