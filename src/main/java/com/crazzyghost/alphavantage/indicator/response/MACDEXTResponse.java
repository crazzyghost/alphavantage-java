package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MACDEXTResponse {

    private MetaData metaData;
    private List<MACDIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private MACDEXTResponse(List<MACDIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private MACDEXTResponse(String errorMessage){
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

    public List<MACDIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public void setIndicatorUnits(List<MACDIndicatorUnit> indicatorUnits) {
        this.indicatorUnits = indicatorUnits;
    }

    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static MACDEXTResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        MACDEXTResponse parse(Map<String, Object> stringObjectMap){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));

            }catch (ClassCastException e){
                return new MACDEXTResponse((String)stringObjectMap.get(keys.get(0)));
            }

            
            MetaData metaData = new MetaData(
                String.valueOf(md.get("1: Symbol")),
                String.valueOf(md.get("2: Indicator")),
                String.valueOf(md.get("3: Last Refreshed")),
                String.valueOf(md.get("4: Interval")),
                Double.valueOf(String.valueOf(md.get("5.1: Fast Period"))),
                Double.valueOf(String.valueOf(md.get("5.2: Slow Period"))),
                Double.valueOf(String.valueOf(md.get("5.3: Signal Period"))),
                Double.valueOf(String.valueOf(md.get("5.4: Fast MA Type"))),
                Double.valueOf(String.valueOf(md.get("5.5: Slow MA Type"))),
                Double.valueOf(String.valueOf(md.get("5.6: Signal MA Type"))),
                String.valueOf(md.get("6: Series Type")),
                String.valueOf(md.get("7: Time Zone"))            
            );

            List<MACDIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                MACDIndicatorUnit indicatorUnit = new MACDIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("MACD_Hist")),
                    Double.parseDouble(m.get("MACD_Signal")),
                    Double.parseDouble(m.get("MACD"))
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new MACDEXTResponse(indicatorUnits, metaData);
        }
    }


    @Override
    public String toString() {
        return "MACDResponse{" +
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
        private double fastPeriod;
        private double slowPeriod;
        private double signalPeriod;
        private double fastMaType;
        private double slowMaType;
        private double signalMaType;
        private String timeZone;
        private String seriesType;
        
        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            double fastPeriod,
            double slowPeriod,
            double signalPeriod, 
            double fastMaType,
            double slowMaType,
            double signalMaType,
            String seriesType, 
            String timeZone 
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.fastPeriod = fastPeriod;
            this.slowPeriod = slowPeriod;
            this.signalPeriod = signalPeriod;
            this.fastMaType = fastMaType;
            this.slowMaType = slowMaType;
            this.signalMaType = signalMaType;
            this.seriesType = seriesType;
            this.timeZone = timeZone;
        }
        
        public MetaData(){
            this("", "", "", "", 12, 26, 9, 0, 0, 0,"", "");
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

        public double getFastPeriod() {
            return fastPeriod;
        }

        public double getSlowPeriod() {
            return slowPeriod;
        }

        public double getSignalPeriod() {
            return signalPeriod;
        }

        /**
         * @return the fastMaType
         */
        public double getFastMaType() {
            return fastMaType;
        }

        /**
         * @return the slowMaType
         */
        public double getSlowMaType() {
            return slowMaType;
        }

        /**
         * @return the signalMaType
         */
        public double getSignalMaType() {
            return signalMaType;
        }

        @Override
        public String toString() {
            return "MetaData {fastMaType=" + fastMaType + ", fastPeriod=" + fastPeriod + ", indicator=" + indicator
                    + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed + ", seriesType=" + seriesType
                    + ", signalMaType=" + signalMaType + ", signalPeriod=" + signalPeriod + ", slowMaType=" + slowMaType
                    + ", slowPeriod=" + slowPeriod + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }
        
    }
}