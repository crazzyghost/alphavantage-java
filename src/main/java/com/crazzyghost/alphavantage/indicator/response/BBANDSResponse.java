package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BBANDSResponse {

    private MetaData metaData;
    private List<BBANDSIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private BBANDSResponse(List<BBANDSIndicatorUnit> indicatorUnits, MetaData metaData){
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private BBANDSResponse(String errorMessage){
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<BBANDSIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }
    
    public MetaData getMetaData() {
        return metaData;
    }
    
    public static BBANDSResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public static class Parser {

        @SuppressWarnings("unchecked")
        BBANDSResponse parse(Map<String, Object> stringObjectMap){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, Object> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                md = (Map<String, Object>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));
            }catch (ClassCastException e){
                return new BBANDSResponse((String)stringObjectMap.get(keys.get(0)));
            }

            MetaData metaData = new MetaData(
                String.valueOf(md.get("1: Symbol")),
                String.valueOf(md.get("2: Indicator")),
                String.valueOf(md.get("3: Last Refreshed")),
                String.valueOf(md.get("4: Interval")),
                Double.valueOf(md.get("5: Time Period").toString()).intValue(),
                Double.valueOf(md.get("6.1: Deviation multiplier for upper band").toString()).intValue(),
                Double.valueOf(md.get("6.2: Deviation multiplier for lower band").toString()).intValue(),
                Double.valueOf(md.get("6.3: MA Type").toString()).intValue(),                
                String.valueOf(md.get("7: Series Type")),
                String.valueOf(md.get("8: Time Zone"))
            );

            List<BBANDSIndicatorUnit> indicatorUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();     
                BBANDSIndicatorUnit indicatorUnit = new BBANDSIndicatorUnit(
                    e.getKey(),
                    Double.parseDouble(m.get("Real Upper Band").toString()),
                    Double.parseDouble(m.get("Real Lower Band").toString()),
                    Double.parseDouble(m.get("Real Middle Band").toString())
                );
                indicatorUnits.add(indicatorUnit);
            }
            return new BBANDSResponse(indicatorUnits, metaData);
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
        private int timePeriod;
        private int nbdevup;
        private int nbdevdn;
        private int maType;
        private String seriesType;
        private String timeZone;

        public MetaData(){
            this("", "", "", "", 0, 0, 0, 0, "", "");
        }

        public MetaData(
            String symbol, 
            String indicator, 
            String lastRefreshed, 
            String interval, 
            int timePeriod,
            int nbdevup,
            int nbdevdn,
            int maType,
            String seriesType, 
            String timeZone
        ) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.timePeriod = timePeriod;
            this.nbdevup = nbdevup;
            this.nbdevdn = nbdevdn;
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

        public String getTimeZone() {
            return timeZone;
        }

        public String getSeriesType() {
            return seriesType;
        }

        public int getTimePeriod() {
            return timePeriod;
        }

        public int getMaType() {
            return maType;
        }

        public int getNbdevup() {
            return nbdevup;
        }

        public int getNbdevdn() {
            return nbdevdn;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", maType=" + maType + ", nbdevdn=" + nbdevdn + ", nbdevup=" + nbdevup + ", seriesType="
                    + seriesType + ", symbol=" + symbol + ", timePeriod=" + timePeriod + ", timeZone=" + timeZone + "}";
        }
        
        
        
    }


}



