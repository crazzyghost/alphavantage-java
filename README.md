# alphavantage-java
## Fluent java wrapper for the [AlphaVantage API](https://www.alphavantage.co/)
---

To have access to the API, start by initializing  `AlphaVantage` singleton with a `Config` instance. Get an API Key from [here.](https://www.alphavantage.co/support/#api-key)

```java
//create config instance with a timeout of 10s and an API Key
Config config = Config.builder()
                    .timeOut(10)
                    .key("demo")
                    .build();
    
AlphaVantage.api().init(config);
```


The authenticated wrapper can now be accessed by calling `Alphavantage.api()`

Access TimeSeries Data
```java

AlphaVantage.api()
            .timeSeries() 
            ...
```
Access Forex rates
```java
AlphaVantage.api()
            .forex() 
            ...
```
Access Exchange rates 
```java
AlphaVantage.api()
            .exchangeRates() 
            ...
```
Access Crypto Currencies
```java
AlphaVantage.api()
            .crypto()
            ...
```

To handle responses add the `onSuccess()` or `onFailure()` callbacks.  

```java
AlphaVantage.api()
            .timeSeries()
            ...
            .onSuccess(e->handleResponse(e))
            .onFailure(e->hanldeError(e))
            ...
```

Examples
```java
//fetch Stock Time Series (Intraday) for microsoft
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
---
