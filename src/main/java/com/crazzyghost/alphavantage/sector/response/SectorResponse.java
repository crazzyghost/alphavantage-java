package com.crazzyghost.alphavantage.sector.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.Parser;

/**
 * @author crazzyghost
 * @since 1.4.0
 * A sector performance response
 */
public final class SectorResponse {

    private MetaData metaData;
    private Map<String, SectorUnit> sectorUnits;
    private String errorMessage;

    private SectorResponse(MetaData metaData, Map<String, SectorUnit> sectorUnits){
        this.metaData = metaData;
        this.sectorUnits = sectorUnits;
    }

    private SectorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public static SectorResponse of(Map<String, Object> stringObjectMap){
        Parser<SectorResponse> parser = new SectorParser();
        return parser.parse(stringObjectMap);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public SectorUnit getRealTimePerformance(){
        return sectorUnits.getOrDefault("Rank A: Real-Time Performance", null);
    }

    public SectorUnit getOneDayPerformance(){
        return sectorUnits.getOrDefault("Rank B: 1 Day Performance", null);
    }

    public SectorUnit getFiveDayPerformance(){
        return sectorUnits.getOrDefault("Rank C: 5 Day Performance", null);
    }

    public SectorUnit getOneMonthPerformance(){
        return sectorUnits.getOrDefault("Rank D: 1 Month Performance", null);
    }

    public SectorUnit getThreeMonthPerformance(){
        return sectorUnits.getOrDefault("Rank E: 3 Month Performance", null);
    }

    public SectorUnit getYearToDatePerformance(){
        return sectorUnits.getOrDefault("Rank F: Year-to-Date (YTD) Performance", null);
    }

    public SectorUnit getOneYearPerformance(){
        return sectorUnits.getOrDefault("Rank G: 1 Year Performance", null);
    }

    public SectorUnit getThreeYearPerformance(){
        return sectorUnits.getOrDefault("Rank H: 3 Year Performance", null);
    }

    public SectorUnit getFiveYearPerformance(){
        return sectorUnits.getOrDefault("Rank I: 5 Year Performance", null);
    }

    public SectorUnit getTenYearPerformance(){
        return sectorUnits.getOrDefault("Rank J: 10 Year Performance", null);
    }



    public static class SectorParser extends Parser<SectorResponse> {

        @SuppressWarnings("unchecked")
        @Override
        public SectorResponse parse(Map<String, Object> stringObjectMap) {
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API.");
            } else {

                try {
                    Map<String, String> metaDataMap = (Map<String, String>) stringObjectMap.get(keys.get(0));

                    MetaData metaData = new MetaData(
                            String.valueOf(metaDataMap.get("Information")),
                            String.valueOf(metaDataMap.get("Last Refreshed"))
                    );

                    keys.remove(0);

                    Map<String, SectorUnit> sectorUnits = new HashMap<>();
                    for (String sectorDescription : keys) {
                        Map<String, String> sectorData = (Map<String, String>) stringObjectMap.get(sectorDescription);
                        SectorUnit sectorUnit = new SectorUnit(
                                sectorData.get("Information Technology"),
                                sectorData.get("Consumer Discretionary"),
                                sectorData.get("Health Care"),
                                sectorData.get("Communication Services"),
                                sectorData.get("Real Estate"),
                                sectorData.get("Utilities"),
                                sectorData.get("Financials"),
                                sectorData.get("Materials"),
                                sectorData.get("Industrials"),
                                sectorData.get("Consumer Staples"),
                                sectorData.get("Energy")
                        );
                        sectorUnits.put(sectorDescription, sectorUnit);
                    }

                    return new SectorResponse(metaData, sectorUnits);

                } catch (ClassCastException e) {
                    return onParseError(stringObjectMap.get(keys.get(0)).toString());
                }
            }

        }

        @Override
        public SectorResponse onParseError(String error) {
            return new SectorResponse(error);
        }

    }

    public static final class MetaData {

        private String information;
        private String lastRefreshed;

        public MetaData(String information, String lastRefreshed) {
            this.information = information;
            this.lastRefreshed = lastRefreshed;
        }

        public String getInformation() {
            return information;
        }

        public String getLastRefreshed() {
            return lastRefreshed;
        }

        @Override
        public String toString() {
            return "MetaData {information=" + information + ", lastRefreshed=" + lastRefreshed + "}";
        }

    }

    @Override
    public String toString() {
        return "SectorResponse {errorMessage=" + errorMessage + ", metaData=" + metaData + ", sectorUnits="
                + sectorUnits + "}";
    }
}
