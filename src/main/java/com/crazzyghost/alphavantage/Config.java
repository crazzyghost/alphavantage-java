package com.crazzyghost.alphavantage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class Config {

    private String key;
    private int timeOut;
    private OkHttpClient httpClient;
    public static String BASE_URL = "https://www.alphavantage.co/query?";

    public Config(Builder builder) {
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

    public static Builder builder(){
        return new Builder();
    }

    private OkHttpClient defaultClient(int timeOut){
        return new OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .build();
    }

    public static class Builder{
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
