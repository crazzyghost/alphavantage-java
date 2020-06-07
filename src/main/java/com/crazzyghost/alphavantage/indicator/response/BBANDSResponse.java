package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

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
        Parser<BBANDSResponse> parser = new BBANDSParser();
        return parser.parse(stringObjectMap);
    }

    public static class BBANDSParser extends DefaultParser<BBANDSResponse>{

        @Override
        public BBANDSResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("1: Symbol")),
                String.valueOf(metaDataMap.get("2: Indicator")),
                String.valueOf(metaDataMap.get("3: Last Refreshed")),
                String.valueOf(metaDataMap.get("4: Interval")),
                Double.valueOf(String.valueOf(metaDataMap.get("5: Time Period"))).intValue(),
                Double.valueOf(String.valueOf(metaDataMap.get("6.1: Deviation multiplier for upper band"))).intValue(),
                Double.valueOf(String.valueOf(metaDataMap.get("6.2: Deviation multiplier for lower band"))).intValue(),
                Double.valueOf(String.valueOf(metaDataMap.get("6.3: MA Type"))).intValue(),                
                String.valueOf(metaDataMap.get("7: Series Type")),
                String.valueOf(metaDataMap.get("8: Time Zone"))
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

        @Override
        public BBANDSResponse onParseError(String error) {
            return new BBANDSResponse(error);
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



