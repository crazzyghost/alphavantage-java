package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class MonthlyRequest extends ForexRequest{

    private Function function;

    private MonthlyRequest(Builder builder){
        super(builder);
        this.function = Function.FX_MONTHLY;
    }

    public static class Builder extends ForexRequest.Builder<Builder> {

        public Builder(){
            super();
        }

        @Override
        public MonthlyRequest build() {
            return new MonthlyRequest(this);
        }
    }
}
