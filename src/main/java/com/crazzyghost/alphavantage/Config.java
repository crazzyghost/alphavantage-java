package com.crazzyghost.alphavantage;

public class Config {

    private String key;
    private int timeOut;
    public static String BASE_URL = "https://www.alphavantage.co/query?";

    public Config(Builder builder) {
        this.key = builder.key;
        this.timeOut = builder.timeOut;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public String getKey() {
        return key;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String key;
        private int timeOut;

        public Builder key(String key){
            this.key = key;
            return this;
        }

        public Builder timeOut(int timeOut){
            this.timeOut = timeOut;
            return this;
        }

        public Config build(){
            return new Config(this);
        }
    }
}
