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
package com.crazzyghost.alphavantage.marketstatus;

import com.crazzyghost.alphavantage.*;
import com.crazzyghost.alphavantage.marketstatus.request.MarketStatusRequest;
import com.crazzyghost.alphavantage.marketstatus.response.MarketStatusResponse;

import java.util.Map;

/**
 * Access to Global Market Status.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public final class MarketStatus implements Fetcher {

    private final Config config;
    private final MarketStatusRequest.Builder builder;
    private SuccessCallback<MarketStatusResponse> successCallback;
    private FailureCallback failureCallback;

    public MarketStatus(Config config) {
        this.config = config;
        this.builder = new MarketStatusRequest.Builder();
    }

    /**
     * Handles request success
     *
     * @param callback successful fetch handler
     * @return this instance, for method chaining
     */
    public MarketStatus onSuccess(SuccessCallback<MarketStatusResponse> callback) {
        this.successCallback = callback;
        return this;
    }

    /**
     * Handles request failure
     *
     * @param callback failed fetch handler
     * @return this instance, for method chaining
     */
    public MarketStatus onFailure(FailureCallback callback) {
        this.failureCallback = callback;
        return this;
    }

    /**
     * Makes a blocking synchronous http request to fetch the data.
     *
     * <p>Using this method will overwrite any async callback.
     *
     * @return the global market status data returned by the API
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.8.0
     */
    public MarketStatusResponse fetchSync() throws AlphaVantageException {
        this.successCallback = null;
        this.failureCallback = null;
        return MarketStatusResponse.of(RequestExecutor.fetchSync(config, builder.build()));
    }

    @Override
    public void fetch() {
        RequestExecutor.fetchAsync(
                config,
                builder.build(),
                (Map<String, Object> data) ->
                        ResponseDispatcher.dispatch(
                                MarketStatusResponse.of(data), successCallback, failureCallback),
                failureCallback);
    }
}
