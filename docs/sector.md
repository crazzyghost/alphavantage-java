## Request

=== "Java"
        :::java
        AlphaVantage.api()
            .sector()
            .onSuccess((e) -> onData(e))
            .fetch();

## Response Type

`SectorResponse`
