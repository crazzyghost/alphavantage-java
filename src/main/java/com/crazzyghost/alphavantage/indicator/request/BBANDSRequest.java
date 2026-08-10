package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.indicator.request.IndicatorRequest;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;


/**
 * Request for Bollinger Bands ({@code BBANDS}), an upper and lower volatility
 * band plotted a configurable number of standard deviations above and below
 * a moving average of a price series.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.BBANDSRequest}
 */
@Deprecated
public class BBANDSRequest extends IndicatorRequest{

    /** The price series field the bands are computed from. */
    private SeriesType series_type;

    /** The number of data points in the middle moving average. */
    private int time_period;

    /** The number of standard deviations above the middle band for the upper band. */
    private int nbdevup;

    /** The number of standard deviations below the middle band for the lower band. */
    private int nbdevdn;

    /** The moving-average type used for the middle band. */
    private MAType maType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private BBANDSRequest(Builder builder){
        super(builder);
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
        this.nbdevdn = builder.nbdevdn;
        this.nbdevup = builder.nbdevup;
        this.maType = builder.maType;
    }

    /**
     * Fluent builder for {@link BBANDSRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder>{

        /** The price series field the bands are computed from. */
        private SeriesType seriesType;

        /** The number of data points in the middle moving average. */
        private int timePeriod;

        /** The number of standard deviations above the middle band; defaults to 2. */
        private int nbdevup = 2;

        /** The number of standard deviations below the middle band; defaults to 2. */
        private int nbdevdn = 2;

        /** The moving-average type used for the middle band; defaults to {@link MAType#SMA}. */
        private MAType maType = MAType.SMA;

        /**
         * Creates a builder pre-set to {@link Function#BBANDS}.
         */
        public Builder(){
            this.function(Function.BBANDS);
        }

        /**
         * Sets the number of data points in the middle moving average.
         *
         * @param timePeriod the time period
         * @return this builder
         */
        public Builder timePeriod(int timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * Sets the price series field the bands are computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Sets the number of standard deviations above the middle band.
         *
         * @param nbdevup the upper-band standard deviation multiplier
         * @return this builder
         */
        public Builder nbdevup(int nbdevup){
            this.nbdevup = nbdevup;
            return this;
        }

        /**
         * Sets the number of standard deviations below the middle band.
         *
         * @param nbdevdn the lower-band standard deviation multiplier
         * @return this builder
         */
        public Builder nbdevdn(int nbdevdn){
            this.nbdevdn = nbdevdn;
            return this;
        }

        /**
         * Sets the moving-average type used for the middle band.
         *
         * @param maType the moving-average type
         * @return this builder
         */
        public Builder maType(MAType maType){
            this.maType = maType;
            return this;
        }

        /**
         * Builds the configured {@link BBANDSRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new BBANDSRequest(this);
        }

    }
}