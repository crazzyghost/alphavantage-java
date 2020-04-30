package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

public class PriceOscillatorRequest extends IndicatorRequest {

    private SeriesType series_type;
    private double fastPeriod;
    private double slowPeriod;
    private MAType maType; 

    private PriceOscillatorRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod == 0.0 ? 0.1 : builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod == 0.0 ? 0.1 : builder.slowPeriod;
        this.maType = builder.maType == null ? MAType.SMA : builder.maType;
        this.series_type = builder.seriesType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder> {

        public double fastPeriod;
        public double slowPeriod;
        public SeriesType seriesType;
        public MAType maType;

        public Builder fastPeriod(double fastPeriod){
            this.fastPeriod = fastPeriod;
            return this;
        }

        public Builder slowPeriod(double slowPeriod){
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