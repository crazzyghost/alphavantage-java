/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.technicalindicator.request;

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
 */
public abstract class TechnicalIndicatorRequest {

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
    protected TechnicalIndicatorRequest(Builder<?> builder) {
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
    public abstract static class Builder<T extends Builder<?>> {

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
        public T function(Function function) {
            this.function = function;
            return (T) this;
        }

        /**
         * Sets the symbol to query.
         *
         * @param symbol the equity, forex pair, or digital/crypto currency symbol
         * @return this builder
         */
        public T forSymbol(String symbol) {
            this.symbol = symbol;
            return (T) this;
        }

        /**
         * Sets the time interval between data points.
         *
         * @param interval the interval
         * @return this builder
         */
        public T interval(Interval interval) {
            this.interval = interval;
            return (T) this;
        }

        /**
         * Sets the response format.
         *
         * @param dataType the response format
         * @return this builder
         */
        public T dataType(DataType dataType) {
            this.dataType = dataType;
            return (T) this;
        }

        /**
         * Builds the concrete request instance configured by this builder.
         *
         * @return the built request
         */
        public abstract TechnicalIndicatorRequest build();
    }

}
