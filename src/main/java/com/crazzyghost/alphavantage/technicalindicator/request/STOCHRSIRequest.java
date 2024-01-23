package com.crazzyghost.alphavantage.technicalindicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

public class STOCHRSIRequest extends TechnicalIndicatorRequest {

    private int time_period;
    private SeriesType series_type;
    private int fastKPeriod;
    private int fastDPeriod;
    private MAType fastDMaType;

    protected STOCHRSIRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.fastDPeriod = builder.fastDPeriod;
        this.fastDMaType = builder.fastDMaType;
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
    }

    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        private int fastKPeriod = 5;
        private int fastDPeriod = 3;
        private MAType fastDMaType = MAType.SMA;
        private int timePeriod;
        private SeriesType seriesType;

        public Builder() {
            this.function(Function.STOCHRSI);
        }

        public Builder fastKPeriod(int fastKPeriod) {
            this.fastKPeriod = fastKPeriod;
            return this;
        }

        public Builder fastDPeriod(int fastDPeriod) {
            this.fastDPeriod = fastDPeriod;
            return this;
        }

        public Builder fastDMaType(MAType type) {
            this.fastDMaType = type;
            return this;
        }

        public Builder timePeriod(int timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        public Builder seriesType(SeriesType seriesType) {
            this.seriesType = seriesType;
            return this;
        }

        @Override
        public TechnicalIndicatorRequest build() {
            return new STOCHRSIRequest(this);
        }

    }
}