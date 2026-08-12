!!! note
    All Indicator Response provide a `getIndicatorUnits()` method to access technical indicator data and `getMetaData()` to access metadata information

## SMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .sma()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .sma()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`SMAResponse`

## EMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .ema()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .ema()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`EMAResponse`

## WMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .wma()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .wma()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`WMAResponse`

## DEMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .dema()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .dema()
        .forSymbol("AAPL")
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`DEMAResponse`

## TEMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .tema()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .tema()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`TEMAResponse`

## TRIMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .trima()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .trima()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`TRIMAResponse`

## KAMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .kama()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .kama()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`KAMAResponse`

## MAMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .mama()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .fastLimit(0.1)
        .slowLimit(0.5)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .mama()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .fastLimit(0.1)
        .slowLimit(0.5)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MAMAResponse`

## VWAP

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .vwap()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .vwap()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`VWAPResponse`

## T3

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .t3()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .t3()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`T3Response`

## MACD

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .macd()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .fastPeriod(12)
        .slowPeriod(26)
        .signalPeriod(9)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .macd()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .fastPeriod(12)
        .slowPeriod(26)
        .signalPeriod(9)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MACDResponse`

## MACDEXT

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .macdext()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .fastPeriod(12)
        .slowPeriod(26)
        .signalPeriod(9)
        .slowMaType(MAType.SMA)
        .fastMaType(MAType.MAMA)
        .signalMaType(MAType.SMA)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .macdext()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .fastPeriod(12)
        .slowPeriod(26)
        .signalPeriod(9)
        .slowMaType(MAType.SMA)
        .fastMaType(MAType.MAMA)
        .signalMaType(MAType.SMA)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MACDEXTResponse`

## STOCH

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .stoch()
        .interval(Interval.SIXTY_MIN)
        .fastKPeriod(5)
        .slowKPeriod(3)
        .slowDPeriod(3)
        .slowKMaType(MAType.SMA)
        .slowDMaType(MAType.SMA)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .stoch()
        .interval(Interval.SIXTY_MIN)
        .fastKPeriod(5)
        .slowKPeriod(3)
        .slowDPeriod(3)
        .slowKMaType(MAType.SMA)
        .slowDMaType(MAType.SMA)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`STOCHResponse`

## STOCHF

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .stochf()
        .interval(Interval.SIXTY_MIN)
        .fastKPeriod(5)
        .fastDPeriod(3)
        .fastDMaType(MAType.MAMA)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .stochf()
        .interval(Interval.SIXTY_MIN)
        .fastKPeriod(5)
        .fastDPeriod(3)
        .fastDMaType(MAType.MAMA)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`STOCHFResponse`

## RSI

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .rsi()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .rsi()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`RSIResponse`

## STOCHRSI

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .stochrsi()
        .interval(Interval.SIXTY_MIN)
        .fastKPeriod(5)
        .fastDPeriod(3)
        .fastDMaType(MAType.MAMA)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .stochrsi()
        .interval(Interval.SIXTY_MIN)
        .fastKPeriod(5)
        .fastDPeriod(3)
        .fastDMaType(MAType.MAMA)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`STOCHRSIResponse`

## WILLR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .willr()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .willr()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`WILLRResponse`

## ADX

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .adx()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .adx()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ADXResponse`

## ADXR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .adxr()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .adxr()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ADXRResponse`

## PPO

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .ppo()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .maType(MAType.MAMA)
        .fastPeriod(10)
        .slowPeriod(26)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .ppo()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .maType(MAType.MAMA)
        .fastPeriod(10)
        .slowPeriod(26)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`PPOResponse`

## APO

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .apo()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .maType(MAType.MAMA)
        .fastPeriod(10)
        .slowPeriod(26)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .apo()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .maType(MAType.MAMA)
        .fastPeriod(10)
        .slowPeriod(26)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`APOResponse`

## MOM

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .mom()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .mom()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MOMResponse`

## BOP

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .bop()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .bop()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`BOPResponse`

## CCI

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .cci()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .cci()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`CCIResponse`

## CMO

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .cmo()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .cmo()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`CMOResponse`

## ROC

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .roc()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .roc()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ROCResponse`

## ROCR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .rocr()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .rocr()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ROCRResponse`

## AROON

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .aroon()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .aroon()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`AROONResponse`

## AROONOSC

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .aroonosc()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .aroonosc()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`AROONOSCResponse`

## MFI

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .mfi()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .mfi()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MFIResponse`

## TRIX

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .trix()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .trix()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`TRIXResponse`

## ULTOSC

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .ultosc()
        .interval(Interval.SIXTY_MIN)
        .timePeriod1(7)
        .timePeriod2(14)
        .timePeriod3(28)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .ultosc()
        .interval(Interval.SIXTY_MIN)
        .timePeriod1(7)
        .timePeriod2(14)
        .timePeriod3(28)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ULTOSCResponse`

## DX

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .dx()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .dx()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`DXResponse`

## MINUS_DI

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .minusdi()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .minusdi()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MINUSDIResponse`

## PLUS_DI

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .plusdi()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .plusdi()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`PLUSDIResponse`

## MINUS_DM

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .minusdm()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .minusdm()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MINUSDMResponse`

## PLUS_DM

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .plusdm()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .plusdm()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`PLUSDMResponse`

## BBANDS

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .bbands()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .seriesType(SeriesType.OPEN)
        .nbdevdn(4)
        .nbdevup(4)
        .maType(MAType.SMA)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .bbands()
        .interval(Interval.DAILY)
        .timePeriod(60)
        .seriesType(SeriesType.OPEN)
        .nbdevdn(4)
        .nbdevup(4)
        .maType(MAType.SMA)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`BBANDSResponse`

## MIDPOINT

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .midpoint()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .midpoint()
        .interval(Interval.WEEKLY)
        .seriesType(SeriesType.OPEN)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MIDPOINTResponse`

## MIDPRICE

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .midprice()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .midprice()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`MIDPRICEResponse`

## SAR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .sar()
        .interval(Interval.DAILY)
        .acceleration(0.02)
        .maximum(0.50)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .sar()
        .interval(Interval.DAILY)
        .acceleration(0.02)
        .maximum(0.50)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`SARResponse`

## TRANGE

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .trange()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .trange()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`TRANGEResponse`

## ATR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .atr()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .atr()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ATRResponse`

## NATR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .natr()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .natr()
        .interval(Interval.WEEKLY)
        .timePeriod(60)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`NATRResponse`

## AD

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .ad()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .ad()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ADResponse`

## ADOSC

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .adosc()
        .interval(Interval.WEEKLY)
        .fastPeriod(3)
        .slowPeriod(10)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .adosc()
        .interval(Interval.WEEKLY)
        .fastPeriod(3)
        .slowPeriod(10)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`ADOSCResponse`

## OBV

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .obv()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .obv()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`OBVResponse`

## HT_TRENDLINE

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .httrendline()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .httrendline()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTTRENDLINEResponse`

## HT_SINE

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .htsine()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .htsine()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTSINEResponse`

## HT_TRENDMODE

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .httrendmode()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .httrendmode()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTTRENDMODEResponse`

## HT_DCPERIOD

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .htdcperiod()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .htdcperiod()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTDCPERIODResponse`

## HT_DCPHASE

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .htdcphase()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .htdcphase()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTDCPHASEResponse`

## HT_PHASOR

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .technicalIndicator()
        .htphasor()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .technicalIndicator()
        .htphasor()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTPHASORResponse`
