package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ULTOSCResponse {

    private MetaData metaData;
    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private ULTOSCResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private ULTOSCResponse(String errorMessage){
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
    
    public static ULTOSCResponse of(Map<String, Object> stringObjectMap, String indicatorKey){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap, indicatorKey);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        ULTOSCResponse parse(Map<String, Object> stringObjectMap, String indicatorKey){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));
            }catch (ClassCastException e){
                return new ULTOSCResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                md.get("1: Symbol").toString(),
                md.get("2: Indicator").toString(),
                md.get("3: Last Refreshed").toString(),
                md.get("4: Interval").toString(),
                Double.valueOf(md.get("5.1: Time Period 1").toString()).intValue(),
                Double.valueOf(md.get("5.2: Time Period 2").toString()).intValue(),
                Double.valueOf(md.get("5.3: Time Period 3").toString()).intValue(),
                md.get("6: Time Zone").toString()
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
            return new ULTOSCResponse(indicatorUnits, metaData);
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
        private int timePeriod1;
        private int timePeriod2;
        private int timePeriod3;
        private String timeZone;
        
        public MetaData(){
            this("", "", "", "", 0, 0, 0, "");
        }

        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval,
            int timePeriod1,
            int timePeriod2,
            int timePeriod3, 
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.timePeriod1 = timePeriod1;
            this.timePeriod2 = timePeriod2;
            this.timePeriod3 = timePeriod3;
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

        public int getTimePeriod1() {
            return timePeriod1;
        }

        public int getTimePeriod2() {
            return timePeriod2;
        }

        public int getTimePeriod3() {
            return timePeriod3;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", symbol=" + symbol + ", timePeriod1=" + timePeriod1 + ", timePeriod2=" + timePeriod2
                    + ", timePeriod3=" + timePeriod3 + ", timeZone=" + timeZone + "}";
        }


        
    }

}



