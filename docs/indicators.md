!!! note
    All Indicator Response provide a `getIndicatorUnits()` method to acess technical indicator data and `getMetaData()` to access metadata information

## SMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## EMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .ema()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## WMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .wma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## DEMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .dema()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## TEMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .tema()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## TRIMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .trima()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## KAMA

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .kama()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## MAMA

=== "Java"
        :::java
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

**Response Type:**
`MAMAResponse`

## VWAP

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .vwap()
            .interval(Interval.WEEKLY)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SimpleIndicatorResponse`

## T3

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .t3()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## MACD

=== "Java"
        :::java
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

**Response Type:**
`MACDResponse`

## MACDEXT

=== "Java"
        :::java
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

**Response Type:**
`MACDEXTResponse`

## STOCH

=== "Java"
        :::java
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

**Response Type:**
`STOCHResponse`

## STOCHF

=== "Java"
        :::java
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

**Response Type:**
`STOCHFResponse`

## RSI

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .rsi()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## STOCHRSI

=== "Java"
        :::java
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

**Response Type:**
`STOCHRSIResponse`

## WILLR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .willr()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## ADX

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .adx()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## ADXR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .adxr()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## PPO

=== "Java"
        :::java
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

**Response Type:**
`PriceOscillatorResponse`

## APO

=== "Java"
        :::java
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

**Response Type:**
`PriceOscillatorResponse`

## MOM

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .mom()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## BOP

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .bop()
            .interval(Interval.WEEKLY)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SimpleIndicatorResponse`

## CCI

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .cci()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## CMO

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .cmo()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## ROC

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .roc()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## ROCR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .rocr()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## AROON

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .aroon()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`AROONResponse`

## AROONOSC

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .aroonosc()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## MFI

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .mfi()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## TRIX

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .trix()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## ULTOSC

=== "Java"
        :::java
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

**Response Type:**
`ULTOSCResponse`

## DX

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .dx()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## MINUS_DI

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .minusdi()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## PLUS_DI

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .plusdi()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## MINUS_DM

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .minusdm()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## PLUS_DM

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .plusdm()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## BBANDS

=== "Java"
        :::java
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

**Response Type:**
`BBANDSResponse`

## MIDPOINT

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .midpoint()
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicSeriesResponse`

## MIDPRICE

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .midprice()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## SAR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .sar()
            .interval(Interval.DAILY)
            .acceleration(0.02)
            .maximum(0.50)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SARResponse`

## TRANGE

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .trange()
            .interval(Interval.WEEKLY)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SimpleIndicatorResponse`

## ATR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .atr()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## NATR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .natr()
            .interval(Interval.WEEKLY)
            .timePeriod(60)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`PeriodicResponse`

## AD

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .ad()
            .interval(Interval.WEEKLY)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SimpleIndicatorResponse`

## ADOSC

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .adosc()
            .interval(Interval.WEEKLY)
            .fastPeriod(3)
            .slowPeriod(10)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`ADOSCResponse`

## OBV

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .obv()
            .interval(Interval.WEEKLY)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SimpleIndicatorResponse`

## HT_TRENDLINE

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .httrendline()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SeriesResponse`

## HT_SINE

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .htsine()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`HTSINEResponse`

## HT_TRENDMODE

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .httrendmode()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SeriesResponse`

## HT_DCPERIOD

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .htdcperiod()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SeriesResponse`

## HT_DCPHASE

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .htdcphase()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`SeriesResponse`

## HT_PHASOR

=== "Java"
        :::java
        AlphaVantage.api()
            .indicator()
            .httrendmode()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("AAPL")
            .onSuccess(e->onData(e))
            .fetch();

**Response Type:**
`HTPHASORResponse`

