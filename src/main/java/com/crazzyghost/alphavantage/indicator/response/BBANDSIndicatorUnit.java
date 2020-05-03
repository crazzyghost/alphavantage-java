package com.crazzyghost.alphavantage.indicator.response;

public class BBANDSIndicatorUnit {
    private String date;
    private double realUpperBandValue;
    private double realLowerBandValue;
    private double realMiddleBandValue;

    public BBANDSIndicatorUnit(String date, double realUpperBandValue, double realLowerBandValue, double realMiddleBandValue) {
        this.date = date;
        this.realUpperBandValue = realUpperBandValue;
        this.realLowerBandValue = realLowerBandValue;
        this.realMiddleBandValue = realMiddleBandValue;
    }
    
    public String getDate() {
        return date;
    }

    public double getRealUpperBandValue() {
        return realUpperBandValue;
    }
    
    public double getRealLowerBandValue() {
        return realLowerBandValue;
    }
    
    public double getRealMiddleBandValue() {
        return realMiddleBandValue;
    }

    @Override
    public String toString() {
        return "BBANDSIndicatorUnit {date=" + date + ", realLowerBandValue=" + realLowerBandValue
                + ", realMiddleBandValue=" + realMiddleBandValue + ", realUpperBandValue=" + realUpperBandValue + "}";
    }
 
    
}