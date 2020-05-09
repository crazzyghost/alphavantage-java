package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class WeeklyRequest extends TimeSeriesRequest {

   public WeeklyRequest(Builder builder){
        super(builder);
    }

    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        public Builder(){
            super();
            this.function(Function.TIME_SERIES_WEEKLY);
        }

        public Builder adjusted(){
            this.function(Function.TIME_SERIES_WEEKLY_ADJUSTED);
            return this;
        }

        @Override
        public WeeklyRequest build() {
            return new WeeklyRequest(this);
        }
    }
}
