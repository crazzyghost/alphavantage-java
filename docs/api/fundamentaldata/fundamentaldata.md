[alphavantage](../alphavantage/index.md) / [fundamentaldata](./index.md) / [FundamentalData](#)

# Sector

`public final class FundamentalData extends Object implements `[`Fetcher`](../alphavantage/fetcher.md)

Access to Fundamental Data

### Constructors

|Name|Summary|
|----|-------|
| [FundamentalData](./fundamentaldata.md) | `public FundamentalData(Config config)` |


### Properties

|Name|Summary|
|----|-------|
| [config](#) | `private Config config` |
| [builder](#) | `private FundamentaDataRequest.Builder builder` |
| [successCallback](#) | `private Fetcher.SuccessCallback<?> successCallback` |
| [failureCallback](#) | `private Fetcher.FailureCallback failureCallback` |

### Methods

|Name|Summary|
|----|-------|
| [onSuccess](#) | `public FundamentalData onSuccess(Fetcher.SuccessCallback<?> callback)` |
| [onFailure](#) | `public FundamentalData onFailure(Fetcher.FailureCallback callback)` |
| [fetch](#) | `public void fetch()` |
| [fetchSync](#) | `public void fetchSync()` |
