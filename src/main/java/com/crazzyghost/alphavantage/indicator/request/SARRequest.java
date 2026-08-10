package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * Request for the parabolic SAR ({@code SAR}), a trend-following stop and
 * reversal indicator that trails price and accelerates toward it over time.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.SARRequest}
 */
@Deprecated
public class SARRequest extends IndicatorRequest {

    /** The acceleration factor step applied on each new extreme point. */
    private double acceleration;

    /** The acceleration factor's upper bound. */
    private double maximum;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private SARRequest(Builder builder) {
        super(builder);
        this.acceleration = builder.acceleration;
        this.maximum = builder.maximum;
    }

    /**
     * Fluent builder for {@link SARRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder>{

        /** The acceleration factor step; defaults to 0.01. */
        private double acceleration = 0.01;

        /** The acceleration factor's upper bound; defaults to 0.20. */
        private double maximum = 0.20;

        /**
         * Creates a builder pre-set to {@link Function#SAR}.
         */
        public Builder(){
            this.function(Function.SAR);
        }

        /**
         * Sets the acceleration factor step applied on each new extreme point.
         *
         * @param acceleration the acceleration step
         * @return this builder
         */
        public Builder acceleration(double acceleration){
            this.acceleration = acceleration;
            return this;
        }

        /**
         * Sets the acceleration factor's upper bound.
         *
         * @param maximum the maximum acceleration
         * @return this builder
         */
        public Builder maximum(double maximum){
            this.maximum = maximum;
            return this;
        }

        /**
         * Builds the configured {@link SARRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new SARRequest(this);
        }

    }
}