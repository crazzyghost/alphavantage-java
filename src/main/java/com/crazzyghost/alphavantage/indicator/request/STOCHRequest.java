package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.MAType;

public class STOCHRequest extends IndicatorRequest {

    private int fastKPeriod;
    private int slowKPeriod;
    private int slowDPeriod;
    private MAType slowKMaType;
    private MAType slowDMaType;

    protected STOCHRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod == 0 ? 5 : builder.fastKPeriod;
        this.slowKPeriod = builder.slowKPeriod == 0 ? 3 : builder.slowKPeriod;
        this.slowDPeriod = builder.slowDPeriod == 0 ? 3 : builder.slowDPeriod;
        this.slowDMaType = builder.slowDMaType == null ? MAType.SMA : builder.slowDMaType;
        this.slowKMaType = builder.slowKMaType == null ? MAType.SMA : builder.slowKMaType;
    }
    
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        public int fastKPeriod;
        public int slowKPeriod;
        public int slowDPeriod;
        public MAType slowKMaType;
        public MAType slowDMaType;

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