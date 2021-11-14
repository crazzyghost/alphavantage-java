package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.Noneable;
import com.squareup.moshi.Json;

public class IncomeStatement {

    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedCurrency")
    private String reportedCurrency;
    @Json(name="grossProfit")
    @Noneable
    private Long grossProfit;
    @Json(name="totalRevenue")
    @Noneable
    private Long  totalRevenue;
    @Json(name="costOfRevenue")
    @Noneable
    private Long  costOfRevenue;
    @Json(name="costofGoodsAndServicesSold")
    @Noneable
    private Long  costofGoodsAndServicesSold;
    @Json(name="operatingIncome")
    @Noneable
    private Long  operatingIncome;
    @Json(name="sellingGeneralAndAdministrative")
    @Noneable
    private Long  sellingGeneralAndAdministrative;
    @Json(name="researchAndDevelopment")
    @Noneable
    private Long  researchAndDevelopment;
    @Json(name="operatingExpenses")
    @Noneable
    private Long  operatingExpenses;
    @Json(name="investmentIncomeNet")
    @Noneable
    private Long  investmentIncomeNet;
    @Json(name="netInterestIncome")
    @Noneable
    private Long  netInterestIncome;
    @Json(name="interestIncome")
    @Noneable
    private Long  interestIncome;
    @Json(name="interestExpense")
    @Noneable
    private Long  interestExpense;
    @Json(name="nonInterestIncome")
    @Noneable
    private Long  nonInterestIncome;
    @Json(name="otherNonOperatingIncome")
    @Noneable
    private Long  otherNonOperatingIncome;
    @Json(name="depreciation")
    @Noneable
    private Long  depreciation;
    @Json(name="depreciationAndAmortization")
    @Noneable
    private Long  depreciationAndAmortization;
    @Json(name="incomeBeforeTax")
    @Noneable
    private Long  incomeBeforeTax;
    @Json(name="incomeTaxExpense")
    @Noneable
    private Long  incomeTaxExpense;
    @Json(name="interestAndDebtExpense")
    @Noneable
    private Long  interestAndDebtExpense;
    @Json(name="netIncomeFromContinuingOperations")
    @Noneable
    private Long  netIncomeFromContinuingOperations;
    @Json(name="comprehensiveIncomeNetOfTax")
    @Noneable
    private Long  comprehensiveIncomeNetOfTax;
    @Json(name="ebit")
    @Noneable
    private Long  ebit;
    @Json(name="ebitda")
    @Noneable
    private Long  ebitda;
    @Json(name="netIncome")
    @Noneable
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
