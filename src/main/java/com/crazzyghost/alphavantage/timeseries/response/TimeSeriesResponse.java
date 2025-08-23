package com.crazzyghost.alphavantage.timeseries.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class TimeSeriesResponse {

    private final MetaData metaData;
    private final List<StockUnit> stockUnits;
    private final String errorMessage;

    private TimeSeriesResponse(MetaData metaData, List<StockUnit> stockUnits) {
        this.metaData = metaData;
        this.stockUnits = stockUnits;
        this.errorMessage = null;
    }

    private TimeSeriesResponse(String errorMessage){
        this.errorMessage = errorMessage;
        this.stockUnits = new ArrayList<>();
        this.metaData = MetaData.empty();
    }

    public static TimeSeriesResponse of(Map<String, Object> stringObjectMap, boolean adjusted){
        Parser<TimeSeriesResponse> parser = new TimeSeriesParser(adjusted);
        return parser.parse(stringObjectMap);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public List<StockUnit> getStockUnits() {
        return stockUnits;
    }

    public static class TimeSeriesParser extends DefaultParser<TimeSeriesResponse> {

        private boolean adjusted;

        public TimeSeriesParser(boolean adjusted){
            this.adjusted = adjusted;
        }
    
        @Override
        public TimeSeriesResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> dataMap) {
    
            MetaData metaData;
            String information = metaDataMap.get("1. Information");
            String symbol = metaDataMap.get("2. Symbol");
            String lastRefreshed = metaDataMap.get("3. Last Refreshed");
            String interval = null;
            String outputSize = null;
            String timeZone;

            if(metaDataMap.get("4. Interval") == null && metaDataMap.get("4. Output Size") == null){
                timeZone = metaDataMap.get("4. timeZone");
            }else if(metaDataMap.get("4. Interval") == null && metaDataMap.get("4. Output Size") != null){
                outputSize = metaDataMap.get("4. Output Size");
                timeZone = metaDataMap.get("5. Output Size");
            }else {
                interval = metaDataMap.get("4. Interval");
                outputSize = metaDataMap.get("5. Output Size");
                timeZone = metaDataMap.get("6. Time Zone");
            }

            metaData = new MetaData(information, symbol, lastRefreshed, interval, outputSize, timeZone);
            
            List<StockUnit> stockUnits =  new ArrayList<>();
    
            for (Map.Entry<String, Map<String, String>> e : dataMap.entrySet()) {
                Map<String, String> m = e.getValue();
                StockUnit.Builder stockUnit = new StockUnit.Builder();
                stockUnit.time(e.getKey());
                stockUnit.open(Double.parseDouble(m.get("1. open")));
                stockUnit.high(Double.parseDouble(m.get("2. high")));
                stockUnit.low(Double.parseDouble(m.get("3. low")));
                stockUnit.close(Double.parseDouble(m.get("4. close")));
                if (!adjusted) {
                    stockUnit.volume(Long.parseLong(m.get("5. volume")));
                } else {
                    stockUnit.adjustedClose(Double.parseDouble(m.get("5. adjusted close")));
                    stockUnit.volume(Long.parseLong(m.get("6. volume")));
                    stockUnit.dividendAmount(Double.parseDouble(m.get("7. dividend amount")));
                    if (m.get("8. split coefficient") != null){
                        stockUnit.splitCoefficient(Double.parseDouble(m.get("8. split coefficient")));
                    }
                }
                stockUnits.add(stockUnit.build());
            }
            return  new TimeSeriesResponse(metaData, stockUnits);
        }

        @Override
        public TimeSeriesResponse onParseError(String error) {
            return new TimeSeriesResponse(error);
        }

    }


    @Override
    public String toString() {
        return "TimeSeriesResponse{" +
                "metaData=" + metaData +
                ", stockUnits=" + stockUnits +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

