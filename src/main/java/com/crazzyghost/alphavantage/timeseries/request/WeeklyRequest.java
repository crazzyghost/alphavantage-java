package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class WeeklyRequest extends TimeSeriesRequest {


    private Function function;

    public WeeklyRequest(Builder builder){
        super(builder);
        this.function  = builder.function;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        private Function function;

        public Builder(){
            super();
            function = Function.TIME_SERIES_WEEKLY;
        }

        public Builder adjusted(){
            function = Function.TIME_SERIES_WEEKLY_ADJUSTED;
            return this;
        }

        @Override
        public WeeklyRequest build() {
            return new WeeklyRequest(this);
        }
    }
}
