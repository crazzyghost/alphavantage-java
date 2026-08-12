/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.NoneableLong;
import com.squareup.moshi.Json;

/**
 * A single annual or quarterly income statement for a company, as filed with
 * the SEC — revenue through to net income for the period ended
 * {@link #getFiscalDateEnding()}.
 * <p>
 * Every monetary figure is a raw, as-reported amount in {@link #getReportedCurrency()},
 * not normalized or scaled by Alpha Vantage. A line item the company did not
 * report for the period comes back as {@code null} rather than zero; see
 * {@link NoneableLong} for why that distinction survives JSON parsing.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
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

    /**
     * Returns the closing date of the fiscal period this statement covers.
     *
     * @return the fiscal period end date, in {@code yyyy-MM-dd} form
     */
    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    /**
     * Returns the ISO currency code this statement's monetary figures are
     * stated in, for example {@code USD}.
     *
     * @return the reporting currency code
     */
    public String getReportedCurrency() {
        return reportedCurrency;
    }

    /**
     * Returns gross profit: total revenue minus cost of revenue. {@code null}
     * if not reported for the period (see {@link NoneableLong}).
     *
     * @return gross profit, in the reporting currency
     */
    public Long getGrossProfit() {
        return grossProfit;
    }

    /**
     * Returns total revenue recognized for the period. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return total revenue, in the reporting currency
     */
    public Long getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Returns the cost directly attributable to producing the revenue
     * reported for the period. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return cost of revenue, in the reporting currency
     */
    public Long getCostOfRevenue() {
        return costOfRevenue;
    }

    /**
     * Returns the cost of goods and services sold, a narrower cost measure
     * than {@link #getCostOfRevenue()} that excludes some indirect costs.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return cost of goods and services sold, in the reporting currency
     */
    public Long getCostofGoodsAndServicesSold() {
        return costofGoodsAndServicesSold;
    }

    /**
     * Returns operating income: gross profit minus operating expenses, before
     * interest and taxes. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return operating income, in the reporting currency
     */
    public Long getOperatingIncome() {
        return operatingIncome;
    }

    /**
     * Returns selling, general and administrative expense. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return selling, general and administrative expense, in the reporting
     *         currency
     */
    public Long getSellingGeneralAndAdministrative() {
        return sellingGeneralAndAdministrative;
    }

    /**
     * Returns research and development expense. {@code null} if not reported
     * for the period (see {@link NoneableLong}).
     *
     * @return research and development expense, in the reporting currency
     */
    public Long getResearchAndDevelopment() {
        return researchAndDevelopment;
    }

    /**
     * Returns total operating expenses for the period. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return operating expenses, in the reporting currency
     */
    public Long getOperatingExpenses() {
        return operatingExpenses;
    }

    /**
     * Returns net income earned on investments, outside of core operations.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return net investment income, in the reporting currency
     */
    public Long getInvestmentIncomeNet() {
        return investmentIncomeNet;
    }

    /**
     * Returns interest income minus interest expense. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return net interest income, in the reporting currency
     */
    public Long getNetInterestIncome() {
        return netInterestIncome;
    }

    /**
     * Returns gross interest income earned for the period. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return interest income, in the reporting currency
     */
    public Long getInterestIncome() {
        return interestIncome;
    }

    /**
     * Returns gross interest expense incurred for the period. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return interest expense, in the reporting currency
     */
    public Long getInterestExpense() {
        return interestExpense;
    }

    /**
     * Returns income earned from sources other than interest, typically fees
     * and commissions. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return non-interest income, in the reporting currency
     */
    public Long getNonInterestIncome() {
        return nonInterestIncome;
    }

    /**
     * Returns other income earned outside the company's core operations.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return other non-operating income, in the reporting currency
     */
    public Long getOtherNonOperatingIncome() {
        return otherNonOperatingIncome;
    }

    /**
     * Returns depreciation expense for the period. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return depreciation expense, in the reporting currency
     */
    public Long getDepreciation() {
        return depreciation;
    }

    /**
     * Returns combined depreciation and amortization expense for the period.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return depreciation and amortization expense, in the reporting
     *         currency
     */
    public Long getDepreciationAndAmortization() {
        return depreciationAndAmortization;
    }

    /**
     * Returns pre-tax income: income before income tax expense is deducted.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return income before tax, in the reporting currency
     */
    public Long getIncomeBeforeTax() {
        return incomeBeforeTax;
    }

    /**
     * Returns income tax expense for the period. {@code null} if not reported
     * for the period (see {@link NoneableLong}).
     *
     * @return income tax expense, in the reporting currency
     */
    public Long getIncomeTaxExpense() {
        return incomeTaxExpense;
    }

    /**
     * Returns combined interest and debt-related expense for the period.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return interest and debt expense, in the reporting currency
     */
    public Long getInterestAndDebtExpense() {
        return interestAndDebtExpense;
    }

    /**
     * Returns net income from continuing operations, excluding discontinued
     * operations. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return net income from continuing operations, in the reporting
     *         currency
     */
    public Long getNetIncomeFromContinuingOperations() {
        return netIncomeFromContinuingOperations;
    }

    /**
     * Returns comprehensive income net of tax: net income plus other
     * comprehensive income items such as unrealized gains and losses.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return comprehensive income net of tax, in the reporting currency
     */
    public Long getComprehensiveIncomeNetOfTax() {
        return comprehensiveIncomeNetOfTax;
    }

    /**
     * Returns earnings before interest and taxes (EBIT). {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return EBIT, in the reporting currency
     */
    public Long getEbit() {
        return ebit;
    }

    /**
     * Returns earnings before interest, taxes, depreciation and amortization
     * (EBITDA). {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return EBITDA, in the reporting currency
     */
    public Long getEbitda() {
        return ebitda;
    }

    /**
     * Returns net income: the company's bottom-line profit or loss for the
     * period. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return net income, in the reporting currency
     */
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
