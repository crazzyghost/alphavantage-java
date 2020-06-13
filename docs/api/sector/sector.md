[alphavantage](../alphavantage/index.md) / [sector](./index.md) / [Sector](./sector.md)

# Sector

`public final class Sector extends Object implements `[`Fetcher`](../alphavantage/fetcher.md)

Access to Sector Performance Data

### Constructors

|Name|Summary|
|----|-------|
| [Sector](./sector.md) | `public Sector(Config config)` |


### Properties

|Name|Summary|
|----|-------|
| [config](#) | `private Config config` |
| [builder](#) | `private SectorRequest.Builder builder` |
| [successCallback](#) | `private Fetcher.SuccessCallback<SectorResponse> successCallback` |
| [failureCallback](#) | `private Fetcher.FailureCallback failureCallback` |

### Methods

|Name|Summary|
|----|-------|
| [onSuccess](#) | `public Sector onSuccess(Fetcher.SuccessCallback<SectorResponse> callback)` |
| [onFailure](#) | `public Sector onFailure(Fetcher.FailureCallback callback)` |
| [fetch](#) | `public void fetch()` |
