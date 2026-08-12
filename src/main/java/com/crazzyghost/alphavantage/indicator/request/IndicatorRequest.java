package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;


/**
 * Base request for every technical indicator endpoint, carrying the
 * parameters ({@code function}, {@code symbol}, {@code interval},
 * {@code datatype}) that all indicators share.
 * <p>
 * Concrete subclasses (for example {@link PeriodicRequest} or
 * {@link SeriesRequest}) add whatever further parameters their indicator
 * requires, such as {@code time_period} or {@code series_type}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.TechnicalIndicatorRequest}
 */
@Deprecated
public abstract class IndicatorRequest {

    /** The Alpha Vantage function code identifying which indicator to request. */
    protected Function function;

    /** The equity, forex pair, or digital/crypto currency symbol to query. */
    protected String symbol;

    /** The time interval between two consecutive data points in the series. */
    protected Interval interval;

    /** The response format, JSON or CSV. */
    protected DataType dataType;

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    protected IndicatorRequest(Builder<?> builder){
        this.function = builder.function;
        this.symbol = builder.symbol;
        this.interval = builder.interval;
        this.dataType = builder.dataType;
    }

    /**
     * Base fluent builder shared by every technical indicator request.
     * <p>
     * {@code T} is the concrete builder subtype, letting each fluent setter
     * return the subclass's own type instead of {@code Builder} itself.
     *
     * @param <T> the concrete builder type returned by this builder's setters
     */
    public abstract static class Builder<T extends Builder<?>>{
        
        /** The Alpha Vantage function code identifying which indicator to request. */
        public Function function;

        /** The equity, forex pair, or digital/crypto currency symbol to query. */
        protected String symbol;

        /** The time interval between data points; defaults to {@link Interval#SIXTY_MIN}. */
        protected Interval interval = Interval.SIXTY_MIN;

        /** The response format; defaults to {@link DataType#JSON}. */
        protected DataType dataType = DataType.JSON;

        /**
         * Sets the indicator function to request.
         *
         * @param function the Alpha Vantage function code
         * @return this builder
         */
        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        /**
         * Sets the symbol to query.
         *
         * @param symbol the equity, forex pair, or digital/crypto currency symbol
         * @return this builder
         */
        public T forSymbol(String symbol){
            this.symbol = symbol;
            return (T) this;
        }

        /**
         * Sets the time interval between data points.
         *
         * @param interval the interval
         * @return this builder
         */
        public T interval(Interval interval){
            this.interval = interval;
            return (T) this;
        }

        /**
         * Sets the response format.
         *
         * @param dataType the response format
         * @return this builder
         */
        public T dataType(DataType dataType){
            this.dataType = dataType;
            return (T) this;
        }

        /**
         * Builds the concrete request instance configured by this builder.
         *
         * @return the built request
         */
        public abstract IndicatorRequest build();
    } 

}