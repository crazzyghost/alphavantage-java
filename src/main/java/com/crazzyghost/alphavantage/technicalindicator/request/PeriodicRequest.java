package com.crazzyghost.alphavantage.technicalindicator.request;

public class PeriodicRequest extends TechnicalIndicatorRequest {

    private int time_period;

    private PeriodicRequest(Builder builder) {
        super(builder);
        this.time_period = builder.timePeriod;
    }

    public static class Builder extends TechnicalIndicatorRequest.Builder<Builder> {

        private int timePeriod = 60;

        public Builder timePeriod(int timePeriod) {
            this.timePeriod = timePeriod;
            return this;
        }

        @Override
        public TechnicalIndicatorRequest build() {
            return new PeriodicRequest(this);
        }

    }

}