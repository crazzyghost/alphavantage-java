package com.crazzyghost.alphavantage.forex.response;

public class ForexUnit {

    private double open;
    private double high;
    private double low;
    private double close;

    private ForexUnit(Builder builder) {
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        double open;
        double high;
        double low;
        double close;

        public Builder open(double open){
            this.open = open;
            return this;
        }

        public Builder high(double high){
            this.high = high;
            return this;
        }
        public Builder low(double low){
            this.low = low;
            return this;
        }

        public Builder close(double close){
            this.close = close;
            return this;
        }

        public ForexUnit build(){
            return new ForexUnit(this);
        }

    }

    @Override
    public String toString() {
        return "ForexUnit{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                '}';
    }
}
