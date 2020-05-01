package com.crazzyghost.alphavantage.indicator.response;

public class SimpleIndicatorUnit {

    String date;
    Double value;
    String indicatorKey;

	public SimpleIndicatorUnit(String date, Double value) {
        this.date = date;
        this.value = value;
    }

    public SimpleIndicatorUnit(String date, Double value, String indicatorKey) {
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
        String key = indicatorKey == null ? "SimpleIndicator" : indicatorKey; 
        return key + "Unit {date=" + date + ", value=" + value + "}";
    }
    
    
}