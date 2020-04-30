package com.crazzyghost.alphavantage.indicator.response;

public class STOCHIndicatorUnit {

    private String date;
    private double slowKValue;
    private double slowDValue;

    public STOCHIndicatorUnit(String date, double slowK, double slowD) {
        this.date = date;
        this.slowKValue = slowK;
        this.slowDValue = slowD;
    }

    public String getDate() {
        return date;
    }

    public double getSlowKValue() {
        return slowKValue;
    }

    public double getSlowDValue() {
        return slowDValue;
    }

    @Override
    public String toString() {
        return "STOCHIndicatorUnit {date=" + date + ", slowKValue=" + slowKValue + ", slowDValue=" + slowDValue + "}";
    }


    
    
}