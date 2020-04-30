package com.crazzyghost.alphavantage.indicator.response;

public class STOCHRSIndicatorUnit {

    private String date;
    private double fastKValue;
    private double fastDValue;

    public STOCHRSIndicatorUnit(String date, double fastK, double fastD) {
        this.date = date;
        this.fastKValue = fastK;
        this.fastDValue = fastD;
    }

    public String getDate() {
        return date;
    }

    public double getSlowKValue() {
        return fastKValue;
    }

    public double getSlowDValue() {
        return fastDValue;
    }

    @Override
    public String toString() {
        return "STOCHRSIndicatorUnit {date=" + date + ", fastKValue=" + fastKValue + ", fastDValue=" + fastDValue + "}";
    }
    
}