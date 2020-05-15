[alphavantage](index.md) / [Fetcher](#)

# Fetcher

`public interface  Fetcher`

Defines an interface for pulling data from the API source.
A fetch operation can either fail or succeed.


### Types

|Name|Summary|
|----|-------|
| [SuccessCallback<V>](#) | Callback when the fetch operation succeeds. `<V>` the type of the reponse of the fetch operation.`interface SuccessCallback<V>` |
| [FailureCallback<V>](#) | Callback when the fetch operation fails. `interface FailureCallback` |


### Methods

|Name|Summary|
|----|-------|
| [fetch](#) | Perform a fetch operation. `void fetch()` |
