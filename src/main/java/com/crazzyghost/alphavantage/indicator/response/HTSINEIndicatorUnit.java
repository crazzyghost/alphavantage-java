package com.crazzyghost.alphavantage.indicator.response;

public class HTSINEIndicatorUnit {
    private String date;
    private double leadSineValue;
    private double sineValue;

    public HTSINEIndicatorUnit(String date, double leadSine, double sine){
        this.date = date;
        this.leadSineValue = leadSine;
        this.sineValue = sine;
    }

    public String getDate() {
        return date;
    }

    public double getLeadSineValue() {
        return leadSineValue;
    }

    public double getSineValue() {
        return sineValue;
    }

    @Override
    public String toString() {
        return "HTSINEIndicatorUnit {date=" + date + ", leadSineValue=" + leadSineValue + ", sineValue=" + sineValue
                + "}";
    }

    
}