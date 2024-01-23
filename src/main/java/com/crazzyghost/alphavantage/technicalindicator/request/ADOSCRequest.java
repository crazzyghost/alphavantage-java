package com.crazzyghost.alphavantage.technicalindicator.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class ADOSCRequest extends TechnicalIndicatorRequest {
    private int fastPeriod;
    private int slowPeriod;

    private ADOSCRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
    }

    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        private int fastPeriod = 3;
        private int slowPeriod = 10;

        public Builder() {
            this.function(Function.ADOSC);
        }

        public Builder fastPeriod(int fastPeriod) {
            this.fastPeriod = fastPeriod;
            return this;
        }

        public Builder slowPeriod(int slowPeriod) {
            this.slowPeriod = slowPeriod;
            return this;
        }

        @Override
        public TechnicalIndicatorRequest build() {
            return new ADOSCRequest(this);
        }

    }
}