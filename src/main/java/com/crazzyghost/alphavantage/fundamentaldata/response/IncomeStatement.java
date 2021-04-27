package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.squareup.moshi.Json;

public class IncomeStatement {

    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedCurrency")
    private String reportedCurrency;
    @Json(name="grossProfit")
    private String grossProfit;
    @Json(name="totalRevenue")
    private String totalRevenue;
    @Json(name="costOfRevenue")
    private String costOfRevenue;
    @Json(name="costofGoodsAndServicesSold")
    private String costofGoodsAndServicesSold;
    @Json(name="operatingIncome")
    private String operatingIncome;
    @Json(name="sellingGeneralAndAdministrative")
    private String sellingGeneralAndAdministrative;
    @Json(name="researchAndDevelopment")
    private String researchAndDevelopment;
    @Json(name="operatingExpenses")
    private String operatingExpenses;
    @Json(name="investmentIncomeNet")
    private String investmentIncomeNet;
    @Json(name="netInterestIncome")
    private String netInterestIncome;
    @Json(name="interestIncome")
    private String interestIncome;
    @Json(name="interestExpense")
    private String interestExpense;
    @Json(name="nonInterestIncome")
    private String nonInterestIncome;
    @Json(name="otherNonOperatingIncome")
    private String otherNonOperatingIncome;
    @Json(name="depreciation")
    private String depreciation;
    @Json(name="depreciationAndAmortization")
    private String depreciationAndAmortization;
    @Json(name="incomeBeforeTax")
    private String incomeBeforeTax;
    @Json(name="incomeTaxExpense")
    private String incomeTaxExpense;
    @Json(name="interestAndDebtExpense")
    private String interestAndDebtExpense;
    @Json(name="netIncomeFromContinuingOperations")
    private String netIncomeFromContinuingOperations;
    @Json(name="comprehensiveIncomeNetOfTax")
    private String comprehensiveIncomeNetOfTax;
    @Json(name="ebit")
    private String ebit;
    @Json(name="ebitda")
    private String ebitda;
    @Json(name="netIncome")
    private String netIncome;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public String getGrossProfit() {
        return grossProfit;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public String getCostOfRevenue() {
        return costOfRevenue;
    }

    public String getCostofGoodsAndServicesSold() {
        return costofGoodsAndServicesSold;
    }

    public String getOperatingIncome() {
        return operatingIncome;
    }

    public String getSellingGeneralAndAdministrative() {
        return sellingGeneralAndAdministrative;
    }

    public String getResearchAndDevelopment() {
        return researchAndDevelopment;
    }

    public String getOperatingExpenses() {
        return operatingExpenses;
    }

    public String getInvestmentIncomeNet() {
        return investmentIncomeNet;
    }

    public String getNetInterestIncome() {
        return netInterestIncome;
    }

    public String getInterestIncome() {
        return interestIncome;
    }

    public String getInterestExpense() {
        return interestExpense;
    }

    public String getNonInterestIncome() {
        return nonInterestIncome;
    }

    public String getOtherNonOperatingIncome() {
        return otherNonOperatingIncome;
    }

    public String getDepreciation() {
        return depreciation;
    }

    public String getDepreciationAndAmortization() {
        return depreciationAndAmortization;
    }

    public String getIncomeBeforeTax() {
        return incomeBeforeTax;
    }

    public String getIncomeTaxExpense() {
        return incomeTaxExpense;
    }

    public String getInterestAndDebtExpense() {
        return interestAndDebtExpense;
    }

    public String getNetIncomeFromContinuingOperations() {
        return netIncomeFromContinuingOperations;
    }

    public String getComprehensiveIncomeNetOfTax() {
        return comprehensiveIncomeNetOfTax;
    }

    public String getEbit() {
        return ebit;
    }

    public String getEbitda() {
        return ebitda;
    }

    public String getNetIncome() {
        return netIncome;
    }

    @Override
    public String toString() {
        return "IncomeStatement{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedCurrency='" + reportedCurrency + '\'' +
                ", grossProfit='" + grossProfit + '\'' +
                ", totalRevenue='" + totalRevenue + '\'' +
                ", costOfRevenue='" + costOfRevenue + '\'' +
                ", costofGoodsAndServicesSold='" + costofGoodsAndServicesSold + '\'' +
                ", operatingIncome='" + operatingIncome + '\'' +
                ", sellingGeneralAndAdministrative='" + sellingGeneralAndAdministrative + '\'' +
                ", researchAndDevelopment='" + researchAndDevelopment + '\'' +
                ", operatingExpenses='" + operatingExpenses + '\'' +
                ", investmentIncomeNet='" + investmentIncomeNet + '\'' +
                ", netInterestIncome='" + netInterestIncome + '\'' +
                ", interestIncome='" + interestIncome + '\'' +
                ", interestExpense='" + interestExpense + '\'' +
                ", nonInterestIncome='" + nonInterestIncome + '\'' +
                ", otherNonOperatingIncome='" + otherNonOperatingIncome + '\'' +
                ", depreciation='" + depreciation + '\'' +
                ", depreciationAndAmortization='" + depreciationAndAmortization + '\'' +
                ", incomeBeforeTax='" + incomeBeforeTax + '\'' +
                ", incomeTaxExpense='" + incomeTaxExpense + '\'' +
                ", interestAndDebtExpense='" + interestAndDebtExpense + '\'' +
                ", netIncomeFromContinuingOperations='" + netIncomeFromContinuingOperations + '\'' +
                ", comprehensiveIncomeNetOfTax='" + comprehensiveIncomeNetOfTax + '\'' +
                ", ebit='" + ebit + '\'' +
                ", ebitda='" + ebitda + '\'' +
                ", netIncome='" + netIncome + '\'' +
                '}';
    }
}
