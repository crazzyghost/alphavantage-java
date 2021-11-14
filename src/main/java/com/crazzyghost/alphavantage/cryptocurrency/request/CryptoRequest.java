package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.Function;

public abstract class CryptoRequest {

 
    protected Function function;
    protected String symbol;
 
    public CryptoRequest(Builder<?> builder){
        this.function = builder.function;
        this.symbol = builder.symbol;
    }

    public abstract static class Builder<T extends Builder<?>>{

        public Function function;
        protected String symbol;

        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        public T symbol(String symbol){
            this.symbol = symbol;
            return (T) this;
        }
      
        public abstract CryptoRequest build();
    }

}
