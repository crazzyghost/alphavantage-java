package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.indicator.request.IndicatorRequest;
import com.crazzyghost.alphavantage.parameters.SeriesType;


public class PeriodicalSeriesRequest extends IndicatorRequest{

    private SeriesType series_type;
    private int time_period;

    private PeriodicalSeriesRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder>{

        public SeriesType seriesType;
        public int timePeriod;

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
            return new PeriodicalSeriesRequest(this);
        }

    }
}