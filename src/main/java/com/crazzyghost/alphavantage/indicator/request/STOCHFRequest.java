package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.MAType;

public class STOCHFRequest extends IndicatorRequest {

    private int fastKPeriod;
    private int fastDPeriod;
    private MAType fastDMaType;

    protected STOCHFRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod == 0 ? 5 : builder.fastKPeriod;
        this.fastDPeriod = builder.fastDPeriod == 0 ? 3 : builder.fastDPeriod;
        this.fastDMaType = builder.fastDMaType == null ? MAType.SMA : builder.fastDMaType;
    }
    
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        public int fastKPeriod;
        public int fastDPeriod;
        public MAType fastDMaType;
    
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