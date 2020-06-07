package com.crazzyghost.alphavantage.sector;

import java.util.HashMap;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;

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

    public static class SectorParser extends DefaultParser<SectorResponse> {

        @Override
        public SectorResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> data) {
            
            MetaData metaData = new MetaData(
                String.valueOf(metaDataMap.get("Information")),
                String.valueOf(metaDataMap.get("Last Refreshed"))
            );

            Map<String, SectorUnit> sectorUnits = new HashMap<>();

            for(Map.Entry<String, Map<String, String>> e: data.entrySet()){
                String sectorDescription = e.getKey();
                Map<String, String> sectorData = e.getValue();
                SectorUnit sectorUnit = new SectorUnit(
                    sectorData.get("Information Technology"), 
                    sectorData.get("consumerDiscretionary"), 
                    sectorData.get("healthCare"), 
                    sectorData.get("communicationServices"), 
                    sectorData.get("realEstate"), 
                    sectorData.get("utilities"), 
                    sectorData.get("financials"), 
                    sectorData.get("materials"), 
                    sectorData.get("industrials"), 
                    sectorData.get("consumerStaples"), 
                    sectorData.get("energy")
                );
                sectorUnits.put(sectorDescription, sectorUnit);
            }

            return new SectorResponse(metaData, sectorUnits);
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

    }
}