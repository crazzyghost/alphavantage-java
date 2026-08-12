package com.crazzyghost.alphavantage.news;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.news.request.NewsRequest;
import com.crazzyghost.alphavantage.news.response.NewsResponse;
import com.crazzyghost.alphavantage.parser.Parser;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

public final class News implements Fetcher {
  private final Config config;
  private final NewsRequest.Builder builder;
  private SuccessCallback<NewsResponse> successCallback;
  private FailureCallback failureCallback;

  public News(Config config) {
    this.config = config;
    this.builder = new NewsRequest.Builder();
  }

  public News setTickers(String tickers) {
    this.builder.tickers(tickers);
    return this;
  }

  public News setTopics(String topics) {
    this.builder.topics(topics);
    return this;
  }

  public News setTimeFrom(String timeFrom) {
    this.builder.timeFrom(timeFrom);
    return this;
  }

  public News setTimeTo(String timeTo) {
    this.builder.timeTo(timeTo);
    return this;
  }

  public News setSort(String sort) {
    this.builder.sort(sort);
    return this;
  }

  public News setLimit(int limit) {
    this.builder.limit(limit);
    return this;
  }

  public News onSuccess(SuccessCallback<NewsResponse> callback) {
    this.successCallback = callback;
    return this;
  }

  public News onFailure(FailureCallback callback) {
    this.failureCallback = callback;
    return this;
  }

  public NewsResponse fetchSync() throws AlphaVantageException {

    Config.checkNotNullOrKeyEmpty(config);

    this.successCallback = null;
    this.failureCallback = null;
    OkHttpClient client = config.getOkHttpClient();

    try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey()))
        .execute()) {
      return NewsResponse.of(
          Parser.parseJSON(response.body() != null ? response.body().string() : null));
    } catch (IOException e) {
      throw new AlphaVantageException(e.getMessage());
    }
  }

  @Override
  public void fetch() {

    Config.checkNotNullOrKeyEmpty(config);

    config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey()))
        .enqueue(new Callback() {
          @Override
          public void onFailure(@NotNull Call call, @NotNull IOException e) {
            if (failureCallback != null) {
              failureCallback.onFailure(new AlphaVantageException());
            }
          }

          @Override
          public void onResponse(@NotNull Call call, @NotNull Response response)
              throws IOException {
            if (response.isSuccessful()) {
              try (ResponseBody body = response.body()) {
                NewsResponse newsResponse =
                    NewsResponse.of(Parser.parseJSON(body != null ? body.string() : null));
                if (newsResponse.getErrorMessage() != null && failureCallback != null) {
                  failureCallback.onFailure(
                      new AlphaVantageException(newsResponse.getErrorMessage()));
                }
                if (successCallback != null) {
                  successCallback.onSuccess(newsResponse);
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