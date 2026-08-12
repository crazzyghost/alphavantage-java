package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.SeriesType;


/**
 * Request for indicators computed over a rolling time period applied to a
 * chosen price series field, such as {@code SMA}, {@code EMA}, or
 * {@code RSI}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest}
 */
@Deprecated
public class PeriodicSeriesRequest extends IndicatorRequest {

    /** The price series field the indicator is computed from. */
    private SeriesType series_type;

    /** The number of data points used to calculate each indicator value. */
    private int time_period;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private PeriodicSeriesRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link PeriodicSeriesRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The price series field the indicator is computed from. */
        private SeriesType seriesType;

        /** The number of data points used to calculate each indicator value. */
        private int timePeriod;

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
         * Sets the price series field the indicator is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link PeriodicSeriesRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {

            return new PeriodicSeriesRequest(this);
        }

    }
}