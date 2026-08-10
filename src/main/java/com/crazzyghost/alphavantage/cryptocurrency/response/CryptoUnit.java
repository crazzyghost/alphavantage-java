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
 * One open-high-low-close-volume bar of a digital currency time series, priced in
 * the market the request asked for. Any field the API omitted is {@code null}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class CryptoUnit {
    /** The bar's opening price, in the market currency the request asked for. */
    private final Double open;
    /** The bar's closing price, in the market currency the request asked for. */
    private final Double close;
    /** The bar's highest price, in the market currency the request asked for. */
    private final Double high;
    /** The bar's lowest price, in the market currency the request asked for. */
    private final Double low;
    /** The amount of the digital currency traded during the bar. */
    private final Double volume;

    /**
     * Creates a bar from the given builder's values.
     *
     * @param builder the builder carrying this bar's OHLCV values
     */
    public CryptoUnit(Builder builder) {
        this.open = builder.open;
        this.close = builder.close;
        this.high = builder.high;
        this.low = builder.low;
        this.volume = builder.volume;
    }


    /**
     * Collects a bar's open-high-low-close-volume values and assembles them into
     * a {@link CryptoUnit}.
     */
    public static class Builder {
        private Double open;
        private Double high;
        private Double low;
        private Double close;
        private Double volume;

        /**
         * Sets the bar's opening price.
         *
         * @param open the opening price, in the market currency
         * @return this builder, for method chaining
         */
        public Builder open(Double open){
            this.open = open;
            return this;
        }

        /**
         * Sets the bar's highest price.
         *
         * @param high the highest price, in the market currency
         * @return this builder, for method chaining
         */
        public Builder high(Double high){
            this.high = high;
            return this;
        }

        /**
         * Sets the bar's lowest price.
         *
         * @param low the lowest price, in the market currency
         * @return this builder, for method chaining
         */
        public Builder low(Double low){
            this.low = low;
            return this;
        }

        /**
         * Sets the bar's closing price.
         *
         * @param close the closing price, in the market currency
         * @return this builder, for method chaining
         */
        public Builder close(Double close){
            this.close = close;
            return this;
        }

        /**
         * Sets the amount of the digital currency traded during the bar.
         *
         * @param volume the trade volume, in units of the digital currency
         * @return this builder, for method chaining
         */
        public Builder volume(Double volume){
            this.volume = volume;
            return this;
        }

        /**
         * Assembles the values set so far into a bar.
         *
         * @return a new bar carrying this builder's values
         */
        public CryptoUnit build(){
            return new CryptoUnit(this);
        }

    }

    /**
     * Returns the bar's opening price.
     *
     * @return the opening price, in the market currency, or {@code null} if the
     *         API omitted it
     */
    public Double getOpen() {
        return open;
    }

    /**
     * Returns the bar's closing price.
     *
     * @return the closing price, in the market currency, or {@code null} if the
     *         API omitted it
     */
    public Double getClose() {
        return close;
    }

    /**
     * Returns the bar's highest price.
     *
     * @return the highest price, in the market currency, or {@code null} if the
     *         API omitted it
     */
    public Double getHigh() {
        return high;
    }

    /**
     * Returns the bar's lowest price.
     *
     * @return the lowest price, in the market currency, or {@code null} if the
     *         API omitted it
     */
    public Double getLow() {
        return low;
    }

    /**
     * Returns the amount of the digital currency traded during the bar.
     *
     * @return the trade volume, in units of the digital currency, or
     *         {@code null} if the API omitted it
     */
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
