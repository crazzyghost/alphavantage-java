package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MAMAResponse {
    private MetaData metaData;
    private List<MAMAIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private MAMAResponse(List<MAMAIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private MAMAResponse(String errorMessage){
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<MAMAIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }
    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static MAMAResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        MAMAResponse parse(Map<String, Object> stringObjectMap){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));
            }catch (ClassCastException e){
                return new MAMAResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                String.valueOf(md.get("1: Symbol")),
                String.valueOf(md.get("2: Indicator")),
                String.valueOf(md.get("3: Last Refreshed")),
                String.valueOf(md.get("4: Interval")),
                Double.valueOf(String.valueOf(md.get("5.1: Fast Limit"))),
                Double.valueOf(String.valueOf(md.get("5.2: Slow Limit"))),
                String.valueOf(md.get("6: Series Type")),
                String.valueOf(md.get("7: Time Zone"))            
            );

            List<MAMAIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                MAMAIndicatorUnit indicatorUnit = new MAMAIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("FAMA")),
                    Double.parseDouble(m.get("MAMA"))
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new MAMAResponse(indicatorUnits, metaData);
        }
    }


    @Override
    public String toString() {
        return "MAMAResponse{" +
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
        private double fastLimit;
        private double slowLimit;
        private String timeZone;
        private String seriesType;
        
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            double fastLimit,
            double slowLimit, 
            String seriesType,
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastLimit = fastLimit;
            this.slowLimit = slowLimit;
            this.timeZone = timeZone;
            this.seriesType = seriesType;
        }
        
        public MetaData(){
            this("", "", "", "", 0.1, 0.1, "", "");
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

        public String getSeriesType() {
            return seriesType;
        }

        public double getFastLimit() {
            return fastLimit;
        }

        public double getSlowLimit() {
            return slowLimit;
        }

        @Override
        public String toString() {
            return "MetaData {fastLimit=" + fastLimit + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed + ", seriesType=" + seriesType + ", slowLimit=" + slowLimit
                    + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

        
        
    }

}