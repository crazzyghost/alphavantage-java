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

### Response Type

`CryptoResponse`

## Health Index

=== "Java"
        :::java
        Alphavantage.api()
            .crypto()
            .rating()
            .forSymbol("BTC")
            .onSuccess(e->onData(e))
            .fetch();

### Response Type

`RatingResponse`
