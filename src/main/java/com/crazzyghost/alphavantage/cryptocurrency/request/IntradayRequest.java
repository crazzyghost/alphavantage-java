package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

public class IntradayRequest extends CryptoRequest {
    private final Interval interval;
    private final OutputSize outputSize;
    private final DataType dataType;

    private IntradayRequest(Builder builder) {
        super(builder);
        this.dataType = builder.dataType;
        this.outputSize = builder.outputSize;
        this.interval = builder.interval;
    }

    public static class Builder extends CryptoRequest.Builder<Builder> {
        private Interval interval;
        private OutputSize outputSize;
        private DataType dataType;

        public Builder() {
            this.function = Function.CRYPTO_INTRADAY;
        }

        public Builder interval(Interval interval) {
            this.interval = interval;
            return this;
        }

        public Builder outputSize(OutputSize outputSize) {
            this.outputSize = outputSize;
            return this;
        }

        public Builder dataType(DataType dataType) {
            this.dataType = dataType;
            return this;
        }

        @Override
        public IntradayRequest build() {
            return new IntradayRequest(this);
        }
    }
}
