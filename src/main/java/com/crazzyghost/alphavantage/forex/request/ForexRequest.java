package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.DataType;

public abstract class ForexRequest{

    protected String from_symbol;
    protected String to_symbol;
    protected DataType dataType;

    protected ForexRequest(Builder builder) {
        this.to_symbol = builder.toSymbol;
        this.from_symbol = builder.fromSymbol;
        this.dataType = builder.dataType != null ? builder.dataType : DataType.JSON ;
    }


    public abstract static class Builder <T extends Builder>{

        public String fromSymbol;
        public String toSymbol;
        public DataType dataType;

        public T fromSymbol(String fromSymbol){
            this.fromSymbol = fromSymbol;
            return (T) this;
        }

        public T toSymbol(String fromSymbol){
            this.toSymbol =fromSymbol;
            return (T) this;
        }

        public T dataType(DataType dataType){
            this.dataType = dataType;
            return (T) this;
        }


        public abstract ForexRequest build();

    }
}
