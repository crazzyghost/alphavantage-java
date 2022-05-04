# alphavantage-java

[alphavantage](#)

## Package alphavantage

### Types

|Name|Summary|
|----|-------|
|[Alphavantage]|Client interface of library. The API is accessed through this class. Exposes a singleton instance for interaction. `class Alphavantage`|
|[AlphavantageException]|Exception class for the library. `class AlphavantageException`|
|[Config]|Allows you to set the library configuration parameters. `class AlphavantageException`|
|[Fetcher]|Defines an interface for pulling data from the API source. A fetch operation can either fail or succeed. `interface Fetcher`|
|[UrlExtractor]|Extracts a valid url from a request object. The request object should contain valid api endpoint parameters. `class UrlExtractor`|

### Packages

|Name|Summary|
|----|-------|
|[timeseries]|`package alphavantage.timeseries`|
|[crypto]|`package alphavantage.crypto`|
|[forex]|`package alphavantage.forex`|
|[indicator]|`package alphavantage.indicator`|
|[exchangerate]|`package alphavantage.exchangerate`|
|[sector]|`package alphavantage.sector`|
|[fundamentaldata]|`package alphavantage.fundamentaldata`|

[TimeSeries]: index.md

[Alphavantage]: alphavantage.md
[AlphavantageException]: exception.md
[Config]: config.md
[Fetcher]: fetcher.md
[UrlExtractor]: extractor.md
[timeseries]: ../timeseries/index.md
[crypto]: ../crypto/index.md
[indicator]: ../indicator/index.md
[forex]: ../forex/index.md
[exchangerate]: ../exchangerate/index.md
[sector]: ../sector/index.md
[fundamentaldata]: ../fundamentaldata/index.md
