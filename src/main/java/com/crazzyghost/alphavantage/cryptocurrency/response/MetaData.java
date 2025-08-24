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
 * Crypto Currency MetaData
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class MetaData {
    private final String information;
    private final String digitalCurrencyCode;
    private final String digitalCurrencyName;
    private final String marketCode;
    private final String marketName;
    private final String lastRefreshed;
    private final String timeZone;
    private final String interval;
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


    public static MetaData empty() {
        return new MetaData(new Builder());
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getDigitalCurrencyCode() {
        return digitalCurrencyCode;
    }

    public String getDigitalCurrencyName() {
        return digitalCurrencyName;
    }

    public String getInformation() {
        return information;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getMarketCode() {
        return marketCode;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getInterval() {
        return interval;
    }

    public String getOutputSize() {
        return outputSize;
    }

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

        public Builder information(String information) {
            this.information = information;
            return this;
        }

        public Builder digitalCurrencyCode(String digitalCurrencyCode) {
            this.digitalCurrencyCode = digitalCurrencyCode;
            return this;
        }

        public Builder digitalCurrencyName(String digitalCurrencyName) {
            this.digitalCurrencyName = digitalCurrencyName;
            return this;
        }

        public Builder marketCode(String marketCode) {
            this.marketCode = marketCode;
            return this;
        }

        public Builder marketName(String marketName) {
            this.marketName = marketName;
            return this;
        }

        public Builder lastRefreshed(String lastRefreshed) {
            this.lastRefreshed = lastRefreshed;
            return this;
        }

        public Builder timeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder interval(String interval) {
            this.interval = interval;
            return this;
        }

        public Builder outputSize(String outputSize) {
            this.outputSize = outputSize;
            return this;
        }

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
