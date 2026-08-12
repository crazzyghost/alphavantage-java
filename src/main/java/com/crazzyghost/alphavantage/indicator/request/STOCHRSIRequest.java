package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

/**
 * Request for the stochastic relative strength index ({@code STOCHRSI}),
 * which applies the {@link STOCHRequest stochastic oscillator}'s %K/%D
 * calculation to RSI values instead of price, producing a more sensitive
 * overbought/oversold reading than RSI alone.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.STOCHRSIRequest}
 */
@Deprecated
public class STOCHRSIRequest extends IndicatorRequest {

    /** The number of data points used to calculate the underlying RSI. */
    private int time_period;

    /** The price series field the underlying RSI is computed from. */
    private SeriesType series_type;

    /** The look-back period used for the raw (fast) %K calculation over RSI. */
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
    protected STOCHRSIRequest(Builder builder) {
        super(builder);
        this.fastKPeriod = builder.fastKPeriod;
        this.fastDPeriod = builder.fastDPeriod;
        this.fastDMaType = builder.fastDMaType;
        this.time_period = builder.timePeriod;
        this.series_type = builder.seriesType;
    }
    
    /**
     * Fluent builder for {@link STOCHRSIRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder> {

        /** The raw %K look-back period; defaults to 5. */
        private int fastKPeriod = 5;

        /** The fast %D smoothing period; defaults to 3. */
        private int fastDPeriod = 3;

        /** The moving-average type used to smooth fast %D; defaults to {@link MAType#SMA}. */
        private MAType fastDMaType = MAType.SMA;

        /** The number of data points used to calculate the underlying RSI. */
        private int timePeriod;

        /** The price series field the underlying RSI is computed from. */
        private SeriesType seriesType;
        
        /**
         * Creates a builder pre-set to {@link Function#STOCHRSI}.
         */
        public Builder(){
            this.function(Function.STOCHRSI);
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
         * Sets the number of data points used to calculate the underlying RSI.
         *
         * @param timePeriod the time period
         * @return this builder
         */
        public Builder timePeriod(int timePeriod){
            this.timePeriod = timePeriod;
            return this;
        }

        /**
         * Sets the price series field the underlying RSI is computed from.
         *
         * @param seriesType the series field
         * @return this builder
         */
        public Builder seriesType(SeriesType seriesType){
            this.seriesType = seriesType;
            return this;
        }

        /**
         * Builds the configured {@link STOCHRSIRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new STOCHRSIRequest(this);
        }
        
    }   
}