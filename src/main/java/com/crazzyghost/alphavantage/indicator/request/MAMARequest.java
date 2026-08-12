package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for the MESA adaptive moving average ({@code MAMA}), an adaptive
 * moving average that adjusts its own smoothing speed to price movement
 * using the Hilbert transform, reported alongside its slower-following
 * companion, FAMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.MAMARequest}
 */
@Deprecated
public class MAMARequest extends IndicatorRequest {

    /** The price series field the average is computed from. */
    private SeriesType series_type;

    /** The upper bound on how fast MAMA can adapt during strongly trending price movement. */
    private double fastLimit;

    /** The lower bound on how fast MAMA can adapt during sideways price movement. */
    private double slowLimit;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private MAMARequest(Builder builder) {
        super(builder);
        this.fastLimit = builder.fastLimit;
        this.slowLimit = builder.slowLimit;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link MAMARequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The upper adaptation-speed bound; defaults to 0.1. */
        private double fastLimit = 0.1;

        /** The lower adaptation-speed bound; defaults to 0.1. */
        private double slowLimit = 0.1;

        /** The price series field the average is computed from. */
        private SeriesType seriesType;

        /**
         * Creates a builder pre-set to {@link Function#MAMA}.
         */
        public Builder() {
            this.function(Function.MAMA);
        }

        /**
         * Sets the upper bound on how fast MAMA can adapt.
         *
         * @param fastLimit the fast limit
         * @return this builder
         */
        public Builder fastLimit(double fastLimit){
            this.fastLimit = fastLimit;
            return this;
        }

        /**
         * Sets the lower bound on how fast MAMA can adapt.
         *
         * @param slowLimit the slow limit
         * @return this builder
         */
        public Builder slowLimit(double slowLimit){
            this.slowLimit = slowLimit;
            return this;
        }

        /**
         * Sets the price series field the average is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link MAMARequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new MAMARequest(this);
        }

        
    }   



}