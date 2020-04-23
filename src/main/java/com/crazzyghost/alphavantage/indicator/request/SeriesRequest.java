package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.SeriesType;

public class SeriesRequest extends IndicatorRequest {

    private SeriesType series_type;

    private SeriesRequest(Builder builder){
        super(builder);
        this.series_type = builder.seriesType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder>{

        public SeriesType seriesType;
     
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new SeriesRequest(this);
        }

    }
}