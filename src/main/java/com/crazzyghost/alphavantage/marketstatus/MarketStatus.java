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

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Access to Global Market Status
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
     * @return current instance of {@link MarketStatusResponse}
     */
    public MarketStatus onSuccess(SuccessCallback<MarketStatusResponse> callback) {
        this.successCallback = callback;
        return this;
    }

    /**
     * Handles request failure
     *
     * @param callback failed fetch handler
     * @return current instance of {@link MarketStatusResponse}
     */
    public MarketStatus onFailure(FailureCallback callback) {
        this.failureCallback = callback;
        return this;
    }


    /**
     * Make a blocking synchronous http request to fetch the data.
     * <p>
     * Using this method will overwrite any async callback
     *
     * @throws AlphaVantageException exception thrown
     * @since 1.5.0
     */
    public MarketStatusResponse fetchSync() throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);

        this.successCallback = null;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();

        try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()) {
            return MarketStatusResponse.of(Parser.parseJSON(response.body().string()));
        } catch (IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }

    }

    @Override
    public void fetch() {

        Config.checkNotNullOrKeyEmpty(config);

        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {
                if (failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try (ResponseBody body = response.body()) {
                        MarketStatusResponse marketStatusResponse = MarketStatusResponse.of(Parser.parseJSON(body.string()));
                        if (marketStatusResponse.getErrorMessage() != null && failureCallback != null) {
                            failureCallback.onFailure(new AlphaVantageException(marketStatusResponse.getErrorMessage()));
                        }
                        if (successCallback != null) {
                            successCallback.onSuccess(marketStatusResponse);
                        }
                    }
                } else {
                    if (failureCallback != null) {
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }
}
