package com.crazzyghost.alphavantage.parameters;

public enum Interval {


    ONE_MIN("1min"),
    FIVE_MIN("5min") ,
    FIFTEEN_MIN("15min"),
    THIRTY_MIN("30min"),
    SIXTY_MIN("60min");

    private String interval;

    Interval(String interval){
        this.interval = interval;
    }


    @Override
    public String toString() {
        return this.interval;
    }
}
