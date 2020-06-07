package com.crazzyghost.alphavantage.sector;

import com.crazzyghost.alphavantage.parameters.Function;

public class SectorRequest {

    private Function function;

    SectorRequest(Builder builder){
        this.function = builder.function;
    }

    public static class Builder {

        private Function function;

        public Builder(){
            this.function = Function.SECTOR;
        }

        SectorRequest build(){
            return new SectorRequest(this);
        }

    }
}