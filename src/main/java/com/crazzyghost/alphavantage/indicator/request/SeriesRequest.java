package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for indicators computed from a chosen price series field but
 * without a rolling time-period parameter, namely the Hilbert transform
 * studies ({@code HT_TRENDLINE}, {@code HT_SINE}, {@code HT_TRENDMODE},
 * {@code HT_DCPERIOD}, {@code HT_DCPHASE}, {@code HT_PHASOR}).
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest}
 */
@Deprecated
public class SeriesRequest extends IndicatorRequest {

    /** The price series field the indicator is computed from. */
    private SeriesType series_type;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private SeriesRequest(Builder builder){
        super(builder);
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link SeriesRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder>{

        /** The price series field the indicator is computed from. */
        private SeriesType seriesType;
     
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
         * Builds the configured {@link SeriesRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new SeriesRequest(this);
        }

    }
}