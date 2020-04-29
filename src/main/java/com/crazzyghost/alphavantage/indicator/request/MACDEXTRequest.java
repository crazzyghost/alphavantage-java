package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

public class MACDEXTRequest extends IndicatorRequest{

    private SeriesType series_type;
    private int fastPeriod;
    private int slowPeriod;
    private int signalPeriod;
    private MAType fastMaType;
    private MAType slowMaType;
    private MAType signalMaType;

    private MACDEXTRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod == 0 ? 12: builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod == 0 ? 26: builder.slowPeriod;
        this.signalPeriod = builder.signalPeriod == 0 ? 9: builder.signalPeriod;
        this.fastMaType = builder.fastMaType == null ? MAType.SMA: builder.fastMaType;
        this.slowMaType = builder.slowMaType == null ? MAType.SMA: builder.slowMaType;
        this.signalMaType = builder.signalMaType == null ? MAType.SMA: builder.signalMaType;
        this.series_type = builder.seriesType;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder> {

        public int fastPeriod;
        public int slowPeriod;
        public int signalPeriod;
        private MAType fastMaType;
        private MAType slowMaType;
        private MAType signalMaType;    
        public SeriesType seriesType;

        public Builder fastPeriod(int fastPeriod){
            this.fastPeriod = fastPeriod;
            return this;
        }

        public Builder slowPeriod(int slowPeriod){
            this.slowPeriod = slowPeriod;
            return this;
        }

        public Builder signalPeriod(int signalPeriod){
            this.signalPeriod = signalPeriod;
            return this;
        }

        public Builder fastMaType(MAType type){
            this.fastMaType = type;
            return this;
        }

        public Builder slowMaType(MAType type){
            this.slowMaType = type;
            return this;
        }

        public Builder signalMaType(MAType type){
            this.signalMaType = type;
            return this;
        }

        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        @Override
        public IndicatorRequest build() {
            return new MACDEXTRequest(this);
        }
        
    }   
}