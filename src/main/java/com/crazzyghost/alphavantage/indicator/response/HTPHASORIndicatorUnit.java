package com.crazzyghost.alphavantage.indicator.response;

public class HTPHASORIndicatorUnit {
    private String date;
    private double phaseValue;
    private double quadratureValue;

    public HTPHASORIndicatorUnit(String date, double leadSine, double sine){
        this.date = date;
        this.phaseValue = leadSine;
        this.quadratureValue = sine;
    }

    public String getDate() {
        return date;
    }

    public double getLeadSineValue() {
        return phaseValue;
    }

    public double getSineValue() {
        return quadratureValue;
    }

    @Override
    public String toString() {
        return "HTPHASORIndicatorUnit {date=" + date + ", phaseValue=" + phaseValue + ", quadratureValue=" + quadratureValue
                + "}";
    }

    
}