package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for moving average convergence / divergence ({@code MACD}), the
 * difference between a fast and slow EMA of a price series, together with a
 * signal line that is itself an EMA of that difference.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.MACDRequest}
 */
@Deprecated
public class MACDRequest extends IndicatorRequest{

    /** The price series field MACD is computed from. */
    private SeriesType series_type;

    /** The number of data points in the fast EMA. */
    private int fastPeriod;

    /** The number of data points in the slow EMA. */
    private int slowPeriod;

    /** The number of data points in the signal line's EMA. */
    private int signalPeriod;


    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private MACDRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
        this.signalPeriod = builder.signalPeriod;
        this.series_type = builder.seriesType;
    }

    /**
     * Fluent builder for {@link MACDRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast EMA; defaults to 12. */
        private int fastPeriod = 12;

        /** The number of data points in the slow EMA; defaults to 26. */
        private int slowPeriod = 26;

        /** The number of data points in the signal line's EMA; defaults to 9. */
        private int signalPeriod = 9;

        /** The price series field MACD is computed from. */
        private SeriesType seriesType;

        /**
         * Creates a builder pre-set to {@link Function#MACD}.
         */
        public Builder() {
            this.function(Function.MACD);
        }

        /**
         * Sets the number of data points in the fast EMA.
         *
         * @param fastPeriod the fast period
         * @return this builder
         */
        public Builder fastPeriod(int fastPeriod){
            this.fastPeriod = fastPeriod;
            return this;
        }

        /**
         * Sets the number of data points in the slow EMA.
         *
         * @param slowPeriod the slow period
         * @return this builder
         */
        public Builder slowPeriod(int slowPeriod){
            this.slowPeriod = slowPeriod;
            return this;
        }

        /**
         * Sets the number of data points in the signal line's EMA.
         *
         * @param signalPeriod the signal period
         * @return this builder
         */
        public Builder signalPeriod(int signalPeriod){
            this.signalPeriod = signalPeriod;
            return this;
        }

        /**
         * Sets the price series field MACD is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link MACDRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new MACDRequest(this);
        }
        
    }   
}