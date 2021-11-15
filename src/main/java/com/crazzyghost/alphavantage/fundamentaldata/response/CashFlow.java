package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.NoneableLong;
import com.squareup.moshi.Json;

public class CashFlow {
    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedCurrency")
    private String reportedCurrency;

    @Json(name="operatingCashflow")
    @NoneableLong
    private Long operatingCashflow;

    @Json(name="paymentsForOperatingActivities")
    @NoneableLong
    private Long paymentsForOperatingActivities;

    @Json(name="proceedsFromOperatingActivities")
    @NoneableLong
    private Long proceedsFromOperatingActivities;

    @Json(name="changeInOperatingLiabilities")
    @NoneableLong
    private Long changeInOperatingLiabilities;

    @Json(name="changeInOperatingAssets")
    @NoneableLong
    private Long changeInOperatingAssets;

    @Json(name="depreciationDepletionAndAmortization")
    @NoneableLong
    private Long depreciationDepletionAndAmortization;

    @Json(name="capitalExpenditures")
    @NoneableLong
    private Long capitalExpenditures;

    @Json(name="changeInReceivables")
    @NoneableLong
    private Long changeInReceivables;

    @Json(name="changeInInventory")
    @NoneableLong
    private Long changeInInventory;

    @Json(name="profitLoss")
    @NoneableLong
    private Long profitLoss;

    @Json(name="cashflowFromInvestment")
    @NoneableLong
    private Long cashflowFromInvestment;

    @Json(name="cashflowFromFinancing")
    @NoneableLong
    private Long cashflowFromFinancing;

    @Json(name="proceedsFromRepaymentsOfShortTermDebt")
    @NoneableLong
    private Long proceedsFromRepaymentsOfShortTermDebt;

    @Json(name="paymentsForRepurchaseOfCommonStock")
    @NoneableLong
    private Long paymentsForRepurchaseOfCommonStock;

    @Json(name="paymentsForRepurchaseOfEquity")
    @NoneableLong
    private Long paymentsForRepurchaseOfEquity;

    @Json(name="paymentsForRepurchaseOfPreferredStock")
    @NoneableLong
    private Long paymentsForRepurchaseOfPreferredStock;

    @Json(name="dividendPayout")
    @NoneableLong
    private Long dividendPayout;

    @Json(name="dividendPayoutCommonStock")
    @NoneableLong
    private Long dividendPayoutCommonStock;

    @Json(name="dividendPayoutPreferredStock")
    @NoneableLong
    private Long dividendPayoutPreferredStock;

    @Json(name="proceedsFromIssuanceOfCommonStock")
    @NoneableLong
    private Long proceedsFromIssuanceOfCommonStock;

    @Json(name="proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet")
    @NoneableLong
    private Long proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;

    @Json(name="proceedsFromIssuanceOfPreferredStock")
    @NoneableLong
    private Long proceedsFromIssuanceOfPreferredStock;

    @Json(name="proceedsFromRepurchaseOfEquity")
    @NoneableLong
    private Long proceedsFromRepurchaseOfEquity;

    @Json(name="proceedsFromSaleOfTreasuryStock")
    @NoneableLong
    private Long proceedsFromSaleOfTreasuryStock;

    @Json(name="changeInCashAndCashEquivalents")
    @NoneableLong
    private Long changeInCashAndCashEquivalents;

    @Json(name="changeInExchangeRate")
    @NoneableLong
    private Long changeInExchangeRate;

    @Json(name="netIncome")
    @NoneableLong
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
