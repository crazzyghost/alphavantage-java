## Digital Currencies

### Defaults

```txt
dataType: DataType.JSON
```

### Daily

=== "Java"
        :::java
        Alphavantage.api()
            .crypto()
            .daily()
            .forSymbol("BTC")
            .market("CNY")
            .onSuccess(e->onData(e.getCryptoUnits()))
            .fetch();

### Weekly

=== "Java"
        :::java
        Alphavantage.api()
            .crypto()
            .weekly()
            .forSymbol("BTC")
            .market("USD")
            .onSuccess(e->onData(e.getCryptoUnits()))
            .fetch();

### Monthly

=== "Java"
        :::java
        Alphavantage.api()
            .crypto()
            .monthly()
            .forSymbol("BTC")
            .market("USD")
            .onSuccess(e->onData(e.getCryptoUnits()))
            .fetch();

**Response Type:**
`CryptoResponse`

### Response

=== "Java"
        :::java
        public void onData(List<CryptoUnit> cryptoUnits){
            cryptoUnits.stream().forEach(u -> {
                System.out.println(u.getHigh());
                System.out.println(u.getLow());
                System.out.println(u.getOpen());
                System.out.println(u.getClose());
                System.out.println(u.getHighUSD());
                System.out.println(u.getLowUSD());
                System.out.println(u.getOpenUSD());
                System.out.println(u.getCloseUSD());
                System.out.println(u.getVolume());
                System.out.println(u.getMarketCap());
                System.out.println(u.getDate());
            })
        }


## Health Index

=== "Java"
        :::java
        Alphavantage.api()
            .crypto()
            .rating()
            .forSymbol("BTC")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`RatingResponse`

### Response

=== "Java"
        :::java
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