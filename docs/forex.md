## Defaults

```txt
interval: Interval.ONE_MIN
ouputSize: OutputSize.COMPACT
dataType: DataType.JSON
```

## Intraday

=== "Java"
        :::java
        Alphavantage.api()
            .forex()
            .intraday()
            .forSymbol("AAPL")
            .interval(Interval.THIRTY_MIN)
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getForexUnits()))
            .fetch();

## Daily

=== "Java"
        :::java
        Alphavantage.api()
            .forex()
            .daily()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getForexUnits()))
            .fetch();

## Weekly

=== "Java"
        :::java
        Alphavantage.api()
            .forex()
            .weekly()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getForexUnits()))
            .fetch();

## Monthly

=== "Java"
        :::java
        Alphavantage.api()
            .forex()
            .monthly()
            .forSymbol("AAPL")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e->onData(e.getForexUnits()))
            .fetch();

## Response Type

`ForexResponse`
