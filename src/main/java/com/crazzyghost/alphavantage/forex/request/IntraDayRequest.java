package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class IntraDayRequest extends ForexRequest {

    private Interval interval;
    private Function function;
    private OutputSize outputSize;

    private IntraDayRequest(Builder builder){
        super(builder);
        this.function = Function.FX_INTRADAY;
        this.outputSize =  builder.outputSize != null ? builder.outputSize : OutputSize.COMPACT;
        this.interval = builder.interval != null ? builder.interval : Interval.ONE_MIN;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ForexRequest.Builder{

        Interval interval;
        OutputSize outputSize;

        public Builder interval(Interval interval){
            this.interval = interval;
            return this;
        }

        public Builder outputSize(OutputSize outputSize){
            this.outputSize = outputSize;
            return this;
        }

        @Override
        public IntraDayRequest build() {
            return new IntraDayRequest(this);
        }
    }
}
