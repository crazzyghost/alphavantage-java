# alphavantage-java

This library was created to provide a fluent interface for accessing the [AlphaVantage API](https://www.alphavantage.co/) with Java.

## Getting Started

To get started using this library, make sure to get an [API Key](https://www.alphavantage.co/support/#api-key) from Alphavantage's website. Add the library as a dependency to your java/android project

### Gradle Installation

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    ...
    implementation 'com.github.crazzyghost:alphavantage-java:x.y.z'
}
```

### Maven Installation

```xml
<repositories>
    ...
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    ...
</repositories>
<dependencies>
    ...
    <dependency>
        <groupId>com.github.crazzyghost</groupId>
        <artifactId>alphavantage-java</artifactId>
        <version>x.y.z</version>
    </dependency>
    ...
</dependencies>
```

### Quick Usage Guide

These five steps summarize how to access data using this library

1. `config`ure the wrapper
2. Select a `category`
3. Set the `parameters` for the selected category
4. Add `response callbacks`
5. `fetch` results

#### 1. `Config`uring the wrapper

Access to the API is through the AlphaVantage Singleton which is accessed using the `static` `api()` method of the class.

!!! tip
    Initialize the singleton with a `Config` instance once through out your apps lifetime.

=== "Java"
        :::java
        Config cfg = Config.builder()
            .key("#&ALPHA10100DEMOKEY")
            .timeOut(10)
            .build();

=== "Kotlin"
        :::kotlin
        var cfg = Config.builder()
            .key("#&ALPHA10100DEMOKEY")
            .timeOut(10)
            .build()

The config object is then used to initialize the instance. You will use this object to set your api key and configure the http client.

!!! warning
    Using the wrapper without setting a config or a config key will throw an exception.

=== "Java"
        :::java
        AlphaVantage.api().init(cfg);
=== "Kotlin"
        :::kotlin
        AlphaVantage.api().init(cfg)

That's it! We're good to go.

#### 2. Selecting a `category`

The available API categories to select from currently are: Stock Time Series, Foreign Exchange(FX), Digital Currencies, Exchange Rates and Technical Indicators.

!!! info
    Each of these categories is exposed through a method call in the instantiated wrapper.

| Category                  |   Method              |
| -------------             | ------------------    |
| Stock Time Series Data    | `.timeSeries()`       |
| Forex Rate Data           | `.forex()`            |
| Exchange Rate Data        | `.exchangeRate()`     |
| Digital Currency Data     | `.crypto()`           |
| Technical Indicator Data  | `.indicator()`        |

For example, to select the Stock Time Series:

=== "Java"
        :::java
        AlphaVantage.api()
            .timeSeries()

=== "Kotlin"
        :::kotlin
        AlphaVantage.api()
            .timeSeries()


#### 3. Setting the `parameters` for the selected category

To set the api request parameters, call the appopriate parameter methods. For instance for the `function` parameter function you call `daily()` for the `TIME_SERIES_DAILY` function, `intraday()` for the `TIME_SERIES_INTRADAY`,  and so on.

Let's select the `TIME_SERIES_INTRADAY` function

=== "Java"
        :::java
        AlphaVantage.api()
            .timeSeries()
            .intraday()
        ...

=== "Kotlin"
        :::kotlin
        AlphaVantage.api()
            .timeSeries()
            .intraday()
        ...

!!! tip
    Start setting parameters by calling an appropriate function method in the selected category

#### 4. Adding `response callbacks`

To handle responses add the `onSuccess()` or `onFailure()` callbacks.

=== "Java"
        :::java
        public void handleSuccess(TimeSeriesResponse response) {
            plotGraph(reponse.getStockUnits());
        }
        public void handleFailure(AlphaVantageException error) {
            /* uh-oh! */
        }

        AlphaVantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("IBM")
            .interval(Interval.FIVE_MIN)
            .outputSize(OutputSize.FULL)
            .onSuccess(e->handleSuccess(e))
            .onFailure(e->hanldeFailure(e))
            ...

=== "Kotlin"
        :::kotlin
        fun handleSuccess(response:TimeSeriesResponse) {
            plotGraph(reponse.getStockUnits())
        }
        fun handleFailure(error:AlphaVantageException) {
            /* uh-oh! */
        }        

        AlphaVantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("IBM")
            .interval(Interval.FIVE_MIN)
            .outputSize(OutputSize.FULL)
            .onSuccess({ e-> handleSuccess(e) })
            .onFailure({ e-> hanldeFailure(e) })            
        ...


!!! info
    Callbacks are optional, you can choose to handle either or both

#### 5.  `fetch` results

When you are okay with setting the parameters call the `fetch()` method. Simple!

=== "Java"
        :::java
        AlphaVantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("IBM")
            .interval(Interval.FIVE_MIN)
            .outputSize(OutputSize.FULL)
            .onSuccess(e->handleSuccess(e))
            .onFailure(e->hanldeFailure(e))
            .fetch();

=== "Kotlin"
        :::java
        AlphaVantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("IBM")
            .interval(Interval.FIVE_MIN)
            .outputSize(OutputSize.FULL)
            .onSuccess({ e-> handleSuccess(e) })
            .onFailure({ e-> hanldeFailure(e) })
            .fetch()

## Releases

Release history can be found in the [change log](changelog.md).

## License

```txt
MIT License

Copyright (c) 2020 Sylvester Sefa-Yeboah

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```