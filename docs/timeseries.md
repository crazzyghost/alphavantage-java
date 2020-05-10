## Time Series

### Defaults

```txt
interval: Interval.ONE_MIN
ouputSize: OutputSize.COMPACT
dataType: DataType.JSON
```

### Intraday

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("AAPL")
            .interval(Interval.THIRTY_MIN)
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Daily

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .daily()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Daily Adjusted

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .daily()
            .adjusted()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Weekly

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .weekly()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Weekly Adjusted

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .weekly()
            .adjusted()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Monthly

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .monthly()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Monthly Adjusted

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .monthly()
            .adjusted()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getStockUnits()))
            .fetch();

### Response Type

`TimeSeriesResponse`

## Quote Endpoint

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .quote()
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

### Response Type

`QuoteResponse`
