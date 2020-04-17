package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class CryptoRequest {


    private Function function;
    private String symbol;
    private String market;


    private CryptoRequest(Builder builder){
        this.function = builder.function;
        this.market = builder.market;
        this.symbol = builder.symbol;
    }

    public Function getFunction() {
        return function;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getMarket() {
        return market;
    }

    public static Builder builder(){
        return  new Builder();
    }

    public static class Builder{

        private Function function;
        private String symbol;
        private String market;

        public Builder(){

        }

        public Builder function(Function function){
            this.function  = function;
            return this;
        }

        public Builder symbol(String symbol){
            this.symbol = symbol;
            return this;
        }

        public Builder market(String market){
            this.market = market;
            return this;
        }

        public CryptoRequest build(){
            return new CryptoRequest(this);
        }
    }

}
