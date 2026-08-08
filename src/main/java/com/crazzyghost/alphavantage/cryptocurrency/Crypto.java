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
package com.crazzyghost.alphavantage.cryptocurrency;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.request.CryptoRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.DigitalCurrencyRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.IntradayRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.RatingRequest;
import com.crazzyghost.alphavantage.cryptocurrency.response.CryptoResponse;
import com.crazzyghost.alphavantage.cryptocurrency.response.RatingResponse;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * Access to Crypto Currency Data.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public final class Crypto implements Fetcher {

    private final Config config;
    private CryptoRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public Crypto(Config config){
        this.config = config;
    }

    /**
     * Accesses daily crypto currency data.
     *
     * @return a {@link DailyRequestProxy} instance
     */
    public DailyRequestProxy daily(){
        return new DailyRequestProxy();
    }

    /**
     * Accesses weekly crypto currency data.
     *
     * @return a {@link WeeklyRequestProxy} instance
     */
    public WeeklyRequestProxy weekly(){
        return new WeeklyRequestProxy();
    }

    /**
     * Accesses monthly crypto currency data.
     *
     * @return a {@link MonthlyRequestProxy} instance
     */
    public MonthlyRequestProxy monthly(){
        return new MonthlyRequestProxy();
    }

    /**
     * Accesses crypto currency health index data.
     *
     * @return a {@link RatingRequestProxy} instance
     */
    public RatingRequestProxy rating(){
        return new RatingRequestProxy();
    }

    /**
     * Accesses intraday crypto currency data.
     *
     * @return an {@link IntradayRequestProxy} instance
     */
    public IntradayRequestProxy intraday(){
        return new IntradayRequestProxy();
    }

    /** Fetches crypto currency data asynchronously, dispatching the parsed response to the registered callback. */
    @Override
    public void fetch() {

        Config.checkNotNullOrKeyEmpty(config);
        
        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try (ResponseBody body = response.body()) {
                        parseCryptoResponse(Parser.parseJSON(body.string()));
                    }
                } else {
                    if (failureCallback != null) {
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }

    /**
     * Makes a blocking synchronous http request to fetch the data.
     * This is called by {@link RequestProxy#fetchSync()}.
     * <p>
     * Using this method will overwrite any async callback.
     *
     * @param successCallback internally used {@link SuccessCallback} that receives the parsed response
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.5.0
     */
    private void fetchSync(SuccessCallback<?> successCallback) throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);
        
        this.successCallback = successCallback;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()) {
            parseCryptoResponse(Parser.parseJSON(response.body().string()));
        } catch(IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }        
    }


    /**
     * Parses a JSON response into a {@link CryptoResponse} or a {@link RatingResponse},
     * depending on the function the request was built for.
     *
     * @param data parsed JSON response
     */
    private void parseCryptoResponse(Map<String, Object> data) {
        switch (builder.function) {
            case CRYPTO_RATING:
                parseRatingResponse(data);
                break;
            case CRYPTO_INTRADAY:
            case DIGITAL_CURRENCY_DAILY:
            case DIGITAL_CURRENCY_MONTHLY:
            case DIGITAL_CURRENCY_WEEKLY:
                parseDigitalCurrencyResponse(data);
                break;
            default:
                break;
        }
    }


    /**
     * Parses digital currency data and dispatches it to the registered callback.
     *
     * @param data parsed JSON data
     */
    @SuppressWarnings("unchecked")
    private void parseDigitalCurrencyResponse(Map<String, Object> data){
        CryptoResponse response = CryptoResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null) {
            ((Fetcher.SuccessCallback<CryptoResponse>)successCallback).onSuccess(response);
        }
    }

    /**
     * Parses crypto health index data and dispatches it to the registered callback.
     *
     * @param data parsed JSON data
     */
    @SuppressWarnings("unchecked")
    private void parseRatingResponse(Map<String, Object> data){
        RatingResponse response = RatingResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null) {
            ((Fetcher.SuccessCallback<RatingResponse>)successCallback).onSuccess(response);
        }
    }
    



    /**
     * An abstract proxy for building requests.
     * Adds the functionality of adding callbacks and a terminal method
     * for fetching data.
     *
     * @param <T> a concrete {@link RequestProxy} implementation
     * @param <U> the response type this proxy's terminal fetch returns
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?, U>, U> {

        protected CryptoRequest.Builder<?> builder;
        protected U syncResponse; // a synchronous response

        private RequestProxy() { }

        public T forSymbol(String symbol) {
            this.builder.symbol(symbol);
            return (T)this;
        }

        public T market(String symbol) {
            this.builder.market(symbol);
            return (T)this;
        }

        public T onSuccess(SuccessCallback<?> callback) {
            Crypto.this.successCallback = callback;
            return (T)this;
        }

        public T onFailure(FailureCallback callback) {
            Crypto.this.failureCallback = callback;
            return (T)this;
        }

        public void fetch() {
            Crypto.this.builder = this.builder;
            Crypto.this.fetch();
        }

        public void setSyncResponse(U response) {
            this.syncResponse = response;
        }


        /**
         * Sets the right builder and makes a synchronous request using
         * {@link Crypto#fetch()}.
         * <p>
         * When calling this method, any async callbacks will be overwritten.
         *
         * @return the api response
         * @throws AlphaVantageException if the request fails or the response cannot be read
         */
        public U fetchSync() throws AlphaVantageException {
            SuccessCallback<U> callback = this::setSyncResponse;
            Crypto.this.builder = this.builder;
            Crypto.this.fetchSync(callback);
            return this.syncResponse;            
        }

    }

    /** Proxy for building a daily {@link DigitalCurrencyRequest}. */
    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy, CryptoResponse> {
        public DailyRequestProxy() {
            super();
            builder = new DigitalCurrencyRequest.Builder().function(Function.DIGITAL_CURRENCY_DAILY);
        }
    }

    /** Proxy for building a weekly {@link DigitalCurrencyRequest}. */
    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy, CryptoResponse> {
        public WeeklyRequestProxy() {
            builder = new DigitalCurrencyRequest.Builder().function(Function.DIGITAL_CURRENCY_WEEKLY);
        }
    }

    /** Proxy for building a monthly {@link DigitalCurrencyRequest}. */
    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy, CryptoResponse> {
        public MonthlyRequestProxy() {
            builder = new DigitalCurrencyRequest.Builder().function(Function.DIGITAL_CURRENCY_MONTHLY);
        }
    }

    /** Proxy for building an {@link IntradayRequest}. */
    public class IntradayRequestProxy extends RequestProxy<IntradayRequestProxy, CryptoResponse> {
        public IntradayRequestProxy() {
            builder = new IntradayRequest.Builder();
        }
    }

    /** Proxy for building a {@link RatingRequest}. */
    public class RatingRequestProxy extends RequestProxy<RatingRequestProxy, RatingResponse> {
        public  RatingRequestProxy(){
            builder = new RatingRequest.Builder();
        }
    }
}