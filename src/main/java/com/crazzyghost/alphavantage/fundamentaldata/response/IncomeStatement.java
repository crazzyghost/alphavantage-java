package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.NoneableLong;
import com.squareup.moshi.Json;

public class IncomeStatement {

    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedCurrency")
    private String reportedCurrency;
    @Json(name="grossProfit")
    @NoneableLong
    private Long grossProfit;
    @Json(name="totalRevenue")
    @NoneableLong
    private Long  totalRevenue;
    @Json(name="costOfRevenue")
    @NoneableLong
    private Long  costOfRevenue;
    @Json(name="costofGoodsAndServicesSold")
    @NoneableLong
    private Long  costofGoodsAndServicesSold;
    @Json(name="operatingIncome")
    @NoneableLong
    private Long  operatingIncome;
    @Json(name="sellingGeneralAndAdministrative")
    @NoneableLong
    private Long  sellingGeneralAndAdministrative;
    @Json(name="researchAndDevelopment")
    @NoneableLong
    private Long  researchAndDevelopment;
    @Json(name="operatingExpenses")
    @NoneableLong
    private Long  operatingExpenses;
    @Json(name="investmentIncomeNet")
    @NoneableLong
    private Long  investmentIncomeNet;
    @Json(name="netInterestIncome")
    @NoneableLong
    private Long  netInterestIncome;
    @Json(name="interestIncome")
    @NoneableLong
    private Long  interestIncome;
    @Json(name="interestExpense")
    @NoneableLong
    private Long  interestExpense;
    @Json(name="nonInterestIncome")
    @NoneableLong
    private Long  nonInterestIncome;
    @Json(name="otherNonOperatingIncome")
    @NoneableLong
    private Long  otherNonOperatingIncome;
    @Json(name="depreciation")
    @NoneableLong
    private Long  depreciation;
    @Json(name="depreciationAndAmortization")
    @NoneableLong
    private Long  depreciationAndAmortization;
    @Json(name="incomeBeforeTax")
    @NoneableLong
    private Long  incomeBeforeTax;
    @Json(name="incomeTaxExpense")
    @NoneableLong
    private Long  incomeTaxExpense;
    @Json(name="interestAndDebtExpense")
    @NoneableLong
    private Long  interestAndDebtExpense;
    @Json(name="netIncomeFromContinuingOperations")
    @NoneableLong
    private Long  netIncomeFromContinuingOperations;
    @Json(name="comprehensiveIncomeNetOfTax")
    @NoneableLong
    private Long  comprehensiveIncomeNetOfTax;
    @Json(name="ebit")
    @NoneableLong
    private Long  ebit;
    @Json(name="ebitda")
    @NoneableLong
    private Long  ebitda;
    @Json(name="netIncome")
    @NoneableLong
    private Long  netIncome;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public Long getGrossProfit() {
        return grossProfit;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public Long getCostOfRevenue() {
        return costOfRevenue;
    }

    public Long getCostofGoodsAndServicesSold() {
        return costofGoodsAndServicesSold;
    }

    public Long getOperatingIncome() {
        return operatingIncome;
    }

    public Long getSellingGeneralAndAdministrative() {
        return sellingGeneralAndAdministrative;
    }

    public Long getResearchAndDevelopment() {
        return researchAndDevelopment;
    }

    public Long getOperatingExpenses() {
        return operatingExpenses;
    }

    public Long getInvestmentIncomeNet() {
        return investmentIncomeNet;
    }

    public Long getNetInterestIncome() {
        return netInterestIncome;
    }

    public Long getInterestIncome() {
        return interestIncome;
    }

    public Long getInterestExpense() {
        return interestExpense;
    }

    public Long getNonInterestIncome() {
        return nonInterestIncome;
    }

    public Long getOtherNonOperatingIncome() {
        return otherNonOperatingIncome;
    }

    public Long getDepreciation() {
        return depreciation;
    }

    public Long getDepreciationAndAmortization() {
        return depreciationAndAmortization;
    }

    public Long getIncomeBeforeTax() {
        return incomeBeforeTax;
    }

    public Long getIncomeTaxExpense() {
        return incomeTaxExpense;
    }

    public Long getInterestAndDebtExpense() {
        return interestAndDebtExpense;
    }

    public Long getNetIncomeFromContinuingOperations() {
        return netIncomeFromContinuingOperations;
    }

    public Long getComprehensiveIncomeNetOfTax() {
        return comprehensiveIncomeNetOfTax;
    }

    public Long getEbit() {
        return ebit;
    }

    public Long getEbitda() {
        return ebitda;
    }

    public Long getNetIncome() {
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
