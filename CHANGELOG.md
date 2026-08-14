# Changelog
All notable changes to this project will be documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- **Premium entitlement support**: `entitlement(Entitlement.REALTIME)` and `entitlement(Entitlement.DELAYED)` setters on stock time series and technical indicator requests, gating realtime versus fifteen-minute-delayed data for premium Alpha Vantage plans. Applies to `TIME_SERIES_INTRADAY`, `TIME_SERIES_DAILY_ADJUSTED`, `GLOBAL_QUOTE`, and all 53 technical indicators. When unset, the parameter is omitted entirely, preserving full backward compatibility.
- `CompanyOverview`: `officialSite`, the analyst-rating block (`analystRatingStrongBuy`, `analystRatingBuy`, `analystRatingHold`, `analystRatingSell`, `analystRatingStrongSell`), `sharesFloat`, `percentInsiders` and `percentInstitutions`
- `CashFlow.stockBasedCompensation` and `QuarterlyEarning.reportTime`
- `month(String)` parameter on `TechnicalIndicator` requests, for historical intraday windows

### Fixed
- Update `BalanceSheet.getOtherNonCurrentAssets()` to use corrected json field name

### Removed
- `AlphaVantage.sector()` and the `sector` package. `?function=SECTOR` returns `{}` server-side; the endpoint is retired.
- `Crypto.rating()`, `RatingRequest` and `RatingResponse`. `?function=CRYPTO_RATING` returns `{}` server-side; the endpoint is retired.
- `Function.TIME_SERIES_INTRADAY_EXTENDED`. The API reports this function merged into `TIME_SERIES_INTRADAY`; the constant had no request class, proxy or response.
- There is no replacement for any of the three. Code calling `sector()` or `Crypto.rating()` was already receiving an empty response; this converts a silent runtime failure into a build failure. `Function.valueOf("SECTOR")` or `Function.valueOf("CRYPTO_RATING")` by name will now throw at runtime, uncatchable at build time.

## [v1.8.0] - 27-08-2025
### Added
- Support for realtime bulk quotes
- Support for crypto intraday
- Support for global market status
- Support for symbol search

### Changed
- Intraday stock time series: added `extended_hours`, `month` and `adjusted` parameters
- Updated build dependencies
- Updated outdated Crypto fields to match API responses

## [v1.7.0] - 23-01-2024
### Added
- Support for economic indicators
- [Modularise library](https://github.com/crazzyghost/alphavantage-java/pull/26)
### Deprecated
- `indicator` interface in favour of `technicalIndicator` for accessing Technical Indicators


## [v1.6.2] - 29-01-2022
### Fixed
- [Number parsing bug](https://github.com/crazzyghost/alphavantage-java/issues/23)

## [v1.6.1] - 15-11-2021
### Fixed
- [JitPack build failure](https://jitpack.io/com/github/crazzyghost/alphavantage-java/1.6.0/build.log)
- BalanceSheet field types

## [v1.6.0] - 14-11-2021
### Added
- Support for fundamental data


## [v1.5.0] - 11-01-2021
### Added
- Support for synchronous requests

## [v1.4.2] - 20-10-2020
### Fixed
- [Missing crypto unit date](https://github.com/crazzyghost/alphavantage-java/pull/13)


## [v1.4.1] - 25-07-2020
### Fixed
- Array index out of bounds exception caused by [empty API response](https://github.com/crazzyghost/alphavantage-java/issues/9)

## [v1.4.0] - 13-06-2020
### Added
- Support for Sector Performances


## [v1.3.2] - 21-05-2020
### Fixed
- Maven build [issue](https://github.com/crazzyghost/alphavantage-java/issues/6)

## [v1.3.1] - 21-05-2020
### Fixed
- Multiple async requests for enpoint [bug](https://github.com/crazzyghost/alphavantage-java/issues/8)

## [v1.3.0] - 09-05-2020
### Added
- Support for Quote Endpoint
- Tests for TimeSeries
### Changed
- RequestHelper to RequestProxy for TimeSeries
### Fixed
- Time Series IntradayRequest interval bug


## [v1.2.1] - 07-05-2020
### Added
- Tests for Forex
### Changed
- RequestHelper to RequestProxy for Forex
### Fixed
- Forex IntradayRequest interval bug
### Removed
- Static builder methods
## [v1.2.0] - 05-05-2020
### Added
- Support for crypto currency Health Index
## [v1.1.1] - 04-05-2020
### Added
- Bid Rates and Ask Rates for Exchange Rates
### Fixed
- Exchange Rates response data access

## [v1.1.0] - 04-05-2020
### Added
- Support for Technical Indicators

## [v1.0.2] - 24-04-2020
### Fixed
- API url parameter [bug](https://github.com/crazzyghost/alphavantage-java/issues/4)

## [v1.0.1] - 17-04-2020
### Added
- CHANGELOG to keep track of releases
### Fixed
- [Incorrect mapping](https://github.com/crazzyghost/alphavantage-java/issues/1) of time series data
- Getters for Crypto and Forex Units
### Changed
- Rename crypto subpackages

## [v1.0.0] - 23-10-2019 (Unreleased)
### Added
- Support for Time Series Data
- Support for Forex Data
- Support for Crypto Currencies
- Support for Exchange Rates
- Tests for Time Series
- README

[v1.8.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.7.0...1.8.0
[v1.7.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.6.2...1.7.0
[v1.6.2]: https://github.com/crazzyghost/alphavantage-java/compare/1.6.1...1.6.2
[v1.6.1]: https://github.com/crazzyghost/alphavantage-java/compare/1.6.0...1.6.1
[v1.6.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.5.0...1.6.0
[v1.5.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.4.2...1.5.0
[v1.4.2]: https://github.com/crazzyghost/alphavantage-java/compare/1.4.1...1.4.2
[v1.4.1]: https://github.com/crazzyghost/alphavantage-java/compare/1.4.0...1.4.1
[v1.4.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.3.2...1.4.0
[v1.3.2]: https://github.com/crazzyghost/alphavantage-java/compare/1.3.1...1.3.2
[v1.3.1]: https://github.com/crazzyghost/alphavantage-java/compare/1.3.0...1.3.1
[v1.3.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.2.1...1.3.0
[v1.2.1]: https://github.com/crazzyghost/alphavantage-java/compare/1.2.0...1.2.1
[v1.2.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.1.1...1.2.0
[v1.1.1]: https://github.com/crazzyghost/alphavantage-java/compare/1.1.0...1.1.1
[v1.1.0]: https://github.com/crazzyghost/alphavantage-java/compare/1.0.2...1.1.0
[v1.0.2]: https://github.com/crazzyghost/alphavantage-java/compare/1.0.1...1.0.2
[v1.0.1]: https://github.com/crazzyghost/alphavantage-java/releases/tag/1.0.1
[v1.0.0]: https://github.com/crazzyghost/alphavantage-java/tree/9d1cbca8a48899398513494ae6717bec0fa93cfb
[ajt001]: https://github.com/ajt001