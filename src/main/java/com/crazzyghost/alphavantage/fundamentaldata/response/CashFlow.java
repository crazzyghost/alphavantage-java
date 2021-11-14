package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.Noneable;
import com.squareup.moshi.Json;

public class CashFlow {
    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedCurrency")
    private String reportedCurrency;

    @Json(name="operatingCashflow")
    @Noneable
    private Long operatingCashflow;

    @Json(name="paymentsForOperatingActivities")
    @Noneable
    private Long paymentsForOperatingActivities;

    @Json(name="proceedsFromOperatingActivities")
    @Noneable
    private Long proceedsFromOperatingActivities;

    @Json(name="changeInOperatingLiabilities")
    @Noneable
    private Long changeInOperatingLiabilities;

    @Json(name="changeInOperatingAssets")
    @Noneable
    private Long changeInOperatingAssets;

    @Json(name="depreciationDepletionAndAmortization")
    @Noneable
    private Long depreciationDepletionAndAmortization;

    @Json(name="capitalExpenditures")
    @Noneable
    private Long capitalExpenditures;

    @Json(name="changeInReceivables")
    @Noneable
    private Long changeInReceivables;

    @Json(name="changeInInventory")
    @Noneable
    private Long changeInInventory;

    @Json(name="profitLoss")
    @Noneable
    private Long profitLoss;

    @Json(name="cashflowFromInvestment")
    @Noneable
    private Long cashflowFromInvestment;

    @Json(name="cashflowFromFinancing")
    @Noneable
    private Long cashflowFromFinancing;

    @Json(name="proceedsFromRepaymentsOfShortTermDebt")
    @Noneable
    private Long proceedsFromRepaymentsOfShortTermDebt;

    @Json(name="paymentsForRepurchaseOfCommonStock")
    @Noneable
    private Long paymentsForRepurchaseOfCommonStock;

    @Json(name="paymentsForRepurchaseOfEquity")
    @Noneable
    private Long paymentsForRepurchaseOfEquity;

    @Json(name="paymentsForRepurchaseOfPreferredStock")
    @Noneable
    private Long paymentsForRepurchaseOfPreferredStock;

    @Json(name="dividendPayout")
    @Noneable
    private Long dividendPayout;

    @Json(name="dividendPayoutCommonStock")
    @Noneable
    private Long dividendPayoutCommonStock;

    @Json(name="dividendPayoutPreferredStock")
    @Noneable
    private Long dividendPayoutPreferredStock;

    @Json(name="proceedsFromIssuanceOfCommonStock")
    @Noneable
    private Long proceedsFromIssuanceOfCommonStock;

    @Json(name="proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet")
    @Noneable
    private Long proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;

    @Json(name="proceedsFromIssuanceOfPreferredStock")
    @Noneable
    private Long proceedsFromIssuanceOfPreferredStock;

    @Json(name="proceedsFromRepurchaseOfEquity")
    @Noneable
    private Long proceedsFromRepurchaseOfEquity;

    @Json(name="proceedsFromSaleOfTreasuryStock")
    @Noneable
    private Long proceedsFromSaleOfTreasuryStock;

    @Json(name="changeInCashAndCashEquivalents")
    @Noneable
    private Long changeInCashAndCashEquivalents;

    @Json(name="changeInExchangeRate")
    @Noneable
    private Long changeInExchangeRate;

    @Json(name="netIncome")
    @Noneable
    private Long netIncome;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public Long getOperatingCashflow() {
        return operatingCashflow;
    }

    public Long getPaymentsForOperatingActivities() {
        return paymentsForOperatingActivities;
    }

    public Long getProceedsFromOperatingActivities() {
        return proceedsFromOperatingActivities;
    }

    public Long getChangeInOperatingLiabilities() {
        return changeInOperatingLiabilities;
    }

    public Long getChangeInOperatingAssets() {
        return changeInOperatingAssets;
    }

    public Long getDepreciationDepletionAndAmortization() {
        return depreciationDepletionAndAmortization;
    }

    public Long getCapitalExpenditures() {
        return capitalExpenditures;
    }

    public Long getChangeInReceivables() {
        return changeInReceivables;
    }

    public Long getChangeInInventory() {
        return changeInInventory;
    }

    public Long getProfitLoss() {
        return profitLoss;
    }

    public Long getCashflowFromInvestment() {
        return cashflowFromInvestment;
    }

    public Long getCashflowFromFinancing() {
        return cashflowFromFinancing;
    }

    public Long getProceedsFromRepaymentsOfShortTermDebt() {
        return proceedsFromRepaymentsOfShortTermDebt;
    }

    public Long getPaymentsForRepurchaseOfCommonStock() {
        return paymentsForRepurchaseOfCommonStock;
    }

    public Long getPaymentsForRepurchaseOfEquity() {
        return paymentsForRepurchaseOfEquity;
    }

    public Long getPaymentsForRepurchaseOfPreferredStock() {
        return paymentsForRepurchaseOfPreferredStock;
    }

    public Long getDividendPayout() {
        return dividendPayout;
    }

    public Long getDividendPayoutCommonStock() {
        return dividendPayoutCommonStock;
    }

    public Long getDividendPayoutPreferredStock() {
        return dividendPayoutPreferredStock;
    }

    public Long getProceedsFromIssuanceOfCommonStock() {
        return proceedsFromIssuanceOfCommonStock;
    }

    public Long getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet() {
        return proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    }

    public Long getProceedsFromIssuanceOfPreferredStock() {
        return proceedsFromIssuanceOfPreferredStock;
    }

    public Long getProceedsFromRepurchaseOfEquity() {
        return proceedsFromRepurchaseOfEquity;
    }

    public Long getProceedsFromSaleOfTreasuryStock() {
        return proceedsFromSaleOfTreasuryStock;
    }

    public Long getChangeInCashAndCashEquivalents() {
        return changeInCashAndCashEquivalents;
    }

    public Long getChangeInExchangeRate() {
        return changeInExchangeRate;
    }

    public Long getNetIncome() {
        return netIncome;
    }

    @Override
    public String toString() {
        return "CashFlow{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedCurrency='" + reportedCurrency + '\'' +
                ", operatingCashflow='" + operatingCashflow + '\'' +
                ", paymentsForOperatingActivities='" + paymentsForOperatingActivities + '\'' +
                ", proceedsFromOperatingActivities='" + proceedsFromOperatingActivities + '\'' +
                ", changeInOperatingLiabilities='" + changeInOperatingLiabilities + '\'' +
                ", changeInOperatingAssets='" + changeInOperatingAssets + '\'' +
                ", depreciationDepletionAndAmortization='" + depreciationDepletionAndAmortization + '\'' +
                ", capitalExpenditures='" + capitalExpenditures + '\'' +
                ", changeInReceivables='" + changeInReceivables + '\'' +
                ", changeInInventory='" + changeInInventory + '\'' +
                ", profitLoss='" + profitLoss + '\'' +
                ", cashflowFromInvestment='" + cashflowFromInvestment + '\'' +
                ", cashflowFromFinancing='" + cashflowFromFinancing + '\'' +
                ", proceedsFromRepaymentsOfShortTermDebt='" + proceedsFromRepaymentsOfShortTermDebt + '\'' +
                ", paymentsForRepurchaseOfCommonStock='" + paymentsForRepurchaseOfCommonStock + '\'' +
                ", paymentsForRepurchaseOfEquity='" + paymentsForRepurchaseOfEquity + '\'' +
                ", paymentsForRepurchaseOfPreferredStock='" + paymentsForRepurchaseOfPreferredStock + '\'' +
                ", dividendPayout='" + dividendPayout + '\'' +
                ", dividendPayoutCommonStock='" + dividendPayoutCommonStock + '\'' +
                ", dividendPayoutPreferredStock='" + dividendPayoutPreferredStock + '\'' +
                ", proceedsFromIssuanceOfCommonStock='" + proceedsFromIssuanceOfCommonStock + '\'' +
                ", proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet='" + proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet + '\'' +
                ", proceedsFromIssuanceOfPreferredStock='" + proceedsFromIssuanceOfPreferredStock + '\'' +
                ", proceedsFromRepurchaseOfEquity='" + proceedsFromRepurchaseOfEquity + '\'' +
                ", proceedsFromSaleOfTreasuryStock='" + proceedsFromSaleOfTreasuryStock + '\'' +
                ", changeInCashAndCashEquivalents='" + changeInCashAndCashEquivalents + '\'' +
                ", changeInExchangeRate='" + changeInExchangeRate + '\'' +
                ", netIncome='" + netIncome + '\'' +
                '}';
    }
}
