## Economic Indicator Data

### Real GDP

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .realGdp()
            .interval(Interval.ANNUAL)
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Real GDP Per Capita

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .realGdpPerCapita()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Treasury Yield

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .treasuryYield()
            .interval(Interval.MONTHLY)
            .maturity(Maturity.SEVEN_YEAR)
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Federal Funds Rate

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .federalFundsRate()
            .interval(Interval.MONTHLY)
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### CPI

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .cpi()
            .interval(Interval.MONTHLY)
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Inflation

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .inflation()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Inflation Expectation

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .inflationExpectation()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Consumer Sentiment

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .consumerSentiment()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Retail Sales

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .retailSales()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Durable Goods Orders

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .durables()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Unemployment Rate

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .unemployment()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

### Non farm Payroll

=== "Java"
        :::java
        EconomicIndicatorResponse response =  AlphaVantage
            .api()
            .economicIndicator()
            .nonFarmPayroll()
            .fetchSync();

**Response Type:**
`EconomicIndicatorResponse`

