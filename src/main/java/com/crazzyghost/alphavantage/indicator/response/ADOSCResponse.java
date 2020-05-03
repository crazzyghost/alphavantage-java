package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ADOSCResponse {

    private MetaData metaData;
    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private ADOSCResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private ADOSCResponse(String errorMessage){
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
    
    public static ADOSCResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        ADOSCResponse parse(Map<String, Object> stringObjectMap){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String, String>>) stringObjectMap.get(keys.get(1));
            }catch (ClassCastException e){
                return new ADOSCResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                md.get("1: Symbol").toString(),
                md.get("2: Indicator").toString(),
                md.get("3: Last Refreshed").toString(),
                md.get("4: Interval").toString(),
                Double.valueOf(md.get("5.1: FastK Period").toString()).intValue(),
                Double.valueOf(md.get("5.2: SlowK Period").toString()).intValue(),
                md.get("6: Time Zone").toString()
            );

            List<SimpleIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                SimpleIndicatorUnit indicatorUnit = new SimpleIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("ADOSC")),
                    "ADOSC"
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new ADOSCResponse(indicatorUnits, metaData);
        }
    }


    @Override
    public String toString() {
        return "ADOSCResponse{" +
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
        private int fastKPeriod;
        private int slowKPeriod;
        private String timeZone;
        
        public MetaData(){
            this("", "", "", "", 0, 0,"");
        }

        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            int fastPeriod,
            int slowPeriod,
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastKPeriod = fastPeriod;
            this.slowKPeriod = slowPeriod;
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

        public int getFastKPeriod() {
            return fastKPeriod;
        }

        public int getSlowKPeriod() {
            return slowKPeriod;
        }

        public String getTimeZone() {
            return timeZone;
        }

        @Override
        public String toString() {
            return "MetaData {fastKPeriod=" + fastKPeriod + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed  
                    + ", slowKPeriod=" + slowKPeriod + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

        
    }

}



