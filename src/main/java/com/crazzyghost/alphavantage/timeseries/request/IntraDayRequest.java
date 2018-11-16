package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class IntraDayRequest extends TimeSeriesRequest {

    private Interval interval;
    private OutputSize outputSize;
    private Function function;

    private IntraDayRequest(IntraDayRequest.Builder builder){
        super(builder);
        this.function = Function.TIME_SERIES_INTRADAY;
        this.interval = builder.interval == null ? Interval.ONE_MIN : builder.interval;
        this.outputSize = builder.outputSize == null ? OutputSize.COMPACT : builder.outputSize ;
    }


    public static Builder builder(){
        return new Builder();
    }


    public static class Builder extends TimeSeriesRequest.Builder<Builder>{

        Interval interval;
        OutputSize outputSize;


        public Builder(){
            super();
        }

        public Builder interval(Interval interval){
            this.interval = interval;
            return this;
        }



        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        @Override
        public IntraDayRequest build() {
            return new IntraDayRequest(this);
        }
    }


}
