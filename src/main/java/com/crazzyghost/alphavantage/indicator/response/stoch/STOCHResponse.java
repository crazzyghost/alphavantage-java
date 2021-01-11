package com.crazzyghost.alphavantage.indicator.response.stoch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class STOCHResponse {
    private MetaData metaData;
    private List<STOCHIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private STOCHResponse(List<STOCHIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private STOCHResponse(String errorMessage){
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<STOCHIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }
   
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static STOCHResponse of(Map<String, Object> stringObjectMap){
        Parser<STOCHResponse> parser = new STOCHParser();
        return parser.parse(stringObjectMap);
    }

    public static class STOCHParser extends DefaultParser<STOCHResponse> {

        @Override
        public STOCHResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: FastK Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: SlowK Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.3: SlowK MA Type"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.4: SlowD Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.5: SlowD MA Type"))),
                String.valueOf(metaDataMap.get("6: Time Zone"))            
            );

            List<STOCHIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                STOCHIndicatorUnit indicatorUnit = new STOCHIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("SlowK")),
                    Double.parseDouble(m.get("SlowD"))
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new STOCHResponse(indicatorUnits, metaData);

        }

        @Override
        public STOCHResponse onParseError(String error) {
           return new STOCHResponse(error);
        }


    }


    @Override
    public String toString() {
        return "STOCHResponse{" +
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
        private double fastKPeriod;
        private double slowKPeriod;
        private double slowKMaType;
        private double slowDPeriod;
        private double slowDMaType;
        private String timeZone;
        
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            double fastKPeriod,
            double slowKPeriod,
            double slowKMaType,
            double slowDPeriod,
            double slowDMaType,        
            String timeZone 
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastKPeriod = fastKPeriod;
            this.slowKPeriod = slowKPeriod;
            this.slowKMaType = slowKMaType;
            this.slowDPeriod = slowDPeriod;
            this.slowDMaType = slowDMaType;
            this.timeZone = timeZone;
        }
        
        public MetaData(){
            this("", "", "", "", 5, 3, 0, 3, 0, "");
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
 
        public double getFastKPeriod() {
            return fastKPeriod;
        }

        public double getSlowKPeriod() {
            return slowKPeriod;
        }

        public double getSlowKMaType() {
            return slowKMaType;
        }

        public double getSlowDPeriod() {
            return slowDPeriod;
        }

        public double getSlowDMaType() {
            return slowDMaType;
        }

        public String getTimeZone() {
            return timeZone;
        }

        @Override
        public String toString() {
            return "MetaData {fastKPeriod=" + fastKPeriod + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed + ", slowDMaType=" + slowDMaType + ", slowDPeriod="
                    + slowDPeriod + ", slowKMaType=" + slowKMaType + ", slowKPeriod=" + slowKPeriod + ", symbol="
                    + symbol + ", timeZone=" + timeZone + "}";
        }

    }
}