package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;

/**
 * Request for the stochastic fast oscillator ({@code STOCHF}), the
 * unsmoothed counterpart of {@link STOCHRequest STOCH} that reports raw
 * {@code %K} and a lightly smoothed {@code %D} without the slow %K stage.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.STOCHFRequest}
 */
@Deprecated
public class STOCHFRequest extends IndicatorRequest {

    /** The look-back period used for the raw (fast) %K calculation. */
    private int fastKPeriod;

    /** The smoothing period applied to fast %K to produce fast %D. */
    private int fastDPeriod;

    /** The moving-average type used to smooth fast %D. */
    private MAType fastDMaType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected STOCHFRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.fastDPeriod = builder.fastDPeriod;
        this.fastDMaType = builder.fastDMaType;
    }
    
    /**
     * Fluent builder for {@link STOCHFRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The raw %K look-back period; defaults to 5. */
        private int fastKPeriod = 5;

        /** The fast %D smoothing period; defaults to 3. */
        private int fastDPeriod = 3;

        /** The moving-average type used to smooth fast %D; defaults to {@link MAType#SMA}. */
        private MAType fastDMaType = MAType.SMA;

        /**
         * Creates a builder pre-set to {@link Function#STOCHF}.
         */
        public Builder() {
            this.function(Function.STOCHF);
        }
    
        /**
         * Sets the look-back period used for the raw (fast) %K calculation.
         *
         * @param fastKPeriod the fast %K period
         * @return this builder
         */
        public Builder fastKPeriod(int fastKPeriod){
            this.fastKPeriod = fastKPeriod;
            return this;
        }

        /**
         * Sets the smoothing period applied to fast %K to produce fast %D.
         *
         * @param fastDPeriod the fast %D period
         * @return this builder
         */
        public Builder fastDPeriod(int fastDPeriod){
            this.fastDPeriod = fastDPeriod;
            return this;
        }

        /**
         * Sets the moving-average type used to smooth fast %D.
         *
         * @param type the moving-average type
         * @return this builder
         */
        public Builder fastDMaType(MAType type){
            this.fastDMaType = type;
            return this;
        }

        /**
         * Builds the configured {@link STOCHFRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new STOCHFRequest(this);
        }
        
    }   
}