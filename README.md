[![CircleCI](https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master.svg?style=shield)](https://circleci.com/gh/crazzyghost/alphavantage-java/tree/master)

I created this wrapper to make accessing the [AlphaVantage API](https://www.alphavantage.co/) with Java fairly simple and fun. The library currently supports Timeseries, Currency Exchange, Forex and Crypto Currency data. Support for Technical Indicators will be released soon. Make sure to get an [API key](https://www.alphavantage.co/support/#api-key) from Alphavantage's website befor continuing. 

To use the library:
1. `config`ure the wrapper
2. Select a `category`
3. Set the `parameters` for the selected category
4. Add `response callbacks`
5. `fetch` results

## 1. `Config`ure the wrapper
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

## 2. Select a `category`
The available API categories to select from are Stock Time Series, Forex, Crypto Currency, and Exchange rate data. Each of these map to a method call in the instantiated wrapper.

| Category          | Method            | 
| -------------     |:------------------| 
| Stock Time Series | `.timeSeries()`   | 
| Forex Rates       | `.forex()`        | 
| Exchange Rates    | `.exchangeRate()` | 
| Crypto Currencies | `.crypto()`       | 

Eg.
```java
//select stock time series category
AlphaVantage.api()
            .timeSeries() 
            ...
```

## 3. Set the `parameters` for the selected category

To set the api request parameters, call the appopriate parameter method. For instance for the `function` parameter
function you call `daily()` for the `TIMESERIES_DAILY` function, `intraday()` for the `TIMESERIES_INTRADAY` function and so on.

```java
//set stock time series function to intraday
AlphaVantage.api()
            .timeSeries()
            .intraday()
            ...
```

## 4. Add `response callbacks`

To handle responses add the `onSuccess()` or `onFailure()` callbacks.  Each category has its response. For instance the `TimeSeries` category has the `TimeSeriesResponse`.

```java
public void handleResponse(TimeSeriesResponse response){/*do something with response*/ }
public void handleError(AlphaVantageException error){/*handle error*/ }

AlphaVantage.api()
            .timeSeries()
            ...
            .onSuccess(e->handleResponse(e))
            .onFailure(e->hanldeError(e))
            ...
```

## 5.  `fetch` results
When you are okay with setting the parameters call the `fetch()` method. Simple

## Examples
```java
//fetch Intraday Stock Time Series for microsoft
AlphaVantage.api()
            .timeSeries()
            .intraday()
            .onSuccess(e->handleSuccess(e.getStockUnits()))
            .interval(Interval.ONE_MIN)
            .onFailure(e->handleFailure(e))
            .forSymbol("MSFT")
            .fetch();

//fetch Forex data for Ghanaian Cedi and US Dollar 
AlphaVantage.api()
            .forex()
            .daily()
            .fromSymbol("USD")
            .toSymbol("GHS")
            .onSuccess(e->handleSuccess(e.getForexUnits()))
            .forSymbol("MSFT")
            .fetch();

//fetch exchange rate from US Dollar to Ghanaian Cedi
AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("USD")
            .toCurrency("GHS")
            .onSuccess(response -> handleSucess(response))
            .fetch();

//fetch digital currency from US Dollar to Ghanaian Cedi
AlphaVantage.api()
            .crypto()
            .daily()
            .symbol("BTC")
            .market("CNY")
            .onSuccess(response -> handleSucess(response))
            .fetch();

```

Happy coding!