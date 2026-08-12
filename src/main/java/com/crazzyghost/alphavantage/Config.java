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
package com.crazzyghost.alphavantage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
/**
 * Holds the library configuration parameters: the api key every request is signed
 * with, and the http client and timeout used to make those requests.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class Config {

    /**
     * The Alpha Vantage query endpoint. Every request the library makes is built
     * by appending url parameters to this base.
     */
    public static final String BASE_URL = "https://www.alphavantage.co/query?";

    private final String key;
    private final int timeOut;
    private final OkHttpClient httpClient;

    private Config(Builder builder) {
        this.key = builder.key;
        this.timeOut = builder.timeOut;
        this.httpClient = builder.httpClient == null ? defaultClient(builder.timeOut): builder.httpClient;
    }

    /**
     * Gets the connect timeout, in seconds, applied to the default http client.
     * Has no effect when a client was supplied explicitly.
     *
     * @return the connect timeout in seconds
     */
    public int getTimeOut() {
        return timeOut;
    }


    /**
     * Gets the api key every request made with this config is signed with.
     *
     * @return the api key, or {@code null} if none was set
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the http client used to make requests. This is either the client
     * supplied to the builder or, if none was, a default one configured with
     * this config's timeout.
     *
     * @return the http client requests are made with
     */
    public OkHttpClient getOkHttpClient(){
        return this.httpClient;
    }

    /**
     * Gets a builder instance.
     *
     * @return a new {@link Builder}
     */
    public static Builder builder(){
        return new Builder();
    }

    /**
     * Configures a default http client for the library.
     *
     * @param timeOut connect timeout
     * @return a default HTTP client for fetching data
     */
    private OkHttpClient defaultClient(int timeOut){
        return new OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .build();
    }

    /**
     * Checks that a config instance is neither null nor missing its api key, and
     * throws if it is. Called before every request so a misconfigured client fails
     * with a clear message rather than an unauthorized response.
     *
     * @param config config instance
     * @since 1.4.0
     */
    public static void checkNotNullOrKeyEmpty(Config config) {
        if (config == null) throw new AlphaVantageException("Config not set");
        if (config.getKey() == null) throw new AlphaVantageException("API Key not set");
    }


    /**
     * Assembles a {@link Config} a piece at a time. This is the standard way to
     * construct one, obtained from {@link Config#builder()}; the setters return
     * the builder itself so calls can be chained.
     * <p>
     * Only the api key is normally worth setting — leave the http client unset
     * and the built config uses a default one.
     *
     * @author Sylvester Sefa-Yeboah
     * @since 1.0.0
     */
    public static class Builder {

        private String key;
        private int timeOut;
        private OkHttpClient httpClient;

        /**
         * Sets the api key requests are signed with.
         *
         * @param  key  the Alpha Vantage api key
         * @return this builder
         */
        public Builder key(String key){
            this.key = key;
            return this;
        }

        /**
         * Sets the connect timeout, in seconds, for the default http client.
         * Ignored when a client is supplied via {@link #httpClient(OkHttpClient)}.
         *
         * @param  timeOut  the connect timeout in seconds
         * @return this builder
         */
        public Builder timeOut(int timeOut){
            this.timeOut = timeOut;
            return this;
        }

        /**
         * Sets the http client used to make requests, replacing the default one.
         * Use this to share a client across an application or to attach
         * interceptors, caching, or a custom connection pool.
         *
         * @param  httpClient  the http client to make requests with
         * @return this builder
         */
        public Builder httpClient(OkHttpClient httpClient){
            this.httpClient = httpClient;
            return this;
        }

        /**
         * Builds a config from the values set on this builder. The api key is not
         * checked here — see {@link Config#checkNotNullOrKeyEmpty(Config)}, which
         * is what validates it.
         *
         * @return a config carrying this builder's values
         */
        public Config build(){
            return new Config(this);
        }
    }

}
