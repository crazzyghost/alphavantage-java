package com.crazzyghost.alphavantage.indicator.request;

public class SimpleIndicatorRequest extends IndicatorRequest {

    private SimpleIndicatorRequest(Builder builder) {
        super(builder);
    }

    public static class Builder extends IndicatorRequest.Builder<Builder>{

        @Override
        public IndicatorRequest build() {
            return new SimpleIndicatorRequest(this);
        }

    }
}