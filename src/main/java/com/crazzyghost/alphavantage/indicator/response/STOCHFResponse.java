package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class STOCHFResponse {

    private MetaData metaData;
    private List<STOCHFIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private STOCHFResponse(List<STOCHFIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private STOCHFResponse(String errorMessage){
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

    public List<STOCHFIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public void setIndicatorUnits(List<STOCHFIndicatorUnit> indicatorUnits) {
        this.indicatorUnits = indicatorUnits;
    }

    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static STOCHFResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        STOCHFResponse parse(Map<String, Object> stringObjectMap){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));

            }catch (ClassCastException e){
                return new STOCHFResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                String.valueOf(md.get("1: Symbol")),
                String.valueOf(md.get("2: Indicator")),
                String.valueOf(md.get("3: Last Refreshed")),
                String.valueOf(md.get("4: Interval")),
                Double.valueOf(String.valueOf(md.get("5.1: FastK Period"))),
                Double.valueOf(String.valueOf(md.get("5.2: FastD Period"))),
                Double.valueOf(String.valueOf(md.get("5.3: FastD MA Type"))),
                String.valueOf(md.get("6: Time Zone"))            
            );

            List<STOCHFIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                STOCHFIndicatorUnit indicatorUnit = new STOCHFIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("FastK")),
                    Double.parseDouble(m.get("FastD"))
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new STOCHFResponse(indicatorUnits, metaData);
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

    public static class MetaData {

        private String symbol;
        private String indicator;
        private String lastRefreshed;
        private String interval;
        private double fastKPeriod;
        private double fastDPeriod;
        private double fastDMaType;
        private String timeZone;
        
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            double fastKPeriod,
            double fastDPeriod,
            double fastDMaType, 
            String timeZone 
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastKPeriod = fastKPeriod;
            this.fastDPeriod = fastDPeriod;
            this.fastDMaType = fastDMaType;
            this.timeZone = timeZone;
        }
        
        public MetaData(){
            this("", "", "", "", 5, 3, 0, "");
        }

       public double getFastDMaType() {
           return fastDMaType;
       } 
       
       public double getFastDPeriod() {
           return fastDPeriod;
       }
        
       public double getFastKPeriod() {
           return fastKPeriod;
       }
        
       public String getIndicator() {
           return indicator;
       }

       public String getInterval() {
           return interval;
       }

       public String getLastRefreshed() {
           return lastRefreshed;
       }

       public String getSymbol() {
           return symbol;
       }

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