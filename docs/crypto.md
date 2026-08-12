## Digital Currencies

### Defaults

```txt
dataType: DataType.JSON
```

### Intraday

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .crypto()
        .intraday()
        .forSymbol("BTC")
        .market("USD")
        .onSuccess(e -> onData(e.getCryptoUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .crypto()
        .intraday()
        .forSymbol("BTC")
        .market("USD")
        .onSuccess { e -> onData(e.cryptoUnits) }
        .fetch()
    ```

**Response Type:**
`CryptoResponse`

### Daily

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .crypto()
        .daily()
        .forSymbol("BTC")
        .market("CNY")
        .onSuccess(e -> onData(e.getCryptoUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .crypto()
        .daily()
        .forSymbol("BTC")
        .market("CNY")
        .onSuccess { e -> onData(e.cryptoUnits) }
        .fetch()
    ```

### Weekly

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .crypto()
        .weekly()
        .forSymbol("BTC")
        .market("USD")
        .onSuccess(e -> onData(e.getCryptoUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .crypto()
        .weekly()
        .forSymbol("BTC")
        .market("USD")
        .onSuccess { e -> onData(e.cryptoUnits) }
        .fetch()
    ```

### Monthly

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .crypto()
        .monthly()
        .forSymbol("BTC")
        .market("USD")
        .onSuccess(e -> onData(e.getCryptoUnits()))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .crypto()
        .monthly()
        .forSymbol("BTC")
        .market("USD")
        .onSuccess { e -> onData(e.cryptoUnits) }
        .fetch()
    ```

**Response Type:**
`CryptoResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(List<CryptoUnit> cryptoUnits){
        cryptoUnits.stream().forEach(u -> {
            System.out.println(u.getHigh());
            System.out.println(u.getLow());
            System.out.println(u.getOpen());
            System.out.println(u.getClose());
            System.out.println(u.getVolume());
        });
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(cryptoUnits: List<CryptoUnit>) {
        cryptoUnits.forEach { u ->
            println(u.high)
            println(u.low)
            println(u.open)
            println(u.close)
            println(u.volume)
        }
    }
    ```

## Health Index

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .crypto()
        .rating()
        .forSymbol("BTC")
        .onSuccess(e -> onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .crypto()
        .rating()
        .forSymbol("BTC")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`RatingResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(RatingResponse response){
        System.out.println(response.getSymbol());
        System.out.println(response.getName());
        System.out.println(response.getFcasRating());
        System.out.println(response.getFcasScore());
        System.out.println(response.getDeveloperScore());
        System.out.println(response.getMarketMaturityScore());
        System.out.println(response.getUtilityScore());
        System.out.println(response.getLastRefreshed());
        System.out.println(response.getTimeZone());
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(response: RatingResponse) {
        println(response.symbol)
        println(response.name)
        println(response.fcasRating)
        println(response.fcasScore)
        println(response.developerScore)
        println(response.marketMaturityScore)
        println(response.utilityScore)
        println(response.lastRefreshed)
        println(response.timeZone)
    }
    ```