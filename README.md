[![CircleCI](https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master.svg?style=shield)](https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master)
[![codecov](https://codecov.io/gh/crazzyghost/alphavantage-java/branch/master/graph/badge.svg)](https://codecov.io/gh/crazzyghost/alphavantage-java)
[![](https://jitpack.io/v/crazzyghost/alphavantage-java.svg)](https://jitpack.io/#crazzyghost/alphavantage-java)


I created this wrapper to make accessing the [AlphaVantage API](https://www.alphavantage.co/) with Java fairly simple and fun. Make sure to get an [API key](https://www.alphavantage.co/support/#api-key) from Alphavantage's website before continuing. 

### Installation (Gradle)
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    ...
    implementation 'com.github.crazzyghost:alphavantage-java:<latest-version>'
}
```

### Usage
1. `config`ure the wrapper
2. Select a `category`
3. Set the `parameters` for the selected category
4. Add `response callbacks`
5. `fetch` results

#### 1. `Config`ure the wrapper
Access to the API is through the AlphaVantage Singleton which is accessed using the `static` `.api()` method call on the class. To begin accessing the API, initialize the singleton with a `Config` instance.

```java
Config cfg = Config.builder()
    .key("#&ALPHA10100DEMOKEY")
    .timeout(10)
    .build();
```
The config object is then used to initialize the instance. You will use this object to set your api key as well as set the timeout of the http requests.

```java
AlphaVantage.api().init(cfg);
```
That's it! We're good to go.

#### 2. Select a `category`
The available API categories to select from currently are: Stock Time Series, Forex, Crypto Currencies, Exchange Rates and Technical Indicators. Each of these map to a method call in the instantiated wrapper.

| Category                  |   Method              | 
| -------------             | ------------------    | 
| Stock Time Series Data    | `.timeSeries()`       | 
| Forex Rate Data           | `.forex()`            | 
| Exchange Rate Data        | `.exchangeRate()`     | 
| Crypto Currency Data      | `.crypto()`           | 
| Technical Indicators      | `.indicator()`        |

For example
```java
//selecting stock time series category
AlphaVantage.api()
    .timeSeries() 
    ...
```

#### 3. Set the `parameters` for the selected category

To set the api request parameters, call the appopriate parameter method. For instance for the `function` parameter
function you call `daily()` for the `TIMESERIES_DAILY` function, `intraday()` for the `TIMESERIES_INTRADAY`, `sma()` for `SMA` and so on.

```java
//set stock time series function to intraday
AlphaVantage.api()
    .timeSeries()
    .intraday()
    ...
```

#### 4. Add `response callbacks`

To handle responses add the `onSuccess()` or `onFailure()` callbacks.  Each category has its response. For instance the `TimeSeries` category has the `TimeSeriesResponse`.

```java
public void handleResponse(TimeSeriesResponse response){/*perform some magic with response*/ }
public void handleError(AlphaVantageException error){/*uh-oh*/ }

AlphaVantage.api()
    .timeSeries()
    ...
    .onSuccess(e->handleResponse(e))
    .onFailure(e->hanldeError(e))
    ...
```

#### 5.  `fetch` results
When you are okay with setting the parameters call the `fetch()` method. Simple!

### Examples
#### Fetching Stock Time Series Data
```java
...
AlphaVantage.api()
    .timeSeries()
    .intraday()
    .onSuccess(e->handleSuccess(e))
    .interval(Interval.ONE_MIN)
    .onFailure(e->handleFailure(e))
    .forSymbol("MSFT")
    .fetch();
...
void handleSuccess(TimeSeriesResponse e){
    plotGraph(e.getStockUnits(), e.getMetaData());
}

```

#### Fetching Forex Rate Data
```java
AlphaVantage.api()
    .forex()
    .daily()
    .fromSymbol("USD")
    .toSymbol("GHS")
    .onSuccess(e->handleSuccess(e.getForexUnits()))
    .forSymbol("MSFT")
    .fetch();
```

#### Fetching Currency Exchange Rate Data
```java
AlphaVantage.api()
    .exchangeRate()
    .fromCurrency("USD")
    .toCurrency("GHS")
    .onSuccess((ExchangeRateResponse e) -> handleSuccess(e))
    .fetch();
```

#### Fetching Crypto Currency Data
```java
AlphaVantage.api()
    .crypto()
    .daily()
    .forSymbol("BTC")
    .market("CNY")
    .onSuccess(CryptoResponse response -> handleSuccess(response.getCryptoUnits()))
    .fetch();
...
//Crypto Rating/ Health Index
AlphaVantage.api()
    .crypto()
    .rating()
    .forSymbol("LTC")
    .onSuccess(RatingResponse response -> handleSuccess(response))
    .fetch();

```
#### Fetching Technical Indicator Data
```java
AlphaVantage.api()
    .indicator()
    .sma()
    .forSymbol("IBM")
    .interval(Interval.THIRTY_MIN)
    .seriesType(SeriesType.HIGH)
    .timePeriod(200)
    .onSuccess(e -> handleSuccess(e))
    .fetch();
```
