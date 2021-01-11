package com.crazzyghost.alphavantage.indicator.response.stochf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class STOCHFResponse {

    private MetaData metaData;
    private List<STOCHFIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private STOCHFResponse(List<STOCHFIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private STOCHFResponse(String errorMessage) {
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<STOCHFIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public static STOCHFResponse of(Map<String, Object> stringObjectMap) {
        Parser<STOCHFResponse> parser = new STOCHFParser();
        return parser.parse(stringObjectMap);
    }

    public static class STOCHFParser extends DefaultParser<STOCHFResponse> {

        @Override
        public STOCHFResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")), 
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")), 
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: FastK Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: FastD Period"))),
                Double.valueOf(String.valueOf(metaDataMap.get("5.3: FastD MA Type"))),
                String.valueOf(metaDataMap.get("6: Time Zone"))
            );

            List<STOCHFIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                STOCHFIndicatorUnit indicatorUnit = new STOCHFIndicatorUnit(e.getKey(),
                        Double.parseDouble(m.get("FastK")), Double.parseDouble(m.get("FastD")));
                indicatorUnits.add(indicatorUnit);
            }
            return new STOCHFResponse(indicatorUnits, metaData);
        }

        @Override
        public STOCHFResponse onParseError(String error) {
            return new STOCHFResponse(error);
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