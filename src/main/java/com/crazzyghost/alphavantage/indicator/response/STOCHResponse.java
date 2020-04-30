package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<STOCHIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public void setIndicatorUnits(List<STOCHIndicatorUnit> indicatorUnits) {
        this.indicatorUnits = indicatorUnits;
    }

    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static STOCHResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        STOCHResponse parse(Map<String, Object> stringObjectMap){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));

            }catch (ClassCastException e){
                return new STOCHResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                String.valueOf(md.get("1: Symbol")),
                String.valueOf(md.get("2: Indicator")),
                String.valueOf(md.get("3: Last Refreshed")),
                String.valueOf(md.get("4: Interval")),
                Double.valueOf(String.valueOf(md.get("5.1: FastK Period"))),
                Double.valueOf(String.valueOf(md.get("5.2: SlowK Period"))),
                Double.valueOf(String.valueOf(md.get("5.3: SlowK MA Type"))),
                Double.valueOf(String.valueOf(md.get("5.4: SlowD Period"))),
                Double.valueOf(String.valueOf(md.get("5.5: SlowD MA Type"))),
                String.valueOf(md.get("6: Time Zone"))            
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