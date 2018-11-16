package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class MonthlyRequest extends TimeSeriesRequest {

    private Function function;

    public MonthlyRequest(Builder builder){
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
            function = Function.TIME_SERIES_MONTHLY;
        }

        public Builder adjusted(){
            function = Function.TIME_SERIES_MONTHLY_ADJUSTED;
            return this;
        }

        @Override
        public MonthlyRequest build() {
            return new MonthlyRequest(this);
        }
    }
}
