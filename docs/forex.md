## Defaults

```txt
interval: Interval.ONE_MIN
ouputSize: OutputSize.COMPACT
dataType: DataType.JSON
```

## Intraday

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .forex()
        .intraday()
        .forSymbol("AAPL")
        .interval(Interval.THIRTY_MIN)
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getForexUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .forex()
        .intraday()
        .forSymbol("AAPL")
        .interval(Interval.THIRTY_MIN)
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.forexUnits) }
        .fetch()
    ```

**Response Type:**
`ForexResponse`

## Daily

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .forex()
        .daily()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getForexUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .forex()
        .daily()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.forexUnits) }
        .fetch()
    ```

**Response Type:**
`ForexResponse`

## Weekly

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .forex()
        .weekly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getForexUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .forex()
        .weekly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.forexUnits) }
        .fetch()
    ```

**Response Type:**
`ForexResponse`

## Monthly

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .forex()
        .monthly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess(e -> onData(e.getForexUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .forex()
        .monthly()
        .forSymbol("AAPL")
        .outputSize(OutputSize.FULL)
        .dataType(DataType.JSON)
        .onSuccess { e -> onData(e.forexUnits) }
        .fetch()
    ```

**Response Type:**
`ForexResponse`

## Response

=== ":material-language-java: Java"
    ```java
    public void onData(List<ForexUnit> forexUnits){
        forexUnits.stream().forEach(u -> {
            System.out.println(u.getHigh());
            System.out.println(u.getLow());
            System.out.println(u.getOpen());
            System.out.println(u.getClose());
            System.out.println(u.getDate());
        });
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(forexUnits: List<ForexUnit>) {
        forexUnits.forEach { u ->
            println(u.high)
            println(u.low)
            println(u.open)
            println(u.close)
            println(u.date)
        }
    }
    ```
