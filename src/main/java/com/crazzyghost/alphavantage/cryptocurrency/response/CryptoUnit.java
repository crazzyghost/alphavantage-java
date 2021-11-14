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

    private final double open;
    private final double close;
    private final double high;
    private final double low;
    private final double openUSD;
    private final double closeUSD;
    private final double highUSD;
    private final double lowUSD;
    private final double volume;
    private final double marketCap;
    private final String date;

    public CryptoUnit(Builder builder) {
        this.open       = builder.open;
        this.close      = builder.close;
        this.high       = builder.high;
        this.low        = builder.low;
        this.openUSD    = builder.openUSD;
        this.closeUSD   = builder.closeUSD;
        this.highUSD    = builder.highUSD;
        this.lowUSD     = builder.lowUSD;
        this.volume     = builder.volume;
        this.marketCap  = builder.marketCap;
        this.date       = builder.date;
    }


    public static class Builder {

        double open;
        double high;
        double low;
        double close;
        double openUSD;
        double closeUSD;
        double highUSD;
        double lowUSD;
        double volume;
        double marketCap;
        String date;

        public Builder open(double open){
            this.open = open;
            return this;
        }

        public Builder high(double high){
            this.high = high;
            return this;
        }
        public Builder low(double low){
            this.low = low;
            return this;
        }

        public Builder close(double close){
            this.close = close;
            return this;
        }

        public Builder openUSD(double openUSD){
            this.openUSD = openUSD;
            return this;
        }

        public Builder highUSD(double highUSD){
            this.highUSD = highUSD;
            return this;
        }
        public Builder lowUSD(double lowUSD){
            this.lowUSD = lowUSD;
            return this;
        }

        public Builder closeUSD(double closeUSD){
            this.closeUSD = closeUSD;
            return this;
        }

        public Builder marketCap(double marketCap){
            this.marketCap = marketCap;
            return this;
        }

        public Builder volume(double volume){
            this.volume = volume;
            return this;
        }

        public Builder date(String date){
            this.date = date;
            return this;
        }

        public CryptoUnit build(){
            return new CryptoUnit(this);
        }

    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpenUSD() {
        return openUSD;
    }

    public double getCloseUSD() {
        return closeUSD;
    }

    public double getHighUSD() {
        return highUSD;
    }

    public double getLowUSD() {
        return lowUSD;
    }

    public double getVolume() {
        return volume;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "\n" + "CryptoUnit {" +
            "date=" + date +
            ", close=" + close + 
            ", closeUSD=" + closeUSD + 
            ", high=" + high + 
            ", highUSD=" + highUSD + 
            ", low=" + low + 
            ", lowUSD=" + lowUSD + 
            ", marketCap=" + marketCap + 
            ", open=" + open + 
            ", openUSD=" + openUSD + 
            ", volume=" + volume + 
        "}";
    }

}
