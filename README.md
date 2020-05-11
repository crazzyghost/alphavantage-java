[![CircleCI](https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master.svg?style=shield)](https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master)
[![codecov](https://codecov.io/gh/crazzyghost/alphavantage-java/branch/master/graph/badge.svg)](https://codecov.io/gh/crazzyghost/alphavantage-java)
[![](https://jitpack.io/v/crazzyghost/alphavantage-java.svg)](https://jitpack.io/#crazzyghost/alphavantage-java)

I created this wrapper to make accessing the [AlphaVantage API](https://www.alphavantage.co/) fairly simple and fun by providing a fluent interface with Java.

## Getting Started

To get started using this library, make sure to get an [API Key](https://www.alphavantage.co/support/#api-key) from Alphavantage's website. Add the library as a dependency to your java/android project

### Gradle Installation

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    ...
    implementation 'com.github.crazzyghost:alphavantage-java:x.y.z'
}
```

### Maven Installation

```xml
<repositories>
    ...
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    ...
</repositories>
<dependencies>
    ...
    <dependency>
        <groupId>com.github.crazzyghost</groupId>
        <artifactId>alphavantage-java</artifactId>
        <version>x.y.z</version>
    </dependency>
    ...
</dependencies>
```

### Quick Usage Guide

These five steps summarize how to access data using this library

1. `config`ure the wrapper
2. Select a `category`
3. Set the `parameters` for the selected category
4. Add `response callbacks`
5. `fetch` results

#### 1. `Config`uring the wrapper

Access to the API is through the AlphaVantage Singleton which is accessed using the `static` `api()` method of the class. Initialize the singleton with a `Config` instance once through out your apps lifetime.

```java
Config cfg = Config.builder()
    .key("#&ALPHA10100DEMOKEY")
    .timeOut(10)
    .build();
```

The config object is then used to initialize the instance. You will use this object to set your api key and configure the http client. Using the wrapper without setting a config or a config key will throw an exception.

```java
AlphaVantage.api().init(cfg);
```

We're good to go.

#### 2. Selecting a `category`

The available API categories to select from currently are: Stock Time Series, Foreign Exchange(FX), Digital Currencies, Exchange Rates and Technical Indicators. Each of these categories is exposed through a method call in the instantiated wrapper.

| Category                  |   Method              |
| -------------             | ------------------    |
| Stock Time Series Data    | `.timeSeries()`       |
| Forex Rate Data           | `.forex()`            |
| Exchange Rate Data        | `.exchangeRate()`     |
| Digital Currency Data     | `.crypto()`           |
| Technical Indicator Data  | `.indicator()`        |

For example, to select the Stock Time Series:

```java
AlphaVantage.api()
    .timeSeries()
```

#### 3. Setting the `parameters` for the selected category

To set the api request parameters, call the appopriate parameter methods. For instance for the `function` parameter function you call `daily()` for the `TIME_SERIES_DAILY` function, `intraday()` for the `TIME_SERIES_INTRADAY`,  and so on.

Let's select the `TIME_SERIES_INTRADAY` function

```java
AlphaVantage.api()
    .timeSeries()
    .intraday()
...
```

Start setting parameters by calling an appropriate function method in the selected category

#### 4. Adding `response callbacks`

To handle responses add the `onSuccess()` or `onFailure()` callbacks.

```java
public void handleSuccess(TimeSeriesResponse response) {
    plotGraph(reponse.getStockUnits());
}
public void handleFailure(AlphaVantageException error) {
    /* uh-oh! */
}

AlphaVantage.api()
    .timeSeries()
    .intraday()
    .forSymbol("IBM")
    .interval(Interval.FIVE_MIN)
    .outputSize(OutputSize.FULL)
    .onSuccess(e->handleSuccess(e))
    .onFailure(e->hanldeFailure(e))
    ...
```

#### 5.  `fetch` results

When you are okay with setting the parameters call the `fetch()` method. Simple!

```java
AlphaVantage.api()
    .timeSeries()
    .intraday()
    .forSymbol("IBM")
    .interval(Interval.FIVE_MIN)
    .outputSize(OutputSize.FULL)
    .onSuccess(e->handleSuccess(e))
    .onFailure(e->hanldeFailure(e))
    .fetch();
```

That's it! :tada: See [site](https://crazzyghost.github.io/alphavantage-java/) for more examples & documentation
