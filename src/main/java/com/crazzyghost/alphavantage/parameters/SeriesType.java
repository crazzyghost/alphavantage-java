package com.crazzyghost.alphavantage.parameters;

public enum SeriesType {
    OPEN("open"),
    HIGH("high"),
    CLOSE("close"),
    LOW("low");

    private final String seriesType;

    SeriesType(String seriesType){
        this.seriesType = seriesType;
    }

    @Override
    public String toString() {
        return seriesType;
    }
}