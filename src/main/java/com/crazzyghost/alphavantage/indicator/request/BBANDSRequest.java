package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.indicator.request.IndicatorRequest;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;


public class BBANDSRequest extends IndicatorRequest{

    private SeriesType series_type;
    private int time_period;
    private int nbdevup;
    private int nbdevdn;
    private MAType maType;

    private BBANDSRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
        this.nbdevdn = builder.nbdevdn;
        this.nbdevup = builder.nbdevup;
        this.maType = builder.maType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder>{

        private SeriesType seriesType;
        private int timePeriod;
        private int nbdevup = 2;
        private int nbdevdn = 2;
        private MAType maType = MAType.SMA;

        public Builder(){
            this.function(Function.BBANDS);
        }

        public Builder timePeriod(int timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        public Builder nbdevup(int nbdevup){
            this.nbdevup = nbdevup;
            return this;
        }

        public Builder nbdevdn(int nbdevdn){
            this.nbdevdn = nbdevdn;
            return this;
        }

        public Builder maType(MAType maType){
            this.maType = maType;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new BBANDSRequest(this);
        }

    }
}