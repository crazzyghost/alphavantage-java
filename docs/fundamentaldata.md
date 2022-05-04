## Fundamental Data

### Cash Flow

=== "Java"
        :::java
        AlphaVantage
            .api()
            .fundamentalData()
            .cashFlow()
            .forSymbol("IBM")
            .onSuccess((CashFlowResponse e) -> process(e))
            .fetch();

**Response Type:**
`CashFlowResponse`

### Income Statement

=== "Java"
        :::java
        AlphaVantage
            .api()
            .fundamentalData()
            .incomeStatement()
            .forSymbol("IBM")
            .onSuccess((IncomeStatementResponse e) -> process(e))
            .fetch();

**Response Type:**
`IncomeStatementResponse`

### Balance Sheet

=== "Java"
        :::java
        AlphaVantage
            .api()
            .fundamentalData()
            .balanceSheet()
            .forSymbol("IBM")
            .onSuccess((BalanceSheetResponse e) -> process(e))
            .fetch();

**Response Type:**
`BalanceSheetResponse`

### Earnings

=== "Java"
        :::java
        AlphaVantage
            .api()
            .fundamentalData()
            .earnings()
            .forSymbol("IBM")
            .onSuccess((EarningsResponse e) -> process(e))
            .fetch();

**Response Type:**
`EarningsResponse`

### Company Overview

=== "Java"
        :::java
        AlphaVantage
            .api()
            .fundamentalData()
            .companyOverview()
            .forSymbol("IBM")
            .onSuccess((CompanyOverviewResponse e) -> process(e))
            .fetch();

**Response Type:**
`CompanyOverviewResponse`
