/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.cryptocurrency.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * A request for the {@code CRYPTO_INTRADAY} endpoint, which reports a digital
 * currency's open-high-low-close-volume series at a selectable sub-daily
 * interval. The function is pinned on construction, so only the symbol, market,
 * interval, output size and data type need setting.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
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

    /**
     * Collects a caller's digital currency symbol, market, interval, output size
     * and data type and assembles them into an {@link IntradayRequest}.
     * <p>
     * The function is pinned to {@link Function#CRYPTO_INTRADAY} on construction.
     */
    public static class Builder extends CryptoRequest.Builder<Builder> {
        private Interval interval;
        private OutputSize outputSize;
        private DataType dataType;

        /** Creates a builder for the {@code CRYPTO_INTRADAY} endpoint. */
        public Builder() {
            this.function = Function.CRYPTO_INTRADAY;
        }

        /**
         * Sets the interval between data points.
         *
         * @param interval the interval between two consecutive data points
         * @return this builder, for method chaining
         */
        public Builder interval(Interval interval) {
            this.interval = interval;
            return this;
        }

        /**
         * Sets how much historical intraday data to return.
         *
         * @param outputSize the amount of historical data to return
         * @return this builder, for method chaining
         */
        public Builder outputSize(OutputSize outputSize) {
            this.outputSize = outputSize;
            return this;
        }

        /**
         * Sets the response format.
         *
         * @param dataType the format the response is returned in
         * @return this builder, for method chaining
         */
        public Builder dataType(DataType dataType) {
            this.dataType = dataType;
            return this;
        }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's parameters
         */
        @Override
        public IntradayRequest build() {
            return new IntradayRequest(this);
        }
    }
}
