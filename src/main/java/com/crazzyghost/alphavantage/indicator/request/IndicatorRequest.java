package com.crazzyghost.alphavantage.indicator.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;


public abstract class IndicatorRequest {

    protected Function function;
    protected String symbol;
    protected Interval interval;
    protected DataType dataType;

    protected IndicatorRequest(Builder<?> builder){
        this.function = builder.function;
        this.symbol = builder.symbol;
        this.interval = builder.interval;
        this.dataType = builder.dataType == null ? DataType.JSON : builder.dataType;
    }

    public abstract static class Builder<T extends Builder<?>>{
        
        public Function function;
        public String symbol;
        public Interval interval;
        public DataType dataType;

        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        public T forSymbol(String symbol){
            this.symbol = symbol;
            return (T) this;
        }

        public T interval(Interval interval){
            this.interval = interval;
            return (T) this;
        }

        public T dataType(DataType dataType){
            this.dataType = dataType;
            return (T) this;
        }

        public abstract IndicatorRequest build();
    } 

}