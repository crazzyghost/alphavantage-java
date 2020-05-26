package com.crazzyghost.alphavantage.forex.response;

public class MetaData {

    private String information;
    private String fromSymbol;
    private String toSymbol;
    private String lastRefreshed;
    private String interval;
    private String outputSize;
    private String timeZone;

    public MetaData(
        String information,
        String fromSymbol,
        String toSymbol,
        String lastRefreshed,
        String interval,
        String outputSize,
        String timeZone
    ) {
        this.information = information;
        this.fromSymbol = fromSymbol;
        this.toSymbol = toSymbol;
        this.lastRefreshed = lastRefreshed;
        this.interval = interval;
        this.outputSize = outputSize;
        this.timeZone = timeZone;
    }

    public static MetaData empty(){
        return new MetaData(null, null, null, null, null, null, null);
    }

    public String getInformation() {
        return information;
    }

    public String getFromSymbol() {
        return fromSymbol;
    }

    public String getToSymbol() {
        return toSymbol;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getInterval() {
        return interval;
    }

    public String getOutputSize() {
        return outputSize;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetaData{");
        sb.append("information=" + information);
        sb.append("fromSymbol=" + fromSymbol);
        sb.append("toSymbol=" + toSymbol);
        sb.append("lastRefreshed=" + lastRefreshed);
        sb.append("timeZone=" + timeZone);
        if (outputSize != null) sb.append("outputSize=" + outputSize);
        if (interval != null) sb.append("interval=" + interval);
        sb.append("lastRefreshed=" + information);
        return sb.toString();
    }
}
