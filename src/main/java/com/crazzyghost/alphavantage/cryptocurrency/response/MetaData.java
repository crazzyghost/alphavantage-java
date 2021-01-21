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

    public MetaData(
        String information,
        String digitalCurrencyCode,
        String digitalCurrencyName,
        String marketCode,
        String marketName,
        String lastRefreshed,
        String timeZone
    ) {
        this.information = information;
        this.digitalCurrencyCode = digitalCurrencyCode;
        this.digitalCurrencyName = digitalCurrencyName;
        this.marketCode = marketCode;
        this.marketName = marketName;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;
    }


    public static MetaData empty(){
        return new MetaData("","","","", "", "", "");
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
                '}';
    }
}
