package com.crazzyghost.alphavantage.technicalindicator.request;

public class SimpleTechnicalIndicatorRequest extends TechnicalIndicatorRequest {

    private SimpleTechnicalIndicatorRequest(Builder builder) {
        super(builder);
    }

    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        @Override
        public TechnicalIndicatorRequest build() {
            return new SimpleTechnicalIndicatorRequest(this);
        }

    }
}