package com.crazzyghost.alphavantage.timeseries.response;

public class MetaData {

    private String information;
    private String symbol;
    private String lastRefreshed;
    private String timeZone;
    private String interval;
    private String outputSize;

    public MetaData(String information, String symbol, String lastRefreshed, String interval, String outputSize, String timeZone) {
        this.information = information;
        this.symbol = symbol;
        this.lastRefreshed = lastRefreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }
    
    public static MetaData empty(){
        return new MetaData(null, null, null, null, null, null);
    }

    public String getInformation() {
        return information;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getOutputSize() {
        return outputSize;
    }

    public String getInterval() {
        return interval;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetaData{");
        sb.append("information=" + information);
        sb.append("symbol=" + symbol);
        sb.append("lastRefreshed=" + lastRefreshed);
        sb.append("timeZone=" + timeZone);
        if(interval != null) sb.append("interval=" + interval);
        if(outputSize != null) sb.append("outputSize=" + outputSize);
        sb.append("}");
        return sb.toString();
    }
}
