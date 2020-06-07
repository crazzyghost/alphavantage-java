package com.crazzyghost.alphavantage.sector;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * @author crazzyghost
 * @since 1.4.0
 * A Sector request 
 */
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