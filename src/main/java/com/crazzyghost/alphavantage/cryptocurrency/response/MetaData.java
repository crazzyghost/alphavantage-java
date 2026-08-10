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

import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * The header block Alpha Vantage returns alongside a digital currency time series:
 * which currency and market the data covers, when it was last refreshed, and the
 * time zone, interval and output size it was served under. Fields that do not apply
 * to the requested endpoint come back empty rather than absent.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class MetaData {
    /**
     * A human-readable description of the endpoint that produced this response.
     * Shared with the {@code MetaData} classes of other packages.
     */
    private final String information;
    /**
     * The digital currency's symbol, for example {@code BTC}. Specific to this
     * package's metadata shape; other packages' {@code MetaData} classes key on
     * a plain {@code symbol} instead.
     */
    private final String digitalCurrencyCode;
    /**
     * The digital currency's full name, for example {@code Bitcoin}. Specific to
     * this package's metadata shape.
     */
    private final String digitalCurrencyName;
    /**
     * The market currency's code, for example {@code USD}. Specific to this
     * package's metadata shape.
     */
    private final String marketCode;
    /**
     * The market currency's full name, for example {@code United States
     * Dollar}. Specific to this package's metadata shape.
     */
    private final String marketName;
    /**
     * The date the series was last refreshed. Shared with the {@code MetaData}
     * classes of other packages.
     */
    private final String lastRefreshed;
    /**
     * The time zone {@link #lastRefreshed} is expressed in. Shared with the
     * {@code MetaData} classes of other packages.
     */
    private final String timeZone;
    /**
     * The interval between data points. Set only for {@code CRYPTO_INTRADAY}
     * responses; {@code null} for daily, weekly and monthly series.
     */
    private final String interval;
    /**
     * The amount of historical data returned. Set only for
     * {@code CRYPTO_INTRADAY} responses; {@code null} for daily, weekly and
     * monthly series.
     */
    private final String outputSize;

    private MetaData(Builder builder) {
        this.information = builder.information;
        this.digitalCurrencyCode = builder.digitalCurrencyCode;
        this.digitalCurrencyName = builder.digitalCurrencyName;
        this.marketCode = builder.marketCode;
        this.marketName = builder.marketName;
        this.lastRefreshed = builder.lastRefreshed;
        this.timeZone = builder.timeZone;
        this.interval = builder.interval;
        this.outputSize = builder.outputSize;
    }


    /**
     * Returns a {@link MetaData} with every field unset, used when the API
     * response carries an error instead of a real time series.
     *
     * @return an empty metadata instance
     */
    public static MetaData empty() {
        return new MetaData(new Builder());
    }

    /**
     * Creates a new builder for assembling a {@link MetaData} instance.
     *
     * @return a new builder
     */
    public static Builder builder(){
        return new Builder();
    }

    /**
     * Returns the digital currency's symbol.
     *
     * @return the digital currency code, for example {@code BTC}
     */
    public String getDigitalCurrencyCode() {
        return digitalCurrencyCode;
    }

    /**
     * Returns the digital currency's full name.
     *
     * @return the digital currency name, for example {@code Bitcoin}
     */
    public String getDigitalCurrencyName() {
        return digitalCurrencyName;
    }

    /**
     * Returns a human-readable description of the endpoint that produced this
     * response.
     *
     * @return the endpoint description
     */
    public String getInformation() {
        return information;
    }

    /**
     * Returns the date the series was last refreshed.
     *
     * @return the last-refreshed date, in the time zone given by
     *         {@link #getTimeZone()}
     */
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    /**
     * Returns the time zone {@link #getLastRefreshed()} is expressed in.
     *
     * @return the time zone name
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Returns the market currency's code.
     *
     * @return the market code, for example {@code USD}
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * Returns the market currency's full name.
     *
     * @return the market name, for example {@code United States Dollar}
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * Returns the interval between data points.
     *
     * @return the interval, or {@code null} for daily, weekly and monthly
     *         series, which have no sub-daily interval
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Returns the amount of historical data returned.
     *
     * @return the output size, or {@code null} for daily, weekly and monthly
     *         series
     */
    public String getOutputSize() {
        return outputSize;
    }

    /**
     * Collects a digital currency time series' header fields and assembles them
     * into a {@link MetaData}.
     */
    public static class Builder {
        private String information;
        private String digitalCurrencyCode;
        private String digitalCurrencyName;
        private String marketCode;
        private String marketName;
        private String lastRefreshed;
        private String timeZone;
        private String interval;
        private String outputSize;

        /**
         * Sets the endpoint description.
         *
         * @param information a human-readable description of the endpoint
         * @return this builder, for method chaining
         */
        public Builder information(String information) {
            this.information = information;
            return this;
        }

        /**
         * Sets the digital currency's symbol.
         *
         * @param digitalCurrencyCode the digital currency code, for example
         *                            {@code BTC}
         * @return this builder, for method chaining
         */
        public Builder digitalCurrencyCode(String digitalCurrencyCode) {
            this.digitalCurrencyCode = digitalCurrencyCode;
            return this;
        }

        /**
         * Sets the digital currency's full name.
         *
         * @param digitalCurrencyName the digital currency name, for example
         *                            {@code Bitcoin}
         * @return this builder, for method chaining
         */
        public Builder digitalCurrencyName(String digitalCurrencyName) {
            this.digitalCurrencyName = digitalCurrencyName;
            return this;
        }

        /**
         * Sets the market currency's code.
         *
         * @param marketCode the market code, for example {@code USD}
         * @return this builder, for method chaining
         */
        public Builder marketCode(String marketCode) {
            this.marketCode = marketCode;
            return this;
        }

        /**
         * Sets the market currency's full name.
         *
         * @param marketName the market name, for example
         *                   {@code United States Dollar}
         * @return this builder, for method chaining
         */
        public Builder marketName(String marketName) {
            this.marketName = marketName;
            return this;
        }

        /**
         * Sets the date the series was last refreshed.
         *
         * @param lastRefreshed the last-refreshed date
         * @return this builder, for method chaining
         */
        public Builder lastRefreshed(String lastRefreshed) {
            this.lastRefreshed = lastRefreshed;
            return this;
        }

        /**
         * Sets the time zone {@link #lastRefreshed} is expressed in.
         *
         * @param timeZone the time zone name
         * @return this builder, for method chaining
         */
        public Builder timeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        /**
         * Sets the interval between data points. Only applicable to
         * {@code CRYPTO_INTRADAY} responses.
         *
         * @param interval the interval between two consecutive data points
         * @return this builder, for method chaining
         */
        public Builder interval(String interval) {
            this.interval = interval;
            return this;
        }

        /**
         * Sets the amount of historical data returned. Only applicable to
         * {@code CRYPTO_INTRADAY} responses.
         *
         * @param outputSize the amount of historical data returned
         * @return this builder, for method chaining
         */
        public Builder outputSize(String outputSize) {
            this.outputSize = outputSize;
            return this;
        }

        /**
         * Assembles the values set so far into a metadata instance.
         *
         * @return a new metadata instance carrying this builder's values
         */
        public MetaData build() {
            return new MetaData(this);
        }
    }


    @Override
    public String toString() {
        return "MetaData{" +
                "information='" + information + '\'' +
                ", digitalCurrencyCode='" + digitalCurrencyCode + '\'' +
                ", digitalCurrencyName='" + digitalCurrencyName + '\'' +
                ", marketCode='" + marketCode + '\'' +
                ", marketName='" + marketName + '\'' +
                ", lastRefreshed='" + lastRefreshed + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", interval=" + interval +
                ", outputSize=" + outputSize +
                '}';
    }
}
