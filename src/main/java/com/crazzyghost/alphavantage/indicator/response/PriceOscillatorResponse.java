package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PriceOscillatorResponse {

    private MetaData metaData;
    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private PriceOscillatorResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private PriceOscillatorResponse(String errorMessage){
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
    
    public static PriceOscillatorResponse of(Map<String, Object> stringObjectMap, String indicatorKey){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap, indicatorKey);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        PriceOscillatorResponse parse(Map<String, Object> stringObjectMap, String indicatorKey){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String, String>>) stringObjectMap.get(keys.get(1));
            }catch (ClassCastException e){
                return new PriceOscillatorResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                md.get("1: Symbol").toString(),
                md.get("2: Indicator").toString(),
                md.get("3: Last Refreshed").toString(),
                md.get("4: Interval").toString(),
                Double.valueOf(md.get("5.1: Fast Period").toString()).intValue(),
                Double.valueOf(md.get("5.2: Slow Period").toString()).intValue(),
                Double.valueOf(md.get("5.3: MA Type").toString()).intValue(),
                md.get("6: Series Type").toString(),
                md.get("7: Time Zone").toString()
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
            return new PriceOscillatorResponse(indicatorUnits, metaData);
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
        private int fastPeriod;
        private int slowPeriod;
        private int maType;
        private String seriesType;
        private String timeZone;
        
        public MetaData(){
            this("", "", "", "", 0, 0, 0, "","");
        }

        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            int fastPeriod,
            int slowPeriod,
            int maType,
            String seriesType,
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastPeriod = fastPeriod;
            this.slowPeriod = slowPeriod;
            this.maType = maType;
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

        public int getFastPeriod() {
            return fastPeriod;
        }

        public int getSlowPeriod() {
            return slowPeriod;
        }

        public int getMaType() {
            return maType;
        }

        public String getSeriesType() {
            return seriesType;
        }

        public String getTimeZone() {
            return timeZone;
        }

        @Override
        public String toString() {
            return "MetaData {fastPeriod=" + fastPeriod + ", indicator=" + indicator + ", interval=" + interval
                    + ", lastRefreshed=" + lastRefreshed + ", maType=" + maType + ", seriesType=" + seriesType
                    + ", slowPeriod=" + slowPeriod + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

        
    }

}



