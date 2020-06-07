package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class SARResponse {

    private MetaData metaData;
    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private SARResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private SARResponse(String errorMessage){
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
    
    public static SARResponse of(Map<String, Object> stringObjectMap){
        Parser<SARResponse> parser = new SARParser();
        return parser.parse(stringObjectMap);
    }

    public static class SARParser extends DefaultParser<SARResponse> {

        @Override
        public SARResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: Acceleration"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: Maximum"))),
                String.valueOf(metaDataMap.get("6: Time Zone"))
            );

            List<SimpleIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                SimpleIndicatorUnit indicatorUnit = new SimpleIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("SAR")),
                    "SAR"
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new SARResponse(indicatorUnits, metaData);
        }

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

    public static class MetaData {

        private String symbol;
        private String indicator;
        private String lastRefreshed;
        private String interval;
        private double acceleration;
        private double maximum;
        private String timeZone;
        
        public MetaData(){
            this("", "", "", "", 0, 0, "");
        }

        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            double acceleration,
            double maximum,
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.acceleration = acceleration;
            this.maximum = maximum;
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

        public double getAcceleration() {
            return acceleration;
        }

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



