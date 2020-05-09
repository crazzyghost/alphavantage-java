package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class MonthlyRequest extends TimeSeriesRequest {


    public MonthlyRequest(Builder builder){
        super(builder);
    }


    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        public Builder(){
            super();
            this.function(Function.TIME_SERIES_MONTHLY);
        }

        public Builder adjusted(){
            this.function(Function.TIME_SERIES_MONTHLY_ADJUSTED);
            return this;
        }

        @Override
        public MonthlyRequest build() {
            return new MonthlyRequest(this);
        }
    }
}
