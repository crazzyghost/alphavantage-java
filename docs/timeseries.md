## Time Series

### Defaults

```txt
interval: Interval.ONE_MIN
ouputSize: OutputSize.COMPACT
dataType: DataType.JSON
```

### Intraday

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .intraday()
        .forSymbol("AAPL")
        .interval(Interval.THIRTY_MIN)
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .adjusted()
        .extendedHours()
        .month("2024-01")
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .intraday()
        .forSymbol("AAPL")
        .interval(Interval.THIRTY_MIN)
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .adjusted()
        .extendedHours()
        .month("2024-01")
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Daily

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .daily()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .daily()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Daily Adjusted

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .daily()
        .adjusted()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .daily()
        .adjusted()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Weekly

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .weekly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .weekly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Weekly Adjusted

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .weekly()
        .adjusted()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .weekly()
        .adjusted()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Monthly

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .monthly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .monthly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Monthly Adjusted

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .monthly()
        .adjusted()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getStockUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .monthly()
        .adjusted()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.stockUnits) }
        .fetch()
    ```

**Response Type:**
`TimeSeriesResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(List<StockUnit> stockUnits){
        stockUnits.stream().forEach(u -> {
            System.out.println(u.getHigh());
            System.out.println(u.getLow());
            System.out.println(u.getOpen());
            System.out.println(u.getClose());
            System.out.println(u.getVolume());
            System.out.println(u.getAdjustedClose());
            System.out.println(u.getDividendAmount());
            System.out.println(u.getSplitCoefficient());
            System.out.println(u.getDate());
        });
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(stockUnits: List<StockUnit>) {
        stockUnits.forEach { u ->
            println(u.high)
            println(u.low)
            println(u.open)
            println(u.close)
            println(u.volume)
            println(u.adjustedClose)
            println(u.dividendAmount)
            println(u.splitCoefficient)
            println(u.date)
        }
    }
    ```

## Quote Endpoint

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .quote()
        .forSymbol("AAPL")
        .onSuccess(e -> onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .quote()
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`QuoteResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(QuoteResponse response){
        System.out.println(response.getHigh());
        System.out.println(response.getLow());
        System.out.println(response.getOpen());
        System.out.println(response.getPrice());
        System.out.println(response.getVolume());
        System.out.println(response.getSymbol());
        System.out.println(response.getLatestTradingDay());
        System.out.println(response.getPreviousClose());
        System.out.println(response.getChange());
        System.out.println(response.getChangePercent());
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(response: QuoteResponse) {
        println(response.high)
        println(response.low)
        println(response.open)
        println(response.price)
        println(response.volume)
        println(response.symbol)
        println(response.latestTradingDay)
        println(response.previousClose)
        println(response.change)
        println(response.changePercent)
    }
    ```

## Realtime Bulk Quote

!!! note
    `forSymbol()` accumulates into a single comma-joined request parameter each time it's called, rather than replacing the previous symbol. Call it once per symbol to fetch quotes in bulk.

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .realtimeBulkQuote()
        .forSymbol("AAPL")
        .forSymbol("MSFT")
        .onSuccess(e -> onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .realtimeBulkQuote()
        .forSymbol("AAPL")
        .forSymbol("MSFT")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`RealtimeBulkQuoteResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(RealtimeBulkQuoteResponse response){
        response.getData().stream().forEach(u -> {
            System.out.println(u.getSymbol());
            System.out.println(u.getTimestamp());
            System.out.println(u.getOpen());
            System.out.println(u.getHigh());
            System.out.println(u.getLow());
            System.out.println(u.getClose());
            System.out.println(u.getVolume());
            System.out.println(u.getPreviousClose());
            System.out.println(u.getChange());
            System.out.println(u.getChangePercent());
            System.out.println(u.getExtendedHoursQuote());
            System.out.println(u.getExtendedHoursChange());
            System.out.println(u.getExtendedHoursChangePercent());
        });
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(response: RealtimeBulkQuoteResponse) {
        response.data.forEach { u ->
            println(u.symbol)
            println(u.timestamp)
            println(u.open)
            println(u.high)
            println(u.low)
            println(u.close)
            println(u.volume)
            println(u.previousClose)
            println(u.change)
            println(u.changePercent)
            println(u.extendedHoursQuote)
            println(u.extendedHoursChange)
            println(u.extendedHoursChangePercent)
        }
    }
    ```
