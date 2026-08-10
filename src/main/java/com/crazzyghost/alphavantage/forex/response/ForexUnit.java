/*
 *
 * Copyright (c) 2025 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage.forex.response;

/**
 * One open-high-low-close bar of a foreign exchange series, covering whichever span the
 * requested cadence samples at — a minute, a day, a week or a month.
 * <p>
 * All four prices are exchange rates in the same direction as the request that produced
 * them: units of the to-symbol per one unit of the from-symbol, so a bar from a
 * {@code EUR} to {@code USD} request reads as dollars per euro. There is no volume
 * field because Alpha Vantage does not report one for currency pairs.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class ForexUnit {

    private double open;
    private double high;
    private double low;
    private double close;
    private String date;

    private ForexUnit(Builder builder) {
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
        this.date = builder.date;
    }

    /**
     * Assembles a single bar field by field.
     * <p>
     * It exists for {@link ForexResponse}'s parser, which reads the prices out of the
     * API payload one key at a time; application code normally receives finished units
     * from {@link ForexResponse#getForexUnits()} rather than building its own.
     */
    public static class Builder{

        double open;
        double high;
        double low;
        double close;
        String date;

        /**
         * Sets the rate at the start of the bar's span.
         *
         * @param open the opening exchange rate
         * @return this builder, for method chaining
         */
        public Builder open(double open){
            this.open = open;
            return this;
        }

        /**
         * Sets the highest rate reached during the bar's span.
         *
         * @param high the highest exchange rate
         * @return this builder, for method chaining
         */
        public Builder high(double high){
            this.high = high;
            return this;
        }

        /**
         * Sets the lowest rate reached during the bar's span.
         *
         * @param low the lowest exchange rate
         * @return this builder, for method chaining
         */
        public Builder low(double low){
            this.low = low;
            return this;
        }

        /**
         * Sets the rate at the end of the bar's span.
         *
         * @param close the closing exchange rate
         * @return this builder, for method chaining
         */
        public Builder close(double close){
            this.close = close;
            return this;
        }

        /**
         * Sets the timestamp identifying the bar.
         *
         * @param date the bar's timestamp, as the API formatted it
         * @return this builder, for method chaining
         */
        public Builder date(String date){
            this.date = date;
            return this;
        }

        /**
         * Assembles the values set so far into a bar.
         *
         * @return a bar holding this builder's values
         */
        public ForexUnit build(){
            return new ForexUnit(this);
        }

    }



    @Override
    public String toString() {
        return "\n" + "ForexUnit{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", date=" + date +
                '}';
    }

    /**
     * Gets the exchange rate at the start of this bar's span.
     *
     * @return the opening rate, in units of the to-symbol per one from-symbol
     */
    public double getOpen() {
        return open;
    }

    /**
     * Gets the highest exchange rate reached within this bar's span.
     *
     * @return the high rate, in units of the to-symbol per one from-symbol
     */
    public double getHigh() {
        return high;
    }

    /**
     * Gets the lowest exchange rate reached within this bar's span.
     *
     * @return the low rate, in units of the to-symbol per one from-symbol
     */
    public double getLow() {
        return low;
    }

    /**
     * Gets the exchange rate at the end of this bar's span. On the newest bar of a
     * series this is the pair's latest rate, and it moves until that span closes.
     *
     * @return the closing rate, in units of the to-symbol per one from-symbol
     */
    public double getClose() {
        return close;
    }

    /**
     * Gets the timestamp identifying this bar, in the timezone
     * {@link MetaData#getTimeZone()} reports.
     * <p>
     * The format follows the cadence: intraday bars are stamped
     * {@code yyyy-MM-dd HH:mm:ss}, while daily, weekly and monthly bars are stamped
     * {@code yyyy-MM-dd} with the last trading day of the span they cover.
     *
     * @return the bar's timestamp, as the API formatted it
     */
    public String getDate() {
        return date;
    }

}
