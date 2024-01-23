package com.crazzyghost.alphavantage.technicalindicator.request;

import com.crazzyghost.alphavantage.parameters.SeriesType;

public class SeriesRequest extends TechnicalIndicatorRequest {

    private SeriesType series_type;

    private SeriesRequest(Builder builder) {
        super(builder);
        this.series_type = builder.seriesType;
    }

    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        private SeriesType seriesType;

        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        @Override
        public TechnicalIndicatorRequest build() {
            return new SeriesRequest(this);
        }

    }
}