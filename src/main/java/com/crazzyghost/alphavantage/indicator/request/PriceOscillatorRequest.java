package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for the two price-oscillator indicators, {@code APO} and
 * {@code PPO}, which measure the difference between a fast and a slow
 * moving average of a price series.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.PriceOscillatorRequest}
 */
@Deprecated
public class PriceOscillatorRequest extends IndicatorRequest {

    /** The price series field the oscillator is computed from. */
    private SeriesType series_type;

    /** The number of data points in the fast moving average. */
    private int fastPeriod;

    /** The number of data points in the slow moving average. */
    private int slowPeriod;

    /** The moving-average type used for both the fast and slow averages. */
    private MAType maType; 

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private PriceOscillatorRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
        this.maType = builder.maType;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link PriceOscillatorRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast moving average; defaults to 12. */
        private int fastPeriod = 12;

        /** The number of data points in the slow moving average; defaults to 26. */
        private int slowPeriod = 26;

        /** The moving-average type used for both averages; defaults to {@link MAType#SMA}. */
        private MAType maType = MAType.SMA;

        /** The price series field the oscillator is computed from. */
        private SeriesType seriesType;

        /**
         * Sets the number of data points in the fast moving average.
         *
         * @param fastPeriod the fast period
         * @return this builder
         */
        public Builder fastPeriod(int fastPeriod){
            this.fastPeriod = fastPeriod;
            return this;
        }

        /**
         * Sets the number of data points in the slow moving average.
         *
         * @param slowPeriod the slow period
         * @return this builder
         */
        public Builder slowPeriod(int slowPeriod){
            this.slowPeriod = slowPeriod;
            return this;
        }

        /**
         * Sets the price series field the oscillator is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Sets the moving-average type used for both the fast and slow averages.
         *
         * @param maType the moving-average type
         * @return this builder
         */
        public Builder maType(MAType maType){
            this.maType = maType;
            return this;
        }

        /**
         * Builds the configured {@link PriceOscillatorRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new PriceOscillatorRequest(this);
        }
        
    }   

}