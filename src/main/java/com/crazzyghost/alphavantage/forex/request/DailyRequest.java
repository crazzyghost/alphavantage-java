package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class DailyRequest extends ForexRequest{

    private Function function;
    private OutputSize outputSize;

    private DailyRequest(Builder builder){
        super(builder);
        this.function = Function.FX_DAILY;
        this.outputSize = builder.outputSize != null ? builder.outputSize : OutputSize.COMPACT;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder extends ForexRequest.Builder{

        Function function;
        OutputSize outputSize;

        public Builder function(Function function){
            this.function = function;
            return this;
        }

        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        @Override
        public ForexRequest build() {
            return new DailyRequest(this);
        }
    }
}
