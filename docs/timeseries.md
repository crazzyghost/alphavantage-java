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

**Response Type:**
`TimeSeriesResponse`

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

**Response Type:**
`TimeSeriesResponse`

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

**Response Type:**
`TimeSeriesResponse`

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

**Response Type:**
`TimeSeriesResponse`

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

**Response Type:**
`TimeSeriesResponse`

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

**Response Type:**
`TimeSeriesResponse`

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

**Response Type:**
`TimeSeriesResponse`

### Response

=== "Java"
        :::java
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
           })
        }

## Quote Endpoint

=== "Java"
        :::java
        Alphavantage.api()
            .timeSeries()
            .quote()
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`QuoteResponse`

### Response

=== "Java"
        :::java
        public void onData(QuoteResponse reponse){
            System.out.println(response.getHigh());
            System.out.println(response.getLow());
            System.out.println(response.getOpen());
            System.out.println(response.getClose());
            System.out.println(response.getVolume());
            System.out.println(response.getSymbol());
            System.out.println(response.getLatestTradingDay());
            System.out.println(response.getPreviousClose());
            System.out.println(response.getChange());
            System.out.println(response.getChangePercent());
        }
