package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public abstract class PriceOscillatorResponse {

    protected MetaData metaData;
    protected List<SimpleIndicatorUnit> indicatorUnits;
    protected String errorMessage;

    protected PriceOscillatorResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    protected PriceOscillatorResponse(String errorMessage) {
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

    public static abstract class PriceOscillatorParser<T> extends DefaultParser<T> {

        public PriceOscillatorParser(){}

        @Override
        public T parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")), 
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: Fast Period"))).intValue(),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: Slow Period"))).intValue(),
                Double.valueOf(String.valueOf(metaDataMap.get("5.3: MA Type"))).intValue(), 
                String.valueOf(metaDataMap.get("6: Series Type")),
                String.valueOf(metaDataMap.get("7: Time Zone"))
            );
            
            List<SimpleIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                SimpleIndicatorUnit indicatorUnit = new SimpleIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get(getIndicatorKey())),
                    getIndicatorKey()
                );
                indicatorUnits.add(indicatorUnit);
            }
            return get(indicatorUnits, metaData);
        }

        @Override
        public T onParseError(String error) {
            return get(error);
        }

        public abstract T get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData);
        public abstract T get(String error);
        public abstract String getIndicatorKey();
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



