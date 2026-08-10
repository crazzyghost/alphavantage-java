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

    /**
     * Parses a decoded {@code CURRENCY_EXCHANGE_RATE} payload into a response.
     *
     * @param stringObjectMap the response body, already decoded from JSON into a map
     * @return the parsed quote, or one carrying an error message if the payload
     *         could not be read
     */
    public static ExchangeRateResponse of(Map<String, Object> stringObjectMap) {
        Parser<ExchangeRateResponse> parser = new ExchangeRateParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Turns a decoded {@code CURRENCY_EXCHANGE_RATE} payload into an
     * {@link ExchangeRateResponse}.
     * <p>
     * The quote sits under the payload's single top-level key, which the parser
     * reaches for by position rather than by name. An error payload carries a plain
     * message string at that position instead of a nested object, so the failed cast
     * is itself the signal to read the value as an error message.
     */
    public static class ExchangeRateParser extends Parser<ExchangeRateResponse> {

        /**
         * Reads the quote fields into a response.
         *
         * @param stringObjectMap the response body, already decoded from JSON into a map
         * @return a response holding the parsed quote, or one holding an error message
         *         if the payload was empty or carried a message in place of a quote
         */
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

        /**
         * Wraps a parse failure or an API error message in a response.
         *
         * @param error the message describing what went wrong
         * @return a response carrying the message, with no quote fields set
         */
        @Override
        public ExchangeRateResponse onParseError(String error) {
            return new ExchangeRateResponse(error);
        }
    }

    /**
     * Returns the error message the API returned, if the request failed.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the ask price, the rate at which the market sells the quote currency.
     * Alpha Vantage reports this only for pairs it has an order book for; where it
     * sends a placeholder instead, the value parses to {@code null}.
     *
     * @return the ask price, or {@code null} if the API did not quote one
     */
    public Double getAskPrice() {
        return askPrice;
    }

    /**
     * Returns the bid price, the rate at which the market buys the quote currency.
     * Alpha Vantage reports this only for pairs it has an order book for; where it
     * sends a placeholder instead, the value parses to {@code null}.
     *
     * @return the bid price, or {@code null} if the API did not quote one
     */
    public Double getBidPrice() {
        return bidPrice;
    }

    /**
     * Returns the code of the base currency, the one being converted from, for
     * example {@code "USD"}. This is the currency the caller passed to
     * {@link ExchangeRate#fromCurrency(String)}.
     *
     * @return the base currency's code
     */
    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    /**
     * Returns the exchange rate between the two currencies: how many units of the
     * quote currency one unit of the base currency buys. The API sends the rate at
     * full precision, so a rate against a currency with a small unit value can carry
     * several more decimal places than the two a price is usually displayed with.
     *
     * @return the rate, in units of the quote currency per unit of the base currency
     */
    public double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Returns the full name of the base currency, for example {@code "United States
     * Dollar"}.
     *
     * @return the base currency's name
     */
    public String getFromCurrencyName() {
        return fromCurrencyName;
    }

    /**
     * Returns the code of the quote currency, the one being converted to, for
     * example {@code "EUR"}. This is the currency the caller passed to
     * {@link ExchangeRate#toCurrency(String)}.
     *
     * @return the quote currency's code
     */
    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    /**
     * Returns the full name of the quote currency, for example {@code "Euro"}.
     *
     * @return the quote currency's name
     */
    public String getToCurrencyName() {
        return toCurrencyName;
    }

    /**
     * Returns the timestamp the rate was last refreshed at, formatted as
     * {@code "yyyy-MM-dd HH:mm:ss"} and expressed in the zone reported by
     * {@link #getTimeZone()}.
     *
     * @return the time the rate was last refreshed
     */
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    /**
     * Returns the time zone {@link #getLastRefreshed()} is expressed in, for example
     * {@code "UTC"}.
     *
     * @return the time zone of the refresh timestamp
     */
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
