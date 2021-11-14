[alphavantage](index.md) / [Alphavantage](#)

# Alphavantage

`public class  Alphavantage extends Object`

Client interface of library. The API is accessed through this class. Exposes a singleton instance for interaction.

## Initialization

This class should only be initialized once by calling `init`.You will typically call this in your main application class. You cannot create an instance using `new`.

```java
Alphavantage.api().init(config)
```

### Constructors

|Name|Summary|
|----|-------|
| [Alphavantage](#) | private default constructor `private Alphavantage()` |


### Properties

|Name|Summary|
|----|-------|
| [INSTANCE](#) | `private static Alphavantage INSTANCE` |
| [config](#) | `private Config config` |

### Methods

|Name|Summary|
|----|-------|
| [api](#) | Get a reference to the api. `public static Alphavantage api()` |
| [init](#) | Initialize the library with your configuration. `public void init(Config config)` |
| [crypto](#) | Access to Digital Currencies. Returns a Crypto Instace for acces to Digital Currency data. `public Crypto crypto()` |
| [exchangeRate](#) | Access to Exchnage Rates Data. `public ExchangeRate exhcangeRate()` |
| [forex](#) | Access to Foreign Exchange Data. `public Forex forex()` |
| [indicator](#) | Access to Technical Indicators. Returns an Indicator instance for access to Technical Indicator Data `public Indicator indicator()` |
| [timeSeries](#) | Access to Stock Time Series Data.`public TimeSeries timeSeries()` |
| [sector](#) | Access to Sector Performance Data.`public Sector sector()` |
| [fundamentaldata](#) | Access to Fundamental Data.`public FundamentalData fundamentalData()` |

[api]: (#)
[init]: (#)
[crypto]: (#)
[exchangeRate]: (#)
[forex]: (#)
[indicator]: (#)
[timeseries]: (#)
[sector]: (#)
[fundamentaldata]: (#)
