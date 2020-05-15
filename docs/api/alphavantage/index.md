# Types
|Name|Summary|
|----|-------|
|[Alphavantage]|Client interface of library. The API is accessed through this class. Exposes a singleton instance for interaction. `class Alphavantage`|
|[AlphavantageException]|Exception class for the library. `class AlphavantageException`|
|[Config]|Allows you to set the library configuration parameters. `class AlphavantageException`|
|[Fetcher]|Defines an interface for pulling data from the API source. A fetch operation can either fail or succeed. `interface Fetcher`|
|[UrlExtractor]|Extracts a valid url from a request object. The request object should contain valid api endpoint parameters. `class UrlExtractor`|


[Alphavantage]: alphavantage.md
[AlphavantageException]: exception.md
[Config]: config.md
[Fetcher]: fetcher.md
[UrlExtractor]: extractor.md