package com.crazzyghost.alphavantage.timeseries.response;

public class MetaData {

    private String information;
    private String symbol;
    private String lastRefreshed;
    private String timeZone;

    public MetaData(String information, String symbol, String lastRefreshed, String timeZone) {
        this.information = information;
        this.symbol = symbol;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;
    }

    public static MetaData empty(){
        return new MetaData("","","","");
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

    @Override
    public String toString() {
        return "MetaData{" +
                "information='" + information + '\'' +
                ", symbol='" + symbol + '\'' +
                ", lastRefreshed='" + lastRefreshed + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
