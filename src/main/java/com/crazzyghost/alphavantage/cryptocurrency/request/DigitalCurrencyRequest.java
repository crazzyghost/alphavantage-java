package com.crazzyghost.alphavantage.cryptocurrency.request;


public class DigitalCurrencyRequest extends CryptoRequest{

    private String market;

    private DigitalCurrencyRequest(Builder builder){
        super(builder);
        this.market = builder.market;
    }


    public static class Builder extends CryptoRequest.Builder<Builder>{

        private String market;
        
        public String getMarket() {
            return market;
        }

        public Builder(){

        }

        public Builder market(String market){
            this.market = market;
            return this;
        }

        @Override
        public CryptoRequest build(){
            return new DigitalCurrencyRequest(this);
        }
    }
    
}