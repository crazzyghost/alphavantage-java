package com.crazzyghost.alphavantage.indicator.request;


public class PeriodicalRequest extends IndicatorRequest{

    private int time_period;

    private PeriodicalRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder>{

        public int timePeriod;

        public Builder timePeriod(int timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new PeriodicalRequest(this);
        }

    }

}