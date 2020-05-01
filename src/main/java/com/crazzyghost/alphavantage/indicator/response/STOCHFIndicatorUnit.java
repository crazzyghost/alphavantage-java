package com.crazzyghost.alphavantage.indicator.response;

public class STOCHFIndicatorUnit {

    private String date;
    private double fastKValue;
    private double fastDValue;

    public STOCHFIndicatorUnit(String date, double fastK, double fastD) {
        this.date = date;
        this.fastKValue = fastK;
        this.fastDValue = fastD;
    }

    public String getDate() {
        return date;
    }

    public double getFastKValue() {
        return fastKValue;
    }

    public double getFastDValue() {
        return fastDValue;
    }

    @Override
    public String toString() {
        return "STOCHFIndicatorUnit {date=" + date + ", fastKValue=" + fastKValue + ", fastDValue=" + fastDValue + "}";
    }


    
    
}