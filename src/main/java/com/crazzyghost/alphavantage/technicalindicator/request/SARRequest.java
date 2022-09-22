package com.crazzyghost.alphavantage.technicalindicator.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class SARRequest extends TechnicalIndicatorRequest {

    private double acceleration;
    private double maximum;

    private SARRequest(Builder builder) {
        super(builder);
        this.acceleration = builder.acceleration;
        this.maximum = builder.maximum;
    }

    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        private double acceleration = 0.01;
        private double maximum = 0.20;

        public Builder() {
            this.function(Function.SAR);
        }

        public Builder acceleration(double acceleration) {
            this.acceleration = acceleration;
            return this;
        }

        public Builder maximum(double maximum) {
            this.maximum = maximum;
            return this;
        }

        @Override
        public TechnicalIndicatorRequest build() {
            return new SARRequest(this);
        }

    }
}