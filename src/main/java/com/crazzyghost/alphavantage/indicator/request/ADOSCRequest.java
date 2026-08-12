package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * Request for the Chaikin A/D oscillator ({@code ADOSC}), the MACD of the
 * Chaikin accumulation/distribution line using a fast and slow EMA period.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.ADOSCRequest}
 */
@Deprecated
public class ADOSCRequest extends IndicatorRequest {

    /** The number of data points in the fast EMA. */
    private int fastPeriod;

    /** The number of data points in the slow EMA. */
    private int slowPeriod;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private ADOSCRequest(Builder builder) {
        super(builder);
        this.fastPeriod = builder.fastPeriod;
        this.slowPeriod = builder.slowPeriod;
    }

    /**
     * Fluent builder for {@link ADOSCRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The number of data points in the fast EMA; defaults to 3. */
        private int fastPeriod = 3;

        /** The number of data points in the slow EMA; defaults to 10. */
        private int slowPeriod = 10;

        /**
         * Creates a builder pre-set to {@link Function#ADOSC}.
         */
        public Builder(){
            this.function(Function.ADOSC);
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
         * Builds the configured {@link ADOSCRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new ADOSCRequest(this);
        }
        
    }  
}