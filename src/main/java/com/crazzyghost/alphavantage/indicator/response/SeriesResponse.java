package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeriesResponse {

    private MetaData metaData;
    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private SeriesResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private SeriesResponse(String errorMessage){
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

    public List<SimpleIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public void setIndicatorUnits(List<SimpleIndicatorUnit> indicatorUnits) {
        this.indicatorUnits = indicatorUnits;
    }

    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static SeriesResponse of(Map<String, Object> stringObjectMap, String indicatorKey){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap, indicatorKey);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        SeriesResponse parse(Map<String, Object> stringObjectMap, String indicatorKey){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));
            }catch (ClassCastException e){
                return new SeriesResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                String.valueOf(md.get("1: Symbol")),
                String.valueOf(md.get("2: Indicator")),
                String.valueOf(md.get("3: Last Refreshed")),
                String.valueOf(md.get("4: Interval")),
                String.valueOf(md.get("5: Series Type")),
                String.valueOf(md.get("6: Time Zone"))
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
            return new SeriesResponse(indicatorUnits, metaData);
        }
    }


    @Override
    public String toString() {
        return "SeriesResponse{" +
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
        private String seriesType;
        private String timeZone;
     
        public MetaData(){
            this("", "", "", "", "", "");
        }

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

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", seriesType=" + seriesType + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

        
    }
}



