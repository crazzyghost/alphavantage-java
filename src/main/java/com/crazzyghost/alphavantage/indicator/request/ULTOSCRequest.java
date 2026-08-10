package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * Request for the ultimate oscillator ({@code ULTOSC}), a momentum
 * oscillator that combines buying pressure across three time periods to
 * reduce the false-divergence signals a single-period oscillator produces.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.ULTOSCRequest}
 */
@Deprecated
public class ULTOSCRequest extends IndicatorRequest{

    /** The first, shortest look-back period. */
    private int timePeriod1;

    /** The second, medium look-back period. */
    private int timePeriod2;

    /** The third, longest look-back period. */
    private int timePeriod3; 

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected ULTOSCRequest(Builder builder) {
        super(builder);
        this.timePeriod1 = builder.timePeriod1;
        this.timePeriod2 = builder.timePeriod2;
        this.timePeriod3 = builder.timePeriod3;
    }

    /**
     * Fluent builder for {@link ULTOSCRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The first, shortest look-back period; defaults to 7. */
        public int timePeriod1 = 7;

        /** The second, medium look-back period; defaults to 14. */
        public int timePeriod2 = 14;

        /** The third, longest look-back period; defaults to 28. */
        public int timePeriod3 = 28; 

        /**
         * Creates a builder pre-set to {@link Function#ULTOSC}.
         */
        public Builder(){
            this.function(Function.ULTOSC);
        }

        /**
         * Sets the first, shortest look-back period.
         *
         * @param period the first time period
         * @return this builder
         */
        public Builder timePeriod1(int period){
            this.timePeriod1 = period;
            return this;
        }
        
        /**
         * Sets the second, medium look-back period.
         *
         * @param period the second time period
         * @return this builder
         */
        public Builder timePeriod2(int period){
            this.timePeriod2 = period;
            return this;
        }

        /**
         * Sets the third, longest look-back period.
         *
         * @param period the third time period
         * @return this builder
         */
        public Builder timePeriod3(int period){
            this.timePeriod3 = period;
            return this;
        }

        /**
         * Builds the configured {@link ULTOSCRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build(){
            return new ULTOSCRequest(this);
        }
    }

}