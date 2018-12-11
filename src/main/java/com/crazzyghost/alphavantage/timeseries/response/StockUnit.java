package com.crazzyghost.alphavantage.timeseries.response;

import java.time.LocalDateTime;

public class StockUnit {

    private double open;
    private double high;
    private double low;
    private double close;
    private double adjustedClose;
    private long volume;
    private double dividendAmount;
    private double splitCoefficient;
    private LocalDateTime dateTime;


    private StockUnit(Builder builder) {
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
        this.adjustedClose = builder.adjustedClose;
        this.volume = builder.volume;
        this.dividendAmount = builder.dividendAmount;
        this.splitCoefficient = builder.splitCoefficient;
        this.dateTime = builder.dateTime;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        double open;
        double high;
        double low;
        double close;
        double adjustedClose;
        long volume;
        double dividendAmount;
        double splitCoefficient;
        LocalDateTime dateTime;

        public Builder open(double open){
            this.open = open;
            return this;
        }

        public Builder high(double high){
            this.high = open;
            return this;
        }
        public Builder low(double low){
            this.low = low;
            return this;
        }

        public Builder close(double close){
            this.close = low;
            return this;
        }
        public Builder adjustedClose(double close){
            this.adjustedClose = close;
            return this;
        }

        public Builder dividendAmount(double dividendAmount){
            this.dividendAmount = dividendAmount;
            return this;
        }

        public Builder volume(long volume){
            this.volume = volume;
            return this;
        }

        public Builder splitCoefficient(double splitCoefficient){
            this.splitCoefficient = splitCoefficient;
            return this;
        }

        public Builder time(LocalDateTime dateTime){
            this.dateTime = dateTime;
            return this;
        }


        public StockUnit build(){
            return new StockUnit(this);
        }
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getAdjustedClose() {
        return adjustedClose;
    }

    public long getVolume() {
        return volume;
    }

    public double getDividendAmount() {
        return dividendAmount;
    }

    public double getSplitCoefficient() {
        return splitCoefficient;
    }

    @Override
    public String toString() {
        return "\n" + "StockUnit{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", adjustedClose=" + adjustedClose +
                ", volume=" + volume +
                ", dividendAmount=" + dividendAmount +
                ", splitCoefficient=" + splitCoefficient +
                ", date=" + dateTime +
                '}';
    }
}
