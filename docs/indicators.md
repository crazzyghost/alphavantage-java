!!! warning "Deprecated"
    `.indicator()` is deprecated. Use [`.technicalIndicator()`](technicalindicator.md) instead — see the [Technical Indicators](technicalindicator.md) recipe.

!!! note
    All Indicator Response provide a `getIndicatorUnits()` method to acess technical indicator data and `getMetaData()` to access metadata information

## SMA

=== ":material-language-java: Java"
    ```java
    AlphaVantage.api()
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
        .vwap()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
        .bop()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
        .trange()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
        .ad()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
        .obv()
        .interval(Interval.WEEKLY)
        .forSymbol("AAPL")
        .onSuccess(e->onData(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage.api()
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
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
        .indicator()
        .htphasor()
        .interval(Interval.DAILY)
        .seriesType(SeriesType.OPEN)
        .forSymbol("AAPL")
        .onSuccess { e -> onData(e) }
        .fetch()
    ```

**Response Type:**
`HTPHASORResponse`
