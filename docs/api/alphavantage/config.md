[alphavantage](index.md) / [Config](#)

# Config

`public class  Config extends Object`

Defines configuration parameters you can set for the library to use. You can set the `httpClient` for the library or use the default `httpClient`.

```java
HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
loggingInterceptor.level(Level.BASIC);

OkHttpClient client = new OkHttpClient.Builder()
    .connectTimeout(4, TimeUnit.SECONDS)
    .callTimeout(4, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .addInterceptor(mockInterceptor)
    .build();

Config config = Config.builder()
    .key("demo")
    .httpClient(client)
    .build();

//OR: use default http client

Config config = Config.builder()
    .key("demo")
    .build()
```


### Types

|Name|Summary|
|----|-------|
| [Builder](#) | `public static class Builder` |


### Constructors

|Name|Summary|
|----|-------|
| [Config](#) | private default constructor `private Config()` |

### Properties

|Name|Summary|
|----|-------|
| [key](#) | `private String key` |
| [timeOut](#) | `private int timeOut` |
| [httpClient](#) | `private OkHttpClient httpClient`|
| [BASE_URL](#) | `public static String BASE_URL` |

### Methods

|Name|Summary|
|----|-------|
| [builder](#) | `public static Builder builder()` |
| [defaultClient](#) | `private OkHttpClient defaultClient(int timeOut)` |
| [getTimeOut](#) | `public int getTimeOut()` |
| [getOkHttpClient](#) | `public OkHttpClient getOkHttpClient()` |
| [getKey](#) | `public String getKey()` |