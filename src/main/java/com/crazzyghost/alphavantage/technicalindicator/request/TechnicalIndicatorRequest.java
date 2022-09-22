package com.crazzyghost.alphavantage.technicalindicator.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;

public abstract class TechnicalIndicatorRequest {

    protected Function function;
    protected String symbol;
    protected Interval interval;
    protected DataType dataType;

    protected TechnicalIndicatorRequest(Builder<?> builder) {
        this.function = builder.function;
        this.symbol = builder.symbol;
        this.interval = builder.interval;
        this.dataType = builder.dataType;
    }

    public abstract static class Builder<T extends Builder<?>> {

        public Function function;
        protected String symbol;
        protected Interval interval = Interval.SIXTY_MIN;
        protected DataType dataType = DataType.JSON;

        public T function(Function function) {
            this.function = function;
            return (T) this;
        }

        public T forSymbol(String symbol) {
            this.symbol = symbol;
            return (T) this;
        }

        public T interval(Interval interval) {
            this.interval = interval;
            return (T) this;
        }

        public T dataType(DataType dataType) {
            this.dataType = dataType;
            return (T) this;
        }

        public abstract TechnicalIndicatorRequest build();
    }

}