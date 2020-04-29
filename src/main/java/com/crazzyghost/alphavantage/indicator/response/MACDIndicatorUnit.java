package com.crazzyghost.alphavantage.indicator.response;

public class MACDIndicatorUnit {
    private String date;
    private double macdHistValue;
    private double macdSignalValue;
    private double macdValue;

    public MACDIndicatorUnit(String date, double macdHistValue, double macdSignalValue, double macdValue) {
        this.date = date;
        this.macdHistValue = macdHistValue;
        this.macdSignalValue = macdSignalValue;
        this.macdValue = macdValue;
    }

    public String getDate() {
        return date;
    }

    public double getMacdHistValue() {
        return macdHistValue;
    }

    public double getMacdSignalValue() {
        return macdSignalValue;
    }

    public double getMacdValue() {
        return macdValue;
    }

    @Override
    public String toString() {
        return "MACDIndicatorUnit {date=" + date + ", macdHistValue=" + macdHistValue + ", macdSignalValue="
                + macdSignalValue + ", macdValue=" + macdValue + "}";
    }

    
}