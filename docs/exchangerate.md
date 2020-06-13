## Request

=== "Java"
        :::java
        AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("USD")
            .toCurrency("GHS")
            .onSuccess((e) -> onData(e))
            .fetch();

**Response Type:**
`ExchangeRateResponse`

### Response

=== "Java"
        :::java
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