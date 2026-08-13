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

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.RequestExecutor;
import com.crazzyghost.alphavantage.ResponseDispatcher;

import java.util.Map;

/**
 * Access to Exchange Rate Data.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public final class ExchangeRate implements Fetcher {

    private final Config config;
    private final ExchangeRateRequest.Builder builder;
    private Fetcher.SuccessCallback<ExchangeRateResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public ExchangeRate(Config config) {
        this.config = config;
        this.builder = new ExchangeRateRequest.Builder();
    }

    public ExchangeRate toCurrency(String toCurrency) {
        this.builder.toCurrency(toCurrency);
        return this;
    }

    public ExchangeRate fromCurrency(String fromCurrency) {
        this.builder.fromCurrency(fromCurrency);
        return this;
    }

    /**
     * Handles request success
     *
     * @param callback successful fetch handler
     * @return this instance, for method chaining
     */
    public ExchangeRate onSuccess(SuccessCallback<ExchangeRateResponse> callback) {
        this.successCallback = callback;
        return this;
    }

    /**
     * Handles request failure
     *
     * @param callback failed fetch handler
     * @return this instance, for method chaining
     */
    public ExchangeRate onFailure(FailureCallback callback) {
        this.failureCallback = callback;
        return this;
    }

    /**
     * Makes a blocking synchronous http request to fetch the data.
     *
     * <p>Using this method will overwrite any async callback.
     *
     * @return the exchange rate data returned by the API
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.5.0
     */
    public ExchangeRateResponse fetchSync() throws AlphaVantageException {
        this.successCallback = null;
        this.failureCallback = null;
        return ExchangeRateResponse.of(RequestExecutor.fetchSync(config, builder.build()));
    }

    @Override
    public void fetch() {
        RequestExecutor.fetchAsync(
                config,
                builder.build(),
                (Map<String, Object> data) ->
                        ResponseDispatcher.dispatch(
                                ExchangeRateResponse.of(data), successCallback, failureCallback),
                failureCallback);
    }
}
