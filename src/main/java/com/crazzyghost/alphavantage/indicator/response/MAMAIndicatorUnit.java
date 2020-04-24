package com.crazzyghost.alphavantage.indicator.response;

public class MAMAIndicatorUnit {

    private String date;
    private double famaValue;
    private double mamaValue;

    public MAMAIndicatorUnit(String date, double fama, double mama) {
        this.date = date;
        this.famaValue = fama;
        this.mamaValue = mama;
    }

    public String getDate() {
        return date;
    }

    public double getFamaValue() {
        return famaValue;
    }

    public double getMamaValue() {
        return mamaValue;
    }

    @Override
    public String toString() {
        return "MAMAIndicatorUnit {date=" + date + ", famaValue=" + famaValue + ", mamaValue=" + mamaValue + "}";
    }


    
    
}