## Request

=== "Java"
        :::java
        AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("USD")
            .toCurrency("GHS")
            .onSuccess((e) -> onData(e))
            .fetch();

## Response Type

`ExchangeRateResponse`
