# alphavantage-java

This library was created to provide a fluent interface for accessing the [AlphaVantage API](https://www.alphavantage.co/) with Java.

## Getting Started

To get started using this library, make sure to get an [API Key](https://www.alphavantage.co/support/#api-key) from Alphavantage's website. Add the library as a dependency to your project

### Installation
=== ":simple-gradle: Gradle"
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
=== ":simple-apachemaven: Maven"
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

Step 1. `config`ure the wrapper

Step 2. Select a `category`

Step 3. Set the `parameters` for the selected category

Step 4. (Optional) Add `response callbacks`

Step 5. `fetch` results

#### 1. `Config`uring the wrapper

Access to the API is through the AlphaVantage Singleton which is accessed using the `static` `api()` method of the class.

!!! tip
    Initialize the singleton with a `Config` instance once through out your apps lifetime.

=== ":material-language-java: Java"
    ```java
    Config cfg = Config.builder()
        .key("#&ALPHA10100DEMOKEY")
        .timeOut(10)
        .build();
    ```

=== ":material-language-kotlin: Kotlin"
    ```kotlin
    var cfg = Config.builder()
        .key("#&ALPHA10100DEMOKEY")
        .timeOut(10)
        .build()
    ```

Use the config object to initialize the api instance. You will use this object to set your api key and configure the http client.

!!! warning
    Using the wrapper without setting a config or a config key will throw an exception.

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api().init(cfg);
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api().init(cfg)
    ```

That's it! We're good to go.

#### 2. Selecting a `category`

Here, we choose which data category/endpoint we want to access

!!! info
    Each of category is exposed through a method call in the instantiated wrapper.

| Category                 | Method                  |
|--------------------------|-------------------------|
| Stock Time Series Data   | `.timeSeries()`         |
| Forex Rate Data          | `.forex()`              |
| Exchange Rate Data       | `.exchangeRate()`       |
| Digital Currency Data    | `.crypto()`             |
| Technical Indicator Data | [`.technicalIndicator()`](technicalindicator.md) |
| Sector Performance Data  | `.sector()`              |
| Fundamental Data         | `.fundamentalData()`    |
| Economic Indicators      | `.economicIndicator()`  |
| Global Market Status     | `.marketStatus()`       |
| Ticker/Symbol Search     | `.search()`             |

For example, to select the Stock Time Series:

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
    ```

#### 3. Setting the `parameters` for the selected category

To set the api request parameters, call the appopriate parameter methods. For instance for the `function` parameter function you call `daily()` for the `TIME_SERIES_DAILY` function, `intraday()` for the `TIME_SERIES_INTRADAY`,  and so on.

Let's select the `TIME_SERIES_INTRADAY` function

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .intraday()
    ```

=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .intraday()
    ```

!!! tip
    Start setting parameters by calling an appropriate function method in the selected category

#### 4. Adding `response callbacks`

To handle responses add the `onSuccess()` or `onFailure()` async callbacks. Starting from version 1.5.0, this is an optional step.

=== ":material-language-java: Java"
    ```java
    public void handleSuccess(TimeSeriesResponse response) {
        plotGraph(response.getStockUnits());
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
        .onFailure(e->handleFailure(e))
        ...
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    fun handleSuccess(response:TimeSeriesResponse) {
        plotGraph(response.getStockUnits())
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
        .onFailure({ e-> handleFailure(e) })            
        ...
    ```


!!! info
    Callbacks are optional, you can choose to handle either or both

#### 5.  `fetch` results

When you are okay with setting the parameters call the `fetch()` method. Simple!

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .timeSeries()
        .intraday()
        .forSymbol("IBM")
        .interval(Interval.FIVE_MIN)
        .outputSize(OutputSize.FULL)
        .onSuccess(e->handleSuccess(e))
        .onFailure(e->handleFailure(e))
        .fetch();
    ```

=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .timeSeries()
        .intraday()
        .forSymbol("IBM")
        .interval(Interval.FIVE_MIN)
        .outputSize(OutputSize.FULL)
        .onSuccess({ e-> handleSuccess(e) })
        .onFailure({ e-> handleFailure(e) })
        .fetch()
    ```
From v1.5.0 up, you can also use the synchronous fetch method by calling the `fetchSync()` method without callback handlers.

=== ":material-language-java: Java"
    ```java
    TimeSeriesResponse response = AlphaVantage.api()
        .timeSeries()
        .intraday()
        .forSymbol("IBM")
        .interval(Interval.FIVE_MIN)
        .outputSize(OutputSize.FULL)
        .fetchSync();
    ```

=== ":material-language-kotlin: Kotlin"
    ```kotlin
    TimeSeriesResponse response = AlphaVantage.api()
        .timeSeries()
        .intraday()
        .forSymbol("IBM")
        .interval(Interval.FIVE_MIN)
        .outputSize(OutputSize.FULL)
        .fetchSync()
    ```

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