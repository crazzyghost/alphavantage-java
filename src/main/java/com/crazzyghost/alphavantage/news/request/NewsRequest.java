package com.crazzyghost.alphavantage.news.request;

import com.crazzyghost.alphavantage.parameters.Function;

public class NewsRequest {

  protected Function function;
  protected String tickers;   // Optional: Filter by tickers
  protected String topics;    // Optional: Filter by topics
  protected String timeFrom;  // Optional: Filter by starting time
  protected String timeTo;    // Optional: Filter by ending time
  protected String sort;      // Optional: Sorting options
  protected int limit;        // Optional: Limit of results

  protected NewsRequest(Builder builder) {
    this.function = builder.function;
    this.tickers = builder.tickers;
    this.topics = builder.topics;
    this.timeFrom = builder.timeFrom;
    this.timeTo = builder.timeTo;
    this.sort = builder.sort;
    this.limit = builder.limit;
  }

  public static class Builder {

    public Function function;
    protected String tickers;
    protected String topics;
    protected String timeFrom;
    protected String timeTo;
    protected String sort;
    protected int limit = 50; // Default limit

    public Builder() {
      this.function = Function.NEWS_SENTIMENT;
    }

    public Builder function(Function function) {
      this.function = function;
      return this;
    }

    public Builder tickers(String tickers) {
      this.tickers = tickers;
      return this;
    }

    public Builder topics(String topics) {
      this.topics = topics;
      return this;
    }

    public Builder timeFrom(String timeFrom) {
      this.timeFrom = timeFrom;
      return this;
    }

    public Builder timeTo(String timeTo) {
      this.timeTo = timeTo;
      return this;
    }

    public Builder sort(String sort) {
      this.sort = sort;
      return this;
    }

    public Builder limit(int limit) {
      this.limit = limit;
      return this;
    }

    public NewsRequest build() {
      return new NewsRequest(this);  // Use constructor to create instance
    }
  }
}