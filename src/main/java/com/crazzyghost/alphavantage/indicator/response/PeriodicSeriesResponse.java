package com.crazzyghost.alphavantage.indicator.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PeriodicSeriesResponse {

    private List<SimpleIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private PeriodicSeriesResponse(List<SimpleIndicatorUnit> indicatorUnits) {
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private PeriodicSeriesResponse(String errorMessage){
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

    public void setForexUnits(List<SimpleIndicatorUnit> indicatorUnits) {
        this.indicatorUnits = indicatorUnits;
    }

    public static PeriodicSeriesResponse of(Map<String, Object> stringObjectMap, String indicatorKey){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap, indicatorKey);
    }

    public static class Parser {

        PeriodicSeriesResponse parse(Map<String, Object> stringObjectMap, String indicatorKey){

            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            // Map<String, String> md;
            Map<String, Map<String, String>> indicatorData;

            try{
                // md = (Map<String, String>) stringObjectMap.get(keys.get(0));
                indicatorData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));

            }catch (ClassCastException e){
                return new PeriodicSeriesResponse((String)stringObjectMap.get(keys.get(0)));
            }


            // MetaData metaData = new MetaData(
            //         md.get("1. Information"),
            //         md.get("2. From Symbol"),
            //         md.get("3. To Symbol"),
            //         md.get("4. Last Refreshed"),
            //         md.get("5. Interval"),
            //         md.get("6. Output Size"),
            //         md.get("7. Time Zone")
            // );

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
            return new PeriodicSeriesResponse(indicatorUnits);
        }
    }


    @Override
    public String toString() {
        return "ForexResponse{" +
                // "metaData=" + metaData +
                "indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

}



// public class MetaData {

//     private String symbol;
//     private String indicator;
//     private String lastRefreshed;
//     private String interval;
//     private String timeZone;

// }