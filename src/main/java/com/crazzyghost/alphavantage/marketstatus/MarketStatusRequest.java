package com.crazzyghost.alphavantage.marketstatus;

import com.crazzyghost.alphavantage.parameters.Function;

public class MarketStatusRequest {
    private final Function function;

    public MarketStatusRequest(Builder builder) {
        this.function = builder.function;
    }

    public static class Builder {
        private final Function function;

        public Builder() {
            this.function = Function.MARKET_STATUS;
        }

        public MarketStatusRequest build() {
            return new MarketStatusRequest(this);
        }
    }
}
