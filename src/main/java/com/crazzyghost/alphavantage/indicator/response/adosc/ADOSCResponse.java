package com.crazzyghost.alphavantage.indicator.response.adosc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

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
        Parser<ADOSCResponse> parser = new ADOSCParser();
        return parser.parse(stringObjectMap);
    }

    public static class ADOSCParser extends DefaultParser<ADOSCResponse> {

        @Override
        public ADOSCResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
               
            MetaData metaData = new MetaData(
                metaDataMap.get("1: Symbol").toString(),
                metaDataMap.get("2: Indicator").toString(),
                metaDataMap.get("3: Last Refreshed").toString(),
                metaDataMap.get("4: Interval").toString(),
                Double.valueOf(String.valueOf(metaDataMap.get("5.1: FastK Period"))).intValue(),
                Double.valueOf(String.valueOf(metaDataMap.get("5.2: SlowK Period"))).intValue(),
                metaDataMap.get("6: Time Zone").toString()
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

        @Override
        public ADOSCResponse onParseError(String error) {
            return new ADOSCResponse(error);
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



