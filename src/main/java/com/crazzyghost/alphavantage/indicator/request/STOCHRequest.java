package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;

public class STOCHRequest extends IndicatorRequest {

    private int fastKPeriod;
    private int slowKPeriod;
    private int slowDPeriod;
    private MAType slowKMaType;
    private MAType slowDMaType;

    protected STOCHRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.slowKPeriod = builder.slowKPeriod;
        this.slowDPeriod = builder.slowDPeriod;
        this.slowDMaType = builder.slowDMaType;
        this.slowKMaType = builder.slowKMaType;
    }
    
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        private int fastKPeriod = 5;
        private int slowKPeriod = 3;
        private int slowDPeriod = 3;
        private MAType slowKMaType = MAType.SMA;
        private MAType slowDMaType = MAType.SMA;

        public Builder() {
            this.function(Function.STOCH);
        }

        public Builder fastKPeriod(int fastKPeriod){
            this.fastKPeriod = fastKPeriod;
            return this;
        }

        public Builder slowKPeriod(int slowKPeriod){
            this.slowKPeriod = slowKPeriod;
            return this;
        }

        public Builder slowDPeriod(int slowDPeriod){
            this.slowDPeriod = slowDPeriod;
            return this;
        }

        public Builder slowKMaType(MAType type){
            this.slowKMaType = type;
            return this;
        }

        public Builder slowDMaType(MAType type){
            this.slowDMaType = type;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new STOCHRequest(this);
        }
        
    }   
}