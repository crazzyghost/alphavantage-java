package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class RatingRequest extends CryptoRequest{
    

    private RatingRequest(Builder builder){
        super(builder);
    }


    public static class Builder extends CryptoRequest.Builder<Builder>{

        public Builder(){
            this.function(Function.CRYPTO_RATING);
        }

        @Override
        public CryptoRequest build(){
            return new RatingRequest(this);
        }
    }

}