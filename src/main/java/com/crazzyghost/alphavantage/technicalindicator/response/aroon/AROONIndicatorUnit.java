package com.crazzyghost.alphavantage.technicalindicator.response.aroon;

public class AROONIndicatorUnit {

    private String date;
    private double aroonUp;
    private double aroonDown;

    public AROONIndicatorUnit(String date, double aroonUp, double aroonDown) {
        this.date = date;
        this.aroonUp = aroonUp;
        this.aroonDown = aroonDown;
    }

    public String getDate() {
        return date;
    }

    public double getAroonUpValue() {
        return aroonUp;
    }

    public double getAroonDownValue() {
        return aroonDown;
    }

    @Override
    public String toString() {
        return "AROONTechnicalIndicatorUnit {date=" + date + ", aroonUp=" + aroonUp + ", aroonDown=" + aroonDown + "}";
    }

}