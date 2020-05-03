package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

public class PriceOscillatorRequest extends IndicatorRequest {

    private SeriesType series_type;
    private int fastPeriod;
    private int slowPeriod;
    private MAType maType; 

    private PriceOscillatorRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
        this.maType = builder.maType;
        this.series_type = builder.seriesType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder> {

        private int fastPeriod = 12;
        private int slowPeriod = 26;
        private MAType maType = MAType.SMA;
        private SeriesType seriesType;

        public Builder fastPeriod(int fastPeriod){
            this.fastPeriod = fastPeriod;
            return this;
        }

        public Builder slowPeriod(int slowPeriod){
            this.slowPeriod = slowPeriod;
            return this;
        }

        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        public Builder maType(MAType maType){
            this.maType = maType;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new PriceOscillatorRequest(this);
        }
        
    }   

}