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

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * A request for the {@code CRYPTO_RATING} endpoint, which reports the FCAS health
 * index for a digital currency. The builder fixes the function, so only the symbol
 * needs setting.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class RatingRequest extends CryptoRequest {

    private RatingRequest(Builder builder){
        super(builder);
    }

    /**
     * Collects a caller's digital currency symbol and assembles it into a
     * {@link RatingRequest}.
     * <p>
     * The function is pinned to {@link Function#CRYPTO_RATING} on construction,
     * so the symbol is the only parameter a caller supplies.
     */
    public static class Builder extends CryptoRequest.Builder<Builder> {

        /** Creates a builder for the {@code CRYPTO_RATING} endpoint. */
        public Builder() { this.function(Function.CRYPTO_RATING); }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's parameters
         */
        @Override
        public CryptoRequest build() {
            return new RatingRequest(this);
        }
    }

}