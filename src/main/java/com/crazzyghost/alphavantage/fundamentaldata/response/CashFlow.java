package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.squareup.moshi.Json;

public class CashFlow {
    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedCurrency")
    private String reportedCurrency;
    @Json(name="operatingCashflow")
    private String operatingCashflow;
    @Json(name="paymentsForOperatingActivities")
    private String paymentsForOperatingActivities;
    @Json(name="proceedsFromOperatingActivities")
    private String proceedsFromOperatingActivities;
    @Json(name="changeInOperatingLiabilities")
    private String changeInOperatingLiabilities;
    @Json(name="changeInOperatingAssets")
    private String changeInOperatingAssets;
    @Json(name="depreciationDepletionAndAmortization")
    private String depreciationDepletionAndAmortization;
    @Json(name="capitalExpenditures")
    private String capitalExpenditures;
    @Json(name="changeInReceivables")
    private String changeInReceivables;
    @Json(name="changeInInventory")
    private String changeInInventory;
    @Json(name="profitLoss")
    private String profitLoss;
    @Json(name="cashflowFromInvestment")
    private String cashflowFromInvestment;
    @Json(name="cashflowFromFinancing")
    private String cashflowFromFinancing;
    @Json(name="proceedsFromRepaymentsOfShortTermDebt")
    private String proceedsFromRepaymentsOfShortTermDebt;
    @Json(name="paymentsForRepurchaseOfCommonStock")
    private String paymentsForRepurchaseOfCommonStock;
    @Json(name="paymentsForRepurchaseOfEquity")
    private String paymentsForRepurchaseOfEquity;
    @Json(name="paymentsForRepurchaseOfPreferredStock")
    private String paymentsForRepurchaseOfPreferredStock;
    @Json(name="dividendPayout")
    private String dividendPayout;
    @Json(name="dividendPayoutCommonStock")
    private String dividendPayoutCommonStock;
    @Json(name="dividendPayoutPreferredStock")
    private String dividendPayoutPreferredStock;
    @Json(name="proceedsFromIssuanceOfCommonStock")
    private String proceedsFromIssuanceOfCommonStock;
    @Json(name="proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet")
    private String proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    @Json(name="proceedsFromIssuanceOfPreferredStock")
    private String proceedsFromIssuanceOfPreferredStock;
    @Json(name="proceedsFromRepurchaseOfEquity")
    private String proceedsFromRepurchaseOfEquity;
    @Json(name="proceedsFromSaleOfTreasuryStock")
    private String proceedsFromSaleOfTreasuryStock;
    @Json(name="changeInCashAndCashEquivalents")
    private String changeInCashAndCashEquivalents;
    @Json(name="changeInExchangeRate")
    private String changeInExchangeRate;
    @Json(name="netIncome")
    private String netIncome;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public String getOperatingCashflow() {
        return operatingCashflow;
    }

    public String getPaymentsForOperatingActivities() {
        return paymentsForOperatingActivities;
    }

    public String getProceedsFromOperatingActivities() {
        return proceedsFromOperatingActivities;
    }

    public String getChangeInOperatingLiabilities() {
        return changeInOperatingLiabilities;
    }

    public String getChangeInOperatingAssets() {
        return changeInOperatingAssets;
    }

    public String getDepreciationDepletionAndAmortization() {
        return depreciationDepletionAndAmortization;
    }

    public String getCapitalExpenditures() {
        return capitalExpenditures;
    }

    public String getChangeInReceivables() {
        return changeInReceivables;
    }

    public String getChangeInInventory() {
        return changeInInventory;
    }

    public String getProfitLoss() {
        return profitLoss;
    }

    public String getCashflowFromInvestment() {
        return cashflowFromInvestment;
    }

    public String getCashflowFromFinancing() {
        return cashflowFromFinancing;
    }

    public String getProceedsFromRepaymentsOfShortTermDebt() {
        return proceedsFromRepaymentsOfShortTermDebt;
    }

    public String getPaymentsForRepurchaseOfCommonStock() {
        return paymentsForRepurchaseOfCommonStock;
    }

    public String getPaymentsForRepurchaseOfEquity() {
        return paymentsForRepurchaseOfEquity;
    }

    public String getPaymentsForRepurchaseOfPreferredStock() {
        return paymentsForRepurchaseOfPreferredStock;
    }

    public String getDividendPayout() {
        return dividendPayout;
    }

    public String getDividendPayoutCommonStock() {
        return dividendPayoutCommonStock;
    }

    public String getDividendPayoutPreferredStock() {
        return dividendPayoutPreferredStock;
    }

    public String getProceedsFromIssuanceOfCommonStock() {
        return proceedsFromIssuanceOfCommonStock;
    }

    public String getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet() {
        return proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    }

    public String getProceedsFromIssuanceOfPreferredStock() {
        return proceedsFromIssuanceOfPreferredStock;
    }

    public String getProceedsFromRepurchaseOfEquity() {
        return proceedsFromRepurchaseOfEquity;
    }

    public String getProceedsFromSaleOfTreasuryStock() {
        return proceedsFromSaleOfTreasuryStock;
    }

    public String getChangeInCashAndCashEquivalents() {
        return changeInCashAndCashEquivalents;
    }

    public String getChangeInExchangeRate() {
        return changeInExchangeRate;
    }

    public String getNetIncome() {
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
