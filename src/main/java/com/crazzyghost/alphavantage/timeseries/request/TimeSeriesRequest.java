package com.crazzyghost.alphavantage.timeseries.request;


import com.crazzyghost.alphavantage.parameters.DataType;

public abstract class TimeSeriesRequest {

    private DataType dataType;
    private String symbol;


    protected TimeSeriesRequest(Builder builder){
        this.symbol = builder.symbol;
        this.dataType = builder.dataType == null ? DataType.JSON : builder.dataType;
    }

    public static abstract class Builder<T extends Builder<T>>{

        protected DataType dataType;
        protected String symbol;

        public Builder(){

        }


        public T dataType(DataType dataType){
            this.dataType = dataType;
            return (T) this;
        }

        public T forSymbol(String symbol){
            this.symbol = symbol;
            return (T) this;
        }

        public abstract TimeSeriesRequest build();

    }


    @Override
    public String toString() {
        return "TimeSeriesRequest{" +
                ", dataType=" + dataType +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
