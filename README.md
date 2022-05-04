<p align="center">
    <a href="https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master"><img src="https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master.svg?style=shield"/></a>
    <a href="https://codecov.io/gh/crazzyghost/alphavantage-java"> <img src="https://codecov.io/gh/crazzyghost/alphavantage-java/branch/master/graph/badge.svg" /></a>
    <a href="https://jitpack.io/#crazzyghost/alphavantage-java"> <img src="https://jitpack.io/v/crazzyghost/alphavantage-java.svg" /></a>
    <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-green.svg"/></a>
    <a href="https://jitpack.io/v/crazzyghost/alphavantage-java/month"> <img src="https://jitpack.io/v/crazzyghost/alphavantage-java/month" /></a>
    <a href="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=alert_status"> <img src="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=alert_status" /></a>
    <a href="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=security_rating"> <img src="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=security_rating" /></a>
    <a href="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=reliability_rating"> <img src="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=reliability_rating" /></a>
    <a href="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=sqale_rating"> <img src="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=sqale_rating" /></a>
    <a href="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=bugs"> <img src="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=bugs" /></a>
    <a href="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=vulnerabilities"> <img src="https://sonarcloud.io/api/project_badges/measure?project=crazzyghost_alphavantage-java&metric=vulnerabilities" /></a>
</p>
</p>

An easy to use, fluent Java wrapper for accessing the [AlphaVantage API](https://www.alphavantage.co/).

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

## Quick Usage Guide

These five steps summarize how to access data using this library

Step 1. `config`ure the wrapper

Step 2. Select a `category`

Step 3. Set the `parameters` for the selected category

Step 4. (Optional) Add `response callbacks`

Step 5. `fetch` results

#### 1. `Config`uring the wrapper

Access to the API is through the AlphaVantage Singleton which is accessed using the `static` `api()` method of the class. Initialize the singleton with a `Config` instance once throughout your app's lifetime.

```java
Config cfg = Config.builder()
    .key("#&ALPHA10100DEMOKEY")
    .timeOut(10)
    .build();
```

Initialize the instance with the config. You will use this object to set your api key and configure the http client. Using the wrapper without setting a config or a config key will throw an exception.

```java
AlphaVantage.api().init(cfg);
```

We're good to go.

#### 2. Selecting a `category`

Here, we choose which data category/endpoint we want to access

| Category                  |   Method              |
| -------------             | ------------------    |
| Stock Time Series Data    | `.timeSeries()`       |
| Forex Rate Data           | `.forex()`            |
| Exchange Rate Data        | `.exchangeRate()`     |
| Digital Currency Data     | `.crypto()`           |
| Technical Indicator Data  | `.indicator()`        |
| Sector Performance Data   | `.sector()`           |
| Fundamental Data          | `.fundamentalData()`  |

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

To handle responses add the `onSuccess()` or `onFailure()` async callbacks. Starting from version 1.5.0, this is an optional step.

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

If you want a synchronous response, call the `fetchSync()` method.

```java
TimeSeriesResponse response = AlphaVantage.api()
    .timeSeries()
    .intraday()
    .forSymbol("IBM")
    .interval(Interval.FIVE_MIN)
    .outputSize(OutputSize.FULL)
    .fetchSync();
```


That's it! :tada: See [site](https://crazzyghost.github.io/alphavantage-java/) and [demo project](https://github.com/crazzyghost/stockmonitor) for more examples & documentation
