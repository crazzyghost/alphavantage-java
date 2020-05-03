package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.indicator.request.IndicatorRequest;
import com.crazzyghost.alphavantage.parameters.SeriesType;


public class PeriodicSeriesRequest extends IndicatorRequest{

    private SeriesType series_type;
    private int time_period;

    private PeriodicSeriesRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder>{

        private SeriesType seriesType;
        private int timePeriod;

        public Builder timePeriod(int timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new PeriodicSeriesRequest(this);
        }

    }
}