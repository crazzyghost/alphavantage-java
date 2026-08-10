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
package com.crazzyghost.alphavantage.timeseries.response;


/**
 * One open-high-low-close-volume bar of a stock time series, covering whichever span
 * the requested cadence samples at — minutes, a day, a week or a month.
 * <p>
 * The four prices are quoted in the ticker's listing currency and volume counts shares
 * traded over the span, so a monthly bar's volume is the month's total rather than a
 * daily average.
 * <p>
 * Three further fields — {@link #getAdjustedClose()}, {@link #getDividendAmount()} and
 * {@link #getSplitCoefficient()} — only carry values when the request went to the
 * adjusted variant of a cadence. They are primitive {@code double} fields, so an
 * unadjusted bar reports them as {@code 0.0} rather than as absent, and {@code 0.0} on
 * its own does not distinguish "not requested" from "no dividend paid". The cadence the
 * bar came from is what tells those apart.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public class StockUnit {

    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double adjustedClose;
    private final long volume;
    private final double dividendAmount;
    private final double splitCoefficient;
    private final String dateTime;


    private StockUnit(Builder builder) {
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
        this.adjustedClose = builder.adjustedClose;
        this.volume = builder.volume;
        this.dividendAmount = builder.dividendAmount;
        this.splitCoefficient = builder.splitCoefficient;
        this.dateTime = builder.dateTime;
    }


    /**
     * Assembles a single bar field by field.
     * <p>
     * It exists for {@link TimeSeriesResponse}'s parser, which reads the prices out of
     * the API payload one key at a time and sets only the fields the cadence in hand
     * actually returned; application code normally receives finished units from
     * {@link TimeSeriesResponse#getStockUnits()} rather than building its own.
     */
    public static class Builder{

        double open;
        double high;
        double low;
        double close;
        double adjustedClose;
        long volume;
        double dividendAmount;
        double splitCoefficient;
        String dateTime;

        /**
         * Sets the price at the start of the bar's span.
         *
         * @param open the opening price
         * @return this builder, for method chaining
         */
        public Builder open(double open){
            this.open = open;
            return this;
        }

        /**
         * Sets the highest price reached during the bar's span.
         *
         * @param high the highest traded price
         * @return this builder, for method chaining
         */
        public Builder high(double high){
            this.high = high;
            return this;
        }
        /**
         * Sets the lowest price reached during the bar's span.
         *
         * @param low the lowest traded price
         * @return this builder, for method chaining
         */
        public Builder low(double low){
            this.low = low;
            return this;
        }

        /**
         * Sets the price at the end of the bar's span, as quoted at the time.
         *
         * @param close the closing price
         * @return this builder, for method chaining
         */
        public Builder close(double close){
            this.close = close;
            return this;
        }
        /**
         * Sets the closing price restated for splits and dividends since the bar. Only
         * the adjusted cadences report one.
         *
         * @param close the split and dividend adjusted closing price
         * @return this builder, for method chaining
         */
        public Builder adjustedClose(double close){
            this.adjustedClose = close;
            return this;
        }

        /**
         * Sets the dividend paid per share within the bar's span. Only the adjusted
         * cadences report one.
         *
         * @param dividendAmount the dividend per share, or zero if none was paid
         * @return this builder, for method chaining
         */
        public Builder dividendAmount(double dividendAmount){
            this.dividendAmount = dividendAmount;
            return this;
        }

        /**
         * Sets the number of shares traded during the bar's span.
         *
         * @param volume the traded volume, in shares
         * @return this builder, for method chaining
         */
        public Builder volume(long volume){
            this.volume = volume;
            return this;
        }

        /**
         * Sets the ratio of a share split taking effect within the bar's span. Only the
         * daily adjusted cadence reports one.
         *
         * @param splitCoefficient the split ratio, or {@code 1.0} on a bar with no
         *                         split
         * @return this builder, for method chaining
         */
        public Builder splitCoefficient(double splitCoefficient){
            this.splitCoefficient = splitCoefficient;
            return this;
        }

        /**
         * Sets the timestamp identifying the bar. Named for the parameter it reads —
         * the payload keys its bars by time — while the finished bar exposes the same
         * value through {@link StockUnit#getDate()}.
         *
         * @param dateTime the bar's timestamp, as the API formatted it
         * @return this builder, for method chaining
         */
        public Builder time(String dateTime){
            this.dateTime = dateTime;
            return this;
        }


        /**
         * Assembles the values set so far into a bar.
         *
         * @return a bar holding this builder's values
         */
        public StockUnit build(){
            return new StockUnit(this);
        }
    }

    /**
     * Gets the price at the start of this bar's span.
     *
     * @return the opening price, in the ticker's listing currency
     */
    public double getOpen() {
        return open;
    }

    /**
     * Gets the highest price reached within this bar's span.
     *
     * @return the high price, in the ticker's listing currency
     */
    public double getHigh() {
        return high;
    }

    /**
     * Gets the lowest price reached within this bar's span.
     *
     * @return the low price, in the ticker's listing currency
     */
    public double getLow() {
        return low;
    }

    /**
     * Gets the price at the end of this bar's span, as it was quoted at the time. On
     * the newest bar of a series this is the ticker's latest price, and it moves until
     * that span closes.
     *
     * @return the closing price, in the ticker's listing currency
     */
    public double getClose() {
        return close;
    }

    /**
     * Gets the closing price restated in today's share terms, so that a series of
     * adjusted closes is comparable across splits and dividends where a series of raw
     * closes is not. This is the field to chart or compute returns from.
     *
     * @return the split and dividend adjusted closing price, in the ticker's listing
     *         currency; {@code 0.0} on a bar from an unadjusted cadence
     */
    public double getAdjustedClose() {
        return adjustedClose;
    }

    /**
     * Gets how many shares changed hands within this bar's span.
     *
     * @return the traded volume, in shares
     */
    public long getVolume() {
        return volume;
    }

    /**
     * Gets the dividend paid per share within this bar's span. On the wider cadences it
     * is the span's total, so a month paying two dividends reports their sum rather
     * than either one.
     *
     * @return the dividend per share, in the ticker's listing currency; {@code 0.0}
     *         both on a span with no dividend and on a bar from an unadjusted cadence
     */
    public double getDividendAmount() {
        return dividendAmount;
    }

    /**
     * Gets the ratio of a share split taking effect within this bar's span — {@code 2}
     * for a two-for-one split, for instance.
     * <p>
     * Only the daily adjusted cadence reports this. The weekly and monthly adjusted
     * cadences carry an adjusted close and a dividend but no split coefficient, so
     * their bars leave it at {@code 0.0} rather than at the {@code 1.0} a genuine
     * no-split bar carries.
     *
     * @return the split ratio; {@code 1.0} on a daily adjusted bar with no split, and
     *         {@code 0.0} on a bar from any other cadence
     */
    public double getSplitCoefficient() {
        return splitCoefficient;
    }

    /**
     * Gets the timestamp identifying this bar, in the timezone the API reports as US
     * Eastern.
     * <p>
     * The format follows the cadence: intraday bars are stamped
     * {@code yyyy-MM-dd HH:mm:ss}, while daily, weekly and monthly bars are stamped
     * {@code yyyy-MM-dd} with the last trading day of the span they cover.
     *
     * @return the bar's timestamp, as the API formatted it
     */
    public String getDate() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "\n" + "StockUnit{" +
                "open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", adjustedClose=" + adjustedClose +
                ", volume=" + volume +
                ", dividendAmount=" + dividendAmount +
                ", splitCoefficient=" + splitCoefficient +
                ", date=" + dateTime +
                '}';
    }
}
