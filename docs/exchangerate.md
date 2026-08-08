## Request

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .exchangeRate()
        .fromCurrency("USD")
        .toCurrency("GHS")
        .onSuccess(e -> onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .exchangeRate()
        .fromCurrency("USD")
        .toCurrency("GHS")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ExchangeRateResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(ExchangeRateResponse response){
        System.out.println(response.getExchangeRate());
        System.out.println(response.getBidPrice());
        System.out.println(response.getAskPrice());
        System.out.println(response.getFromCurrencyCode());
        System.out.println(response.getFromCurrencyName());
        System.out.println(response.getToCurrencyCode());
        System.out.println(response.getToCurrencyName());
        System.out.println(response.getLastRefreshed());
        System.out.println(response.getTimeZone());
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(response: ExchangeRateResponse) {
        println(response.exchangeRate)
        println(response.bidPrice)
        println(response.askPrice)
        println(response.fromCurrencyCode)
        println(response.fromCurrencyName)
        println(response.toCurrencyCode)
        println(response.toCurrencyName)
        println(response.lastRefreshed)
        println(response.timeZone)
    }
    ```
