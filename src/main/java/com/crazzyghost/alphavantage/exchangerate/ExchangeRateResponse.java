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
package com.crazzyghost.alphavantage.exchangerate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.Parser;

/**
 * ExchangeRate Response
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class ExchangeRateResponse {

    private String fromCurrencyCode;
    private String fromCurrencyName;
    private String toCurrencyCode;
    private String toCurrencyName;
    private double exchangeRate;
    private String lastRefreshed;
    private String timeZone;
    private Double bidPrice;
    private Double askPrice;
    private final String errorMessage;

    private ExchangeRateResponse(
        String fromCurrencyCode,
        String fromCurrencyName,
        String toCurrencyCode,
        String toCurrencyName,
        Double exchangeRate,
        String lastRefreshed,
        String timeZone,
        Double bidPrice,
        Double askPrice
    ) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.fromCurrencyName = fromCurrencyName;
        this.toCurrencyCode = toCurrencyCode;
        this.toCurrencyName = toCurrencyName;
        this.exchangeRate = exchangeRate;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.errorMessage = null;
    }

    private ExchangeRateResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public static ExchangeRateResponse of(Map<String, Object> stringObjectMap) {
        Parser<ExchangeRateResponse> parser = new ExchangeRateParser();
        return parser.parse(stringObjectMap);
    }

    public static class ExchangeRateParser extends Parser<ExchangeRateResponse> {

        @SuppressWarnings("unchecked")
        @Override
        public ExchangeRateResponse parse(Map<String, Object> stringObjectMap) {
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            } else {

                Map<String, String> data;
                try {
                    data = (Map<String, String>) stringObjectMap.get(keys.get(0));
                } catch (ClassCastException e) {
                    return onParseError((String) stringObjectMap.get(keys.get(0)));
                }

                Double bidPrice = data.get("8. Bid Price").equals("-") ? null : Double.parseDouble(data.get("8. Bid Price"));
                Double askPrice = data.get("9. Ask Price").equals("-") ? null : Double.parseDouble(data.get("9. Ask Price"));

                return new ExchangeRateResponse(
                        data.get("1. From_Currency Code"),
                        data.get("2. From_Currency Name"),
                        data.get("3. To_Currency Code"),
                        data.get("4. To_Currency Name"),
                        Double.parseDouble(data.get("5. Exchange Rate")),
                        data.get("6. Last Refreshed"),
                        data.get("7. Time Zone"),
                        bidPrice,
                        askPrice
                );
            }
        }

        @Override
        public ExchangeRateResponse onParseError(String error) {
            return new ExchangeRateResponse(error);
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public String getFromCurrencyName() {
        return fromCurrencyName;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public String getToCurrencyName() {
        return toCurrencyName;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
            "fromCurrencyCode='" + fromCurrencyCode + '\'' +
            ", fromCurrencyName='" + fromCurrencyName + '\'' +
            ", toCurrencyCode='" + toCurrencyCode + '\'' +
            ", toCurrencyName='" + toCurrencyName + '\'' +
            ", exchangeRate=" + exchangeRate +
            ", lastRefreshed='" + lastRefreshed + '\'' +
            ", timeZone='" + timeZone + '\'' +
            ", bidPrice='" + bidPrice + '\'' +
            ", askPrice='" + askPrice+ '\'' +
            ", errorMessage='" + errorMessage + '\'' +
        '}';
    }
}
