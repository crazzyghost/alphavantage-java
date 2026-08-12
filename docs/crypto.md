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