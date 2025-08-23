package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class IntraDayRequest extends TimeSeriesRequest {

    private Interval interval;
    private OutputSize outputSize;
    private boolean adjusted;
    private boolean extended_hours;
    private String month;

    private IntraDayRequest(Builder builder){
        super(builder);
        this.interval = builder.interval;
        this.outputSize = builder.outputSize;
        this.adjusted = builder.adjusted;
        this.extended_hours = builder.extendedHours;
        this.month = builder.month;
    }

    public static class Builder extends TimeSeriesRequest.Builder<Builder>{
 
        private Interval interval = Interval.ONE_MIN;
        private OutputSize outputSize = OutputSize.COMPACT;
        private boolean adjusted = false;
        private boolean extendedHours = false;
        private String month;

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

        public Builder adjusted(){
            this.adjusted = true;
            return this;
        }

        public Builder extendedHours(){
            this.extendedHours = true;
            return this;
        }

        public Builder month(String month){
            this.month = month;
            return this;
        }

        @Override
        public IntraDayRequest build() {
            return new IntraDayRequest(this);
        }
    }


}
