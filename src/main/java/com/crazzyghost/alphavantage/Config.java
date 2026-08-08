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

    public static final String BASE_URL = "https://www.alphavantage.co/query?";

    private final String key;
    private final int timeOut;
    private final OkHttpClient httpClient;

    private Config(Builder builder) {
        this.key = builder.key;
        this.timeOut = builder.timeOut;
        this.httpClient = builder.httpClient == null ? defaultClient(builder.timeOut): builder.httpClient;
    }

    public int getTimeOut() {
        return timeOut;
    }


    public String getKey() {
        return key;
    }

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


    public static class Builder {

        private String key;
        private int timeOut;
        private OkHttpClient httpClient;

        public Builder key(String key){
            this.key = key;
            return this;
        }

        public Builder timeOut(int timeOut){
            this.timeOut = timeOut;
            return this;
        }

        public Builder httpClient(OkHttpClient httpClient){
            this.httpClient = httpClient;
            return this;
        }

        public Config build(){
            return new Config(this);
        }
    }

}
