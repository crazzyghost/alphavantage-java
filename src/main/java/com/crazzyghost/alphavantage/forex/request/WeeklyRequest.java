package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class WeeklyRequest extends ForexRequest{

    private Function function;

    private WeeklyRequest(Builder builder){
        super(builder);
        this.function = Function.FX_WEEKLY;
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends ForexRequest.Builder {

        public Builder(){
            super();
        }

        @Override
        public WeeklyRequest build() {
            return new WeeklyRequest(this);
        }
    }
}
