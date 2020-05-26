package com.crazzyghost.alphavantage.forex.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class ForexResponse {

    private MetaData metaData;
    private List<ForexUnit> forexUnits;
    private String errorMessage;

    private ForexResponse(MetaData metaData, List<ForexUnit> forexUnits) {
        this.metaData = metaData;
        this.forexUnits = forexUnits;
        this.errorMessage = null;
    }

    private ForexResponse(String errorMessage){
        this.metaData = MetaData.empty();
        this.forexUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public List<ForexUnit> getForexUnits() {
        return forexUnits;
    }

    public static ForexResponse of(Map<String, Object> stringObjectMap){
        Parser<ForexResponse> parser = new ForexParser();
        return parser.parse(stringObjectMap);
    }

    public static class ForexParser extends DefaultParser<ForexResponse> {

        @Override
        public ForexResponse onParseError(String error) {
            return new ForexResponse(error);
        }

        @Override
        public ForexResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> units) {

            String information = metaDataMap.get("1. Information");
            String fromSymbol = metaDataMap.get("2. From Symbol");
            String toSymbol = metaDataMap.get("3. To Symbol");
            String lastRefreshed = metaDataMap.getOrDefault("4. Last Refreshed", null);
            String interval = metaDataMap.getOrDefault("5. Interval", null);
            String outputSize = metaDataMap.getOrDefault("6. Output Size", null);
            String timeZone = metaDataMap.getOrDefault("7. Time Zone", null);
 
            if(metaDataMap.get("4. Last Refreshed") == null){
                outputSize = metaDataMap.get("4. Output Size");
                lastRefreshed = metaDataMap.get("5. Last Refreshed");
                timeZone = metaDataMap.get("6. Time Zone");           
            }
           
            MetaData metaData = new MetaData(
                information, 
                fromSymbol, 
                toSymbol, 
                lastRefreshed,
                interval,
                outputSize,
                timeZone
            );

            List<ForexUnit> forexUnits =  new ArrayList<>();

            for (Map.Entry<String,Map<String,String>> e: units.entrySet()) {
                ForexUnit.Builder forexUnit = new ForexUnit.Builder();
                Map<String, String> m = e.getValue();
                forexUnit.date(e.getKey());
                forexUnit.open(Double.parseDouble(m.get("1. open")));
                forexUnit.high(Double.parseDouble(m.get("2. high")));
                forexUnit.low(Double.parseDouble(m.get("3. low")));
                forexUnit.close(Double.parseDouble(m.get("4. close")));
                forexUnits.add(forexUnit.build());
            }
            return  new ForexResponse(metaData, forexUnits);
        }
    }


    @Override
    public String toString() {
        return "ForexResponse{" +
            "metaData=" + metaData +
            ", forexUnits=" + forexUnits.size() +
            ", errorMessage='" + errorMessage + '\'' +
        '}';
    }
}
