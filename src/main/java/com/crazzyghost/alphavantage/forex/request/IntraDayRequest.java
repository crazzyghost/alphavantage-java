package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class IntraDayRequest extends ForexRequest {

    private Interval interval;
    private Function function;
    private OutputSize outputsize;

    private IntraDayRequest(Builder builder){
        super(builder);
        this.function = Function.FX_INTRADAY;
        this.outputsize =  builder.outputsize != null ? builder.outputsize : OutputSize.COMPACT;
        this.interval = builder.interval != null ? builder.interval : Interval.ONE_MIN;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends ForexRequest.Builder{

        Interval interval;
        OutputSize outputsize;

        public Builder interval(Interval interval){
            this.interval = interval;
            return this;
        }

        public Builder outputSize(OutputSize outputsize){
            this.outputsize = outputsize;
            return this;
        }

        @Override
        public IntraDayRequest build() {
            return new IntraDayRequest(this);
        }
    }
}
