## Economic Indicator Data

### Real GDP

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .realGdp()
        .interval(Interval.ANNUAL)
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .realGdp()
        .interval(Interval.ANNUAL)
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Real GDP Per Capita

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .realGdpPerCapita()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .realGdpPerCapita()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Treasury Yield

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .treasuryYield()
        .interval(Interval.MONTHLY)
        .maturity(Maturity.SEVEN_YEAR)
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .treasuryYield()
        .interval(Interval.MONTHLY)
        .maturity(Maturity.SEVEN_YEAR)
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Federal Funds Rate

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .federalFundsRate()
        .interval(Interval.MONTHLY)
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .federalFundsRate()
        .interval(Interval.MONTHLY)
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### CPI

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .cpi()
        .interval(Interval.MONTHLY)
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .cpi()
        .interval(Interval.MONTHLY)
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Inflation

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .inflation()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .inflation()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Inflation Expectation

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .inflationExpectation()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .inflationExpectation()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Consumer Sentiment

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .consumerSentiment()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .consumerSentiment()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Retail Sales

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .retailSales()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .retailSales()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Durable Goods Orders

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .durables()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .durables()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Unemployment Rate

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .unemployment()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .unemployment()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`

### Non farm Payroll

=== ":material-language-java: Java"
    ```java
    EconomicIndicatorResponse response = AlphaVantage
        .api()
        .economicIndicator()
        .nonFarmPayroll()
        .fetchSync();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    val response = AlphaVantage
        .api()
        .economicIndicator()
        .nonFarmPayroll()
        .fetchSync()
    ```

**Response Type:**
`EconomicIndicatorResponse`
