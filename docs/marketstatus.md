## Market Status

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .marketStatus()
        .onSuccess(response -> onData(response))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .marketStatus()
        .onSuccess { response -> onData(response) }
        .fetch()
    ```

**Response Type:**
`MarketStatusResponse`

### Response

=== ":material-language-java: Java"
    ```java
    public void onData(MarketStatusResponse response){
        response.getMarkets().forEach(market -> {
            System.out.println(market.getMarketType());
            System.out.println(market.getRegion());
            System.out.println(market.getPrimaryExchanges());
            System.out.println(market.getLocalOpen());
            System.out.println(market.getLocalClose());
            System.out.println(market.getCurrentStatus());
            System.out.println(market.getNotes());
        });
    }
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun onData(response: MarketStatusResponse) {
        response.markets.forEach { market ->
            println(market.marketType)
            println(market.region)
            println(market.primaryExchanges)
            println(market.localOpen)
            println(market.localClose)
            println(market.currentStatus)
            println(market.notes)
        }
    }
    ```
