package com.crazzyghost.alphavantage.cryptocurrency.output;

public class CryptoUnit {


    private double open;
    private double close;
    private double high;
    private double low;
    private double openUSD;
    private double closeUSD;
    private double highUSD;
    private double lowUSD;
    private double volume;
    private double marketCap;

    public CryptoUnit(Builder builder) {
        this.open       = builder.open;
        this.close      = builder.close;
        this.high       = builder.high;
        this.low        = builder.low;
        this.openUSD    = builder.openUSD;
        this.closeUSD   = builder.closeUSD;
        this.highUSD    = builder.highUSD;
        this.lowUSD     = builder.lowUSD;
        this.volume     = builder.volume;
        this.marketCap  = builder.marketCap;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        double open;
        double high;
        double low;
        double close;
        double openUSD;
        double closeUSD;
        double highUSD;
        double lowUSD;
        double volume;
        double marketCap;

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

        public Builder openUSD(double open){
            this.openUSD = open;
            return this;
        }

        public Builder highUSD(double high){
            this.highUSD = high;
            return this;
        }
        public Builder lowUSD(double low){
            this.lowUSD = low;
            return this;
        }

        public Builder closeUSD(double close){
            this.closeUSD = close;
            return this;
        }

        public Builder marketCap(double marketCap){
            this.closeUSD = marketCap;
            return this;
        }

        public Builder volume(double volume){
            this.volume = volume;
            return this;
        }



        public CryptoUnit build(){
            return new CryptoUnit(this);
        }

    }


}
