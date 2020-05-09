package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class IntraDayRequest extends TimeSeriesRequest {

    private Interval interval;
    private OutputSize outputSize;

    private IntraDayRequest(IntraDayRequest.Builder builder){
        super(builder);
        this.interval = builder.interval;
        this.outputSize = builder.outputSize;
    }

    public static class Builder extends TimeSeriesRequest.Builder<Builder>{
 
        private Interval interval = Interval.ONE_MIN;
        private OutputSize outputSize = OutputSize.COMPACT;


        public Builder(){
            super();
            this.function(Function.TIME_SERIES_INTRADAY);
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
