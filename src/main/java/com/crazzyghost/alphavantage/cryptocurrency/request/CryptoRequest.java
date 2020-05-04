package com.crazzyghost.alphavantage.cryptocurrency.request;


public class CryptoRequest extends DigitalCurrencyRequest {

    private String market;

    private CryptoRequest(Builder builder){
        super(builder);
        this.market = builder.market;
    }

    @Deprecated
    public static Builder builder(){
        return  new Builder();
    }

    public static class Builder extends DigitalCurrencyRequest.Builder<Builder>{

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
        public DigitalCurrencyRequest build(){
            return new CryptoRequest(this);
        }
    }

}
