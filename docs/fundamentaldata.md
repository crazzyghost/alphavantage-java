## Fundamental Data

### Cash Flow

=== ":material-language-java: Java"
    ```java
    AlphaVantage
        .api()
        .fundamentalData()
        .cashFlow()
        .forSymbol("IBM")
        .onSuccess((CashFlowResponse e) -> process(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage
        .api()
        .fundamentalData()
        .cashFlow()
        .forSymbol("IBM")
        .onSuccess { e -> process(e) }
        .fetch()
    ```

**Response Type:**
`CashFlowResponse`

### Income Statement

=== ":material-language-java: Java"
    ```java
    AlphaVantage
        .api()
        .fundamentalData()
        .incomeStatement()
        .forSymbol("IBM")
        .onSuccess((IncomeStatementResponse e) -> process(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage
        .api()
        .fundamentalData()
        .incomeStatement()
        .forSymbol("IBM")
        .onSuccess { e -> process(e) }
        .fetch()
    ```

**Response Type:**
`IncomeStatementResponse`

### Balance Sheet

=== ":material-language-java: Java"
    ```java
    AlphaVantage
        .api()
        .fundamentalData()
        .balanceSheet()
        .forSymbol("IBM")
        .onSuccess((BalanceSheetResponse e) -> process(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage
        .api()
        .fundamentalData()
        .balanceSheet()
        .forSymbol("IBM")
        .onSuccess { e -> process(e) }
        .fetch()
    ```

**Response Type:**
`BalanceSheetResponse`

### Earnings

=== ":material-language-java: Java"
    ```java
    AlphaVantage
        .api()
        .fundamentalData()
        .earnings()
        .forSymbol("IBM")
        .onSuccess((EarningsResponse e) -> process(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage
        .api()
        .fundamentalData()
        .earnings()
        .forSymbol("IBM")
        .onSuccess { e -> process(e) }
        .fetch()
    ```

**Response Type:**
`EarningsResponse`

### Company Overview

=== ":material-language-java: Java"
    ```java
    AlphaVantage
        .api()
        .fundamentalData()
        .companyOverview()
        .forSymbol("IBM")
        .onSuccess((CompanyOverviewResponse e) -> process(e))
        .fetch();
    ```
=== ":material-language-kotlin: Kotlin"
    ```kotlin
    AlphaVantage
        .api()
        .fundamentalData()
        .companyOverview()
        .forSymbol("IBM")
        .onSuccess { e -> process(e) }
        .fetch()
    ```

**Response Type:**
`CompanyOverviewResponse`
