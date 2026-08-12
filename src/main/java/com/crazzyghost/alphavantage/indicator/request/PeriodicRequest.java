package com.crazzyghost.alphavantage.indicator.request;


/**
 * Request for indicators that operate on a rolling time period but not on a
 * specific price series field, such as {@code WILLR}, {@code ADX}, or
 * {@code CCI}, which are computed directly from an instrument's high, low,
 * and close rather than from a chosen {@code series_type}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest}
 */
@Deprecated
public class PeriodicRequest extends IndicatorRequest{

    /** The number of data points used to calculate each indicator value. */
    private int time_period;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private PeriodicRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
    }

    /**
     * Fluent builder for {@link PeriodicRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder>{

        /** The number of data points used to calculate each indicator value; defaults to 60. */
        private int timePeriod = 60;

        /**
         * Sets the number of data points used to calculate each indicator value.
         *
         * @param timePeriod the time period
         * @return this builder
         */
        public Builder timePeriod(int timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * Builds the configured {@link PeriodicRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new PeriodicRequest(this);
        }

    }

}