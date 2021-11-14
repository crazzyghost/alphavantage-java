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
package com.crazzyghost.alphavantage.fundamentaldata.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * Base Fundamental Data Request
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.6.0
 */
public abstract class FundamentalDataRequest {

    protected Function function;
    protected String symbol;

    protected FundamentalDataRequest(Builder<?> builder) {
        this.function = builder.function;
        this.symbol = builder.symbol;
    }


    public abstract static class Builder <T extends Builder<?>> {

        private String symbol;
        public Function function;

        public T symbol(String symbol){
            this.symbol = symbol;
            return (T) this;
        }

        public T function(Function function){
            this.function = function;
            return (T) this;
        }

        public abstract FundamentalDataRequest build();

    }
}
