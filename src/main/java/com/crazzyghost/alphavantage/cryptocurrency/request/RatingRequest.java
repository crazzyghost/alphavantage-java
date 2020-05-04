package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class RatingRequest extends DigitalCurrencyRequest{
    

    private RatingRequest(Builder builder){
        super(builder);
    }

    public static Builder builder(){
        return  new Builder();
    }

    public static class Builder extends DigitalCurrencyRequest.Builder<Builder>{

        public Builder(){
            this.function(Function.CRYPTO_RATING);
        }

        @Override
        public DigitalCurrencyRequest build(){
            return new RatingRequest(this);
        }
    }

}