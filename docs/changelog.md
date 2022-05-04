# Changelog
## v1.6.1 - 15-11-2021
- Fix: [JitPack build failure](https://jitpack.io/com/github/crazzyghost/alphavantage-java/1.6.0/build.log)
- Fix: BalanceSheet field types

## v1.6.0 - 14-11-2021

- New: Support for fundamental data

## v1.5.0 - 11-01-2021

- New: Support for synchronous requests

## v1.4.2 - 20-10-2020

- [Missing crypto unit date](https://github.com/crazzyghost/alphavantage-java/pull/13) 

## v1.4.1 - 25-07-2020

- Fix: Array index out of bounds exception caused by [empty API response](https://github.com/crazzyghost/alphavantage-java/issues/9)

## v1.4.0 - 13-06-2020

- New: Support for Sector Performances

## v1.3.2 - 21-05-2020

- Fix: Maven build [issue](https://github.com/crazzyghost/alphavantage-java/issues/6)

## v1.3.1 - 21-05-2020

- Fix: Multiple async requests for enpoint [bug](https://github.com/crazzyghost/alphavantage-java/issues/8)

## v1.3.0 - 09-05-2020

- New: Support for Quote Endpoint
- Changed: RequestHelper to RequestProxy for TimeSeries
- Fix: Time Series IntradayRequest interval bug

## v1.2.1 - 07-05-2020

- Fix: Forex IntradayRequest interval bug

## v1.2.0 - 05-05-2020

- New: Support for crypto currency Health Index

## v1.1.1 - 04-05-2020

- New: Bid Rates and Ask Rates for Exchange Rates
- Fix: Exchange Rates response data access

## v1.1.0 - 04-05-2020

- New: Support for Technical Indicators

## v1.0.2 - 24-04-2020

- Fix: API url parameter bug

## v1.0.1 - 17-04-2020

- Fix: Incorrect mapping of time series data
- Fix: Getters for Crypto and Forex Units
- Change: Rename crypto subpackages

## v1.0.0 - 23-10-2019

- New: Support for Time Series Data
- New:Support for Forex Data
- New:Support for Crypto Currencies
- New:Support for Exchange Rates
- New:Tests for Time Series
