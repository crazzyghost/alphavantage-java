package com.crazzyghost.alphavantage.news.response;

import com.crazzyghost.alphavantage.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsResponse {
  private final String errorMessage;
  private final List<NewsItem> newsItems;

  private NewsResponse(List<NewsItem> newsItems, String error) {
    this.errorMessage = error;
    this.newsItems = newsItems != null ? newsItems : new ArrayList<>();
  }

  public static NewsResponse of(Map<String, Object> stringObjectMap) {
    Parser<NewsResponse> parser = new NewsParser();
    return parser.parse(stringObjectMap);
  }

  public List<NewsItem> getNewsItems() {
    return newsItems;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  @Override
  public String toString() {
    return newsItems.toString();
  }

  public static class NewsItem {
    private final String title;
    private final String url;
    private final String timePublished;
    private final List<String> authors;
    private final String summary;
    private final String source;
    private final double overallSentimentScore;
    private final String overallSentimentLabel;
    private final List<TickerSentiment> tickerSentiment;

    public NewsItem(String title, String url, String timePublished, List<String> authors,
                    String summary,
                    String source, double overallSentimentScore, String overallSentimentLabel,
                    List<TickerSentiment> tickerSentiment) {
      this.title = title;
      this.url = url;
      this.timePublished = timePublished;
      this.authors = authors != null ? authors : new ArrayList<>();
      this.summary = summary;
      this.source = source;
      this.overallSentimentScore = overallSentimentScore;
      this.overallSentimentLabel = overallSentimentLabel;
      this.tickerSentiment = tickerSentiment != null ? tickerSentiment : new ArrayList<>();
    }

    @Override
    public String toString() {
      return title + " - " + summary + " (" + overallSentimentLabel + ")";
    }

    public String getTitle() {
      return title;
    }

    public String getSummary() {
      return summary;
    }

    public String getUrl() {
      return url;
    }
  }

  public static class TickerSentiment {
    private final String ticker;
    private final double relevanceScore;
    private final double sentimentScore;
    private final String sentimentLabel;

    public TickerSentiment(String ticker, double relevanceScore, double sentimentScore,
                           String sentimentLabel) {
      this.ticker = ticker;
      this.relevanceScore = relevanceScore;
      this.sentimentScore = sentimentScore;
      this.sentimentLabel = sentimentLabel;
    }

    @Override
    public String toString() {
      return ticker + ": " + sentimentLabel + " (" + sentimentScore + ")";
    }
  }

  public static class NewsParser extends Parser<NewsResponse> {

    @SuppressWarnings("unchecked")
    @Override
    public NewsResponse parse(Map<String, Object> stringObjectMap) {
      List<NewsItem> newsItems = new ArrayList<>();
      String errorMessage = null;

      // Check if the response is empty
      if (stringObjectMap.isEmpty()) {
        return onParseError("Empty JSON returned by the API, no news items found.");
      }

      try {
        List<Map<String, Object>> feed = (List<Map<String, Object>>) stringObjectMap.get("feed");
        if (feed != null) {
          for (Map<String, Object> item : feed) {
            // Parse the ticker sentiment
            List<TickerSentiment> tickerSentiments = new ArrayList<>();
            List<Map<String, Object>> tickerSentimentList =
                (List<Map<String, Object>>) item.get("ticker_sentiment");
            if (tickerSentimentList != null) {
              for (Map<String, Object> tickerSentimentMap : tickerSentimentList) {
                tickerSentiments.add(new TickerSentiment(
                    (String) tickerSentimentMap.get("ticker"),
                    Double.parseDouble(tickerSentimentMap.get("relevance_score").toString()),
                    Double.parseDouble(tickerSentimentMap.get("ticker_sentiment_score").toString()),
                    (String) tickerSentimentMap.get("ticker_sentiment_label")
                ));
              }
            }

            // Create a NewsItem and add it to the list
            newsItems.add(new NewsItem(
                (String) item.get("title"),
                (String) item.get("url"),
                (String) item.get("time_published"),
                (List<String>) item.get("authors"),
                (String) item.get("summary"),
                (String) item.get("source"),
                Double.parseDouble(item.get("overall_sentiment_score").toString()),
                (String) item.get("overall_sentiment_label"),
                tickerSentiments
            ));
          }
        }
      } catch (ClassCastException | NullPointerException e) {
        errorMessage = "Error parsing news sentiment results.";
      }

      return new NewsResponse(newsItems, errorMessage);
    }

    @Override
    public NewsResponse onParseError(String error) {
      return new NewsResponse(null, error);
    }
  }
}