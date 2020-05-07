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
        this.outputsize =  builder.outputsize;
        this.interval = builder.interval;
    }

    public static class Builder extends ForexRequest.Builder<Builder>{

        Interval interval = Interval.ONE_MIN;
        OutputSize outputsize = OutputSize.COMPACT;

        public Builder interval(Interval interval){
            this.interval = interval;
            return this;
        }

        public Builder outputSize(OutputSize outputsize){
            this.outputsize = outputsize;
            return this;
        }

        @Override
        public ForexRequest build() {
            return new IntraDayRequest(this);
        }
    }
}
