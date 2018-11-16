package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class DailyRequest extends TimeSeriesRequest{

    private OutputSize outputSize;
    private Function function;

    private DailyRequest(Builder builder){
        super(builder);
        this.function = builder.function;
        this.outputSize = builder.outputSize == null ? OutputSize.COMPACT : builder.outputSize ;
    }


//    @Override
//    public void fetch(Fetcher fetcher) {
//        fetcher.fetch();
//    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        Function function;
        OutputSize outputSize;


        public Builder(){
            super();
            function = Function.TIME_SERIES_DAILY;
        }

        public Builder adjusted(){
            this.function = Function.TIME_SERIES_DAILY_ADJUSTED;
            return this;
        }

        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        @Override
        public DailyRequest build() {
            return new DailyRequest(this);
        }
    }
}
