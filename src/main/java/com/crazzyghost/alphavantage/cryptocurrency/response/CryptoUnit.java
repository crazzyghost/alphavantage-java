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
package com.crazzyghost.alphavantage.cryptocurrency.response;

/**
 * Crypto Currency Unit
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class CryptoUnit {
    private final Double open;
    private final Double close;
    private final Double high;
    private final Double low;
    private final Double volume;

    public CryptoUnit(Builder builder) {
        this.open = builder.open;
        this.close = builder.close;
        this.high = builder.high;
        this.low = builder.low;
        this.volume = builder.volume;
    }


    public static class Builder {
        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Double volume;

        public Builder open(Double open){
            this.open = open;
            return this;
        }

        public Builder high(Double high){
            this.high = high;
            return this;
        }
        public Builder low(Double low){
            this.low = low;
            return this;
        }

        public Builder close(Double close){
            this.close = close;
            return this;
        }

        public Builder volume(Double volume){
            this.volume = volume;
            return this;
        }

        public CryptoUnit build(){
            return new CryptoUnit(this);
        }

    }

    public Double getOpen() {
        return open;
    }

    public Double getClose() {
        return close;
    }

    public Double getHigh() {
        return high;
    }

    public Double getLow() {
        return low;
    }

    public Double getVolume() {
        return volume;
    }


    @Override
    public String toString() {
        return "\n" + "CryptoUnit {" +
            ", close=" + close +
            ", high=" + high +
            ", low=" + low +
            ", open=" + open +
            ", volume=" + volume +
        "}";
    }

}
