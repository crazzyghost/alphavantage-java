package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class ULTOSCRequest extends IndicatorRequest{

    private int timePeriod1;
    private int timePeriod2;
    private int timePeriod3; 

    protected ULTOSCRequest(Builder builder) {
        super(builder);
        this.timePeriod1 = builder.timePeriod1;
        this.timePeriod2 = builder.timePeriod2;
        this.timePeriod3 = builder.timePeriod3;
    }

    public static class Builder extends IndicatorRequest.Builder<Builder> {

        public int timePeriod1 = 7;
        public int timePeriod2 = 14;
        public int timePeriod3 = 28; 

        public Builder(){
            this.function(Function.ULTOSC);
        }

        public Builder timePeriod1(int period){
            this.timePeriod1 = period;
            return this;
        }
        
        public Builder timePeriod2(int period){
            this.timePeriod2 = period;
            return this;
        }

        public Builder timePeriod3(int period){
            this.timePeriod3 = period;
            return this;
        }

        @Override
        public IndicatorRequest build(){
            return new ULTOSCRequest(this);
        }
    }

}