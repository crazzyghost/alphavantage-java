package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;

public class STOCHFRequest extends IndicatorRequest {

    private int fastKPeriod;
    private int fastDPeriod;
    private MAType fastDMaType;

    protected STOCHFRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.fastDPeriod = builder.fastDPeriod;
        this.fastDMaType = builder.fastDMaType;
    }
    
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        private int fastKPeriod = 5;
        private int fastDPeriod = 3;
        private MAType fastDMaType = MAType.SMA;

        public Builder() {
            this.function(Function.STOCHF);
        }
    
        public Builder fastKPeriod(int fastKPeriod){
            this.fastKPeriod = fastKPeriod;
            return this;
        }

        public Builder fastDPeriod(int fastDPeriod){
            this.fastDPeriod = fastDPeriod;
            return this;
        }

        public Builder fastDMaType(MAType type){
            this.fastDMaType = type;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new STOCHFRequest(this);
        }
        
    }   
}