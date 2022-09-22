package com.crazzyghost.alphavantage.technicalindicator.response;

public class SimpleTechnicalIndicatorUnit {

    String date;
    Double value;
    String indicatorKey;

    public SimpleTechnicalIndicatorUnit(String date, Double value) {
        this.date = date;
        this.value = value;
    }

    public SimpleTechnicalIndicatorUnit(String date, Double value, String indicatorKey) {
        this(date, value);
        this.indicatorKey = indicatorKey;
    }

    public String getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        String key = indicatorKey == null ? "SimpleTechnicalIndicator" : indicatorKey;
        return key + "Unit {date=" + date + ", value=" + value + "}";
    }

}