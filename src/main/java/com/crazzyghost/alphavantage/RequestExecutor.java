/*
 *
 * Copyright (c) 2026 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage;

import com.crazzyghost.alphavantage.parser.ParserDelegate;
import com.crazzyghost.alphavantage.parser.ParserDelegates;

import okhttp3.Response;
import okhttp3.ResponseBody;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * The shared HTTP fetch logic every proxy class (e.g. {@code TimeSeries}, {@code Forex})
 * delegates to instead of building and executing requests itself.
 *
 * <p>Builds the request url from a request object via {@link UrlExtractor}, executes it against
 * {@code config}'s http client, and parses the response body with the {@link ParserDelegate}
 * {@link ParserDelegates#delegateFor(Object) resolved} for that request. Parsing here only covers
 * turning the raw body into a response object; whether that response carries an API-level error is
 * for the caller to check, typically via {@link ResponseDispatcher}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.9.0
 */
public final class RequestExecutor {

    private RequestExecutor() {}

    /**
     * Executes {@code request} synchronously and returns the parsed response.
     *
     * @param <T> the response type
     * @param config the library configuration, supplying the api key and http client
     * @param request the endpoint request object
     * @return the parsed response
     * @throws AlphaVantageException if {@code config} is invalid, the request fails, or the
     *     response body cannot be parsed
     */
    public static <T> T fetchSync(Config config, Object request) {
        Config.checkNotNullOrKeyEmpty(config);
        ParserDelegate<T> delegate = ParserDelegates.delegateFor(request);
        try (Response response =
                config.getOkHttpClient()
                        .newCall(UrlExtractor.extract(request, config.getKey()))
                        .execute()) {
            return delegate.parse(response.body().string());
        } catch (IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }
    }

    /**
     * Executes {@code request} asynchronously. {@code onParsed} is called with the parsed
     * response on a successful http response; {@code onFailure} is called for a transport failure,
     * an unsuccessful http status, or a response body that cannot be parsed.
     *
     * @param <T> the response type
     * @param config the library configuration, supplying the api key and http client
     * @param request the endpoint request object
     * @param onParsed called with the parsed response when the request succeeds
     * @param onFailure called on failure, or {@code null} to ignore failures
     * @throws AlphaVantageException if {@code config} is invalid
     */
    public static <T> void fetchAsync(
            Config config,
            Object request,
            Consumer<T> onParsed,
            Fetcher.FailureCallback onFailure) {
        Config.checkNotNullOrKeyEmpty(config);
        ParserDelegate<T> delegate = ParserDelegates.delegateFor(request);

        config.getOkHttpClient()
                .newCall(UrlExtractor.extract(request, config.getKey()))
                .enqueue(
                        new okhttp3.Callback() {
                            @Override
                            public void onFailure(
                                    @NotNull okhttp3.Call call, @NotNull IOException e) {
                                if (onFailure != null)
                                    onFailure.onFailure(new AlphaVantageException(e.getMessage()));
                            }

                            @Override
                            public void onResponse(
                                    @NotNull okhttp3.Call call, @NotNull Response response) {
                                if (!response.isSuccessful()) {
                                    if (onFailure != null)
                                        onFailure.onFailure(new AlphaVantageException());
                                    return;
                                }
                                try (ResponseBody body = response.body()) {
                                    onParsed.accept(delegate.parse(body.string()));
                                } catch (IOException e) {
                                    if (onFailure != null) {
                                        onFailure.onFailure(
                                                new AlphaVantageException(e.getMessage()));
                                    }
                                }
                            }
                        });
    }
}
