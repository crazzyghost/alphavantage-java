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
 * A single annual or quarterly balance sheet report for a company, as filed
 * with the SEC — assets, liabilities and shareholder equity as of
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
public class BalanceSheet {
    @Json(name = "fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name = "reportedCurrency")
    private String reportedCurrency;
    @Json(name = "totalAssets")
    @NoneableLong
    private Long totalAssets;
    @Json(name = "totalCurrentAssets")
    @NoneableLong
    private Long totalCurrentAssets;
    @Json(name = "cashAndCashEquivalentsAtCarryingValue")
    @NoneableLong
    private Long cashAndCashEquivalentsAtCarryingValue;
    @Json(name = "cashAndShortTermInvestments")
    @NoneableLong
    private Long cashAndShortTermInvestments;
    @Json(name = "inventory")
    @NoneableLong
    private Long inventory;
    @Json(name = "currentNetReceivables")
    @NoneableLong
    private Long currentNetReceivables;
    @Json(name = "totalNonCurrentAssets")
    @NoneableLong
    private Long totalNonCurrentAssets;
    @Json(name = "propertyPlantEquipment")
    @NoneableLong
    private Long propertyPlantEquipment;
    @Json(name = "accumulatedDepreciationAmortizationPPE")
    @NoneableLong
    private Long accumulatedDepreciationAmortizationPPE;
    @Json(name = "intangibleAssets")
    @NoneableLong
    private Long intangibleAssets;
    @Json(name = "intangibleAssetsExcludingGoodwill")
    @NoneableLong
    private Long intangibleAssetsExcludingGoodwill;
    @Json(name = "goodwill")
    @NoneableLong
    private Long goodWill;
    @Json(name = "investments")
    @NoneableLong
    private Long investments;
    @Json(name = "longTermInvestments")
    @NoneableLong
    private Long longTermInvestments;
    @Json(name = "shortTermInvestments")
    @NoneableLong
    private Long shortTermInvestments;
    @Json(name = "otherCurrentAssets")
    @NoneableLong
    private Long otherCurrentAssets;
    @Json(name = "otherNonCurrentAssets")
    @NoneableLong
    private Long otherNonCurrentAssets;
    @Json(name = "totalLiabilities")
    @NoneableLong
    private Long totalLiabilities;
    @Json(name = "totalCurrentLiabilities")
    @NoneableLong
    private Long totalCurrentLiabilities;
    @Json(name = "currentAccountsPayable")
    @NoneableLong
    private Long currentAccountsPayable;
    @Json(name = "deferredRevenue")
    @NoneableLong
    private Long deferredRevenue;
    @Json(name = "currentDebt")
    @NoneableLong
    private Long currentDebt;
    @Json(name = "shortTermDebt")
    @NoneableLong
    private Long shortTermDebt;
    @Json(name = "totalNonCurrentLiabilities")
    @NoneableLong
    private Long totalNonCurrentLiabilities;
    @Json(name = "capitalLeaseObligations")
    @NoneableLong
    private Long capitalLeaseObligations;
    @Json(name = "longTermDebt")
    @NoneableLong
    private Long longTermDebt;
    @Json(name = "currentLongTermDebt")
    @NoneableLong
    private Long currentLongTermDebt;
    @Json(name = "longTermDebtNoncurrent")
    @NoneableLong
    private Long longTermDebtNonCurrent;
    @Json(name = "shortLongTermDebtTotal")
    @NoneableLong
    private Long shortLongTermDebtTotal;
    @Json(name = "otherCurrentLiabilities")
    @NoneableLong
    private Long otherCurrentLiabilities;
    @Json(name = "otherNonCurrentLiabilities")
    @NoneableLong
    private Long otherNonCurrentLiabilities;
    @Json(name = "totalShareholderEquity")
    @NoneableLong
    private Long totalShareholderEquity;
    @Json(name = "treasuryStock")
    @NoneableLong
    private Long treasuryStock;
    @Json(name = "retainedEarnings")
    @NoneableLong
    private Long retainedEarnings;
    @Json(name = "commonStock")
    @NoneableLong
    private Long commonStock;
    @Json(name = "commonStockSharesOutstanding")
    @NoneableLong
    private Long commonStockSharesOutstanding;

    /**
     * Returns the closing date of the fiscal period this report covers.
     *
     * @return the fiscal period end date, in {@code yyyy-MM-dd} form
     */
    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    /**
     * Returns the ISO currency code this report's monetary figures are stated
     * in, for example {@code USD}.
     *
     * @return the reporting currency code
     */
    public String getReportedCurrency() {
        return reportedCurrency;
    }

    /**
     * Returns total assets, the sum of all asset line items on the balance sheet.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return total assets, in the reporting currency
     */
    public Long getTotalAssets() {
        return totalAssets;
    }

    /**
     * Returns total current assets: assets expected to be converted to cash,
     * sold, or consumed within one year. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return total current assets, in the reporting currency
     */
    public Long getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    /**
     * Returns cash and cash equivalents at their balance-sheet carrying value.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return cash and cash equivalents, in the reporting currency
     */
    public Long getCashAndCashEquivalentsAtCarryingValue() {
        return cashAndCashEquivalentsAtCarryingValue;
    }

    /**
     * Returns the combined balance of cash, cash equivalents and short-term
     * investments. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return cash and short-term investments, in the reporting currency
     */
    public Long getCashAndShortTermInvestments() {
        return cashAndShortTermInvestments;
    }

    /**
     * Returns the value of inventory held for sale or for use in production.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return inventory value, in the reporting currency
     */
    public Long getInventory() {
        return inventory;
    }

    /**
     * Returns net accounts receivable due within one year, after the allowance
     * for doubtful accounts. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return current net receivables, in the reporting currency
     */
    public Long getCurrentNetReceivables() {
        return currentNetReceivables;
    }

    /**
     * Returns total non-current (long-term) assets: assets not expected to be
     * converted to cash within one year. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return total non-current assets, in the reporting currency
     */
    public Long getTotalNonCurrentAssets() {
        return totalNonCurrentAssets;
    }

    /**
     * Returns the gross book value of property, plant and equipment, before
     * accumulated depreciation. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return property, plant and equipment, in the reporting currency
     */
    public Long getPropertyPlantEquipment() {
        return propertyPlantEquipment;
    }

    /**
     * Returns accumulated depreciation and amortization charged against
     * property, plant and equipment to date. {@code null} if not reported for
     * the period (see {@link NoneableLong}).
     *
     * @return accumulated depreciation and amortization of PP&amp;E, in the
     *         reporting currency
     */
    public Long getAccumulatedDepreciationAmortizationPPE() {
        return accumulatedDepreciationAmortizationPPE;
    }

    /**
     * Returns total intangible assets, including goodwill. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return intangible assets, in the reporting currency
     */
    public Long getIntangibleAssets() {
        return intangibleAssets;
    }

    /**
     * Returns intangible assets excluding goodwill, for example patents,
     * trademarks and licenses. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return intangible assets excluding goodwill, in the reporting currency
     */
    public Long getIntangibleAssetsExcludingGoodwill() {
        return intangibleAssetsExcludingGoodwill;
    }

    /**
     * Returns goodwill: the premium paid over identifiable fair value in past
     * acquisitions. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return goodwill, in the reporting currency
     */
    public Long getGoodWill() {
        return goodWill;
    }

    /**
     * Returns total investments, combining both short-term and long-term
     * holdings. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return total investments, in the reporting currency
     */
    public Long getInvestments() {
        return investments;
    }

    /**
     * Returns investments the company intends to hold for more than one year.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return long-term investments, in the reporting currency
     */
    public Long getLongTermInvestments() {
        return longTermInvestments;
    }

    /**
     * Returns investments expected to be liquidated within one year.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return short-term investments, in the reporting currency
     */
    public Long getShortTermInvestments() {
        return shortTermInvestments;
    }

    /**
     * Returns current assets not broken out into one of the other
     * current-asset line items. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return other current assets, in the reporting currency
     */
    public Long getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    /**
     * Returns non-current assets not broken out into one of the other
     * non-current-asset line items. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return other non-current assets, in the reporting currency
     */
    public Long getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }

    /**
     * Returns total liabilities, the sum of all liability line items on the
     * balance sheet. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return total liabilities, in the reporting currency
     */
    public Long getTotalLiabilities() {
        return totalLiabilities;
    }

    /**
     * Returns total current liabilities: obligations due within one year.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return total current liabilities, in the reporting currency
     */
    public Long getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    /**
     * Returns amounts owed to suppliers and vendors, due within one year.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return current accounts payable, in the reporting currency
     */
    public Long getCurrentAccountsPayable() {
        return currentAccountsPayable;
    }

    /**
     * Returns payments received in advance for goods or services not yet
     * delivered or performed. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return deferred revenue, in the reporting currency
     */
    public Long getDeferredRevenue() {
        return deferredRevenue;
    }

    /**
     * Returns the portion of total debt, of any original maturity, classified
     * as due within one year. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return current debt, in the reporting currency
     */
    public Long getCurrentDebt() {
        return currentDebt;
    }

    /**
     * Returns debt originally issued with a maturity of one year or less.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return short-term debt, in the reporting currency
     */
    public Long getShortTermDebt() {
        return shortTermDebt;
    }

    /**
     * Returns total non-current (long-term) liabilities: obligations not due
     * within one year. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return total non-current liabilities, in the reporting currency
     */
    public Long getTotalNonCurrentLiabilities() {
        return totalNonCurrentLiabilities;
    }

    /**
     * Returns the company's outstanding obligations under capital leases.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return capital lease obligations, in the reporting currency
     */
    public Long getCapitalLeaseObligations() {
        return capitalLeaseObligations;
    }

    /**
     * Returns debt with a maturity beyond one year. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return long-term debt, in the reporting currency
     */
    public Long getLongTermDebt() {
        return longTermDebt;
    }

    /**
     * Returns the portion of long-term debt due within the next year.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return current portion of long-term debt, in the reporting currency
     */
    public Long getCurrentLongTermDebt() {
        return currentLongTermDebt;
    }

    /**
     * Returns the portion of long-term debt not due within the next year.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return non-current portion of long-term debt, in the reporting currency
     */
    public Long getLongTermDebtNonCurrent() {
        return longTermDebtNonCurrent;
    }

    /**
     * Returns the combined balance of short-term debt and the current portion
     * of long-term debt. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return combined short- and current long-term debt, in the reporting
     *         currency
     */
    public Long getShortLongTermDebtTotal() {
        return shortLongTermDebtTotal;
    }

    /**
     * Returns current liabilities not broken out into one of the other
     * current-liability line items. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return other current liabilities, in the reporting currency
     */
    public Long getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    /**
     * Returns non-current liabilities not broken out into one of the other
     * non-current-liability line items. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return other non-current liabilities, in the reporting currency
     */
    public Long getOtherNonCurrentLiabilities() {
        return otherNonCurrentLiabilities;
    }

    /**
     * Returns total shareholder equity: total assets minus total liabilities.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return total shareholder equity, in the reporting currency
     */
    public Long getTotalShareholderEquity() {
        return totalShareholderEquity;
    }

    /**
     * Returns the value of the company's own shares it has repurchased and
     * holds in treasury. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return treasury stock, in the reporting currency
     */
    public Long getTreasuryStock() {
        return treasuryStock;
    }

    /**
     * Returns cumulative net income retained in the business rather than paid
     * out as dividends. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return retained earnings, in the reporting currency
     */
    public Long getRetainedEarnings() {
        return retainedEarnings;
    }

    /**
     * Returns the par or stated value of issued common stock. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return common stock value, in the reporting currency
     */
    public Long getCommonStock() {
        return commonStock;
    }

    /**
     * Returns the number of common shares currently outstanding. {@code null}
     * if not reported for the period (see {@link NoneableLong}).
     *
     * @return common shares outstanding
     */
    public Long getCommonStockSharesOutstanding() {
        return commonStockSharesOutstanding;
    }

    @Override
    public String toString() {
        return "BalanceSheet{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedCurrency='" + reportedCurrency + '\'' +
                ", totalAssets='" + totalAssets + '\'' +
                ", totalCurrentAssets='" + totalCurrentAssets + '\'' +
                ", cashAndCashEquivalentsAtCarryingValue='" + cashAndCashEquivalentsAtCarryingValue + '\'' +
                ", cashAndShortTermInvestments='" + cashAndShortTermInvestments + '\'' +
                ", inventory='" + inventory + '\'' +
                ", currentNetReceivables='" + currentNetReceivables + '\'' +
                ", totalNonCurrentAssets='" + totalNonCurrentAssets + '\'' +
                ", propertyPlantEquipment='" + propertyPlantEquipment + '\'' +
                ", intangibleAssets='" + intangibleAssets + '\'' +
                ", intangibleAssetsExcludingGoodwill='" + intangibleAssetsExcludingGoodwill + '\'' +
                ", goodWill='" + goodWill + '\'' +
                ", investments='" + investments + '\'' +
                ", longTermInvestments='" + longTermInvestments + '\'' +
                ", shortTermInvestments='" + shortTermInvestments + '\'' +
                ", otherCurrentAssets='" + otherCurrentAssets + '\'' +
                ", otherNonCurrentAssets='" + otherNonCurrentAssets + '\'' +
                ", totalLiabilities='" + totalLiabilities + '\'' +
                ", totalCurrentLiabilities='" + totalCurrentLiabilities + '\'' +
                ", currentAccountsPayable='" + currentAccountsPayable + '\'' +
                ", deferredRevenue='" + deferredRevenue + '\'' +
                ", currentDebt='" + currentDebt + '\'' +
                ", shortTermDebt='" + shortTermDebt + '\'' +
                ", totalNonCurrentLiabilities='" + totalNonCurrentLiabilities + '\'' +
                ", capitalLeaseObligations='" + capitalLeaseObligations + '\'' +
                ", longTermDebt='" + longTermDebt + '\'' +
                ", currentLongTermDebt='" + currentLongTermDebt + '\'' +
                ", longTermDebtNonCurrent='" + longTermDebtNonCurrent + '\'' +
                ", shortLongTermDebtTotal='" + shortLongTermDebtTotal + '\'' +
                ", otherCurrentLiabilities='" + otherCurrentLiabilities + '\'' +
                ", otherNonCurrentLiabilities='" + otherNonCurrentLiabilities + '\'' +
                ", totalShareholderEquity='" + totalShareholderEquity + '\'' +
                ", treasuryStock='" + treasuryStock + '\'' +
                ", retainedEarnings='" + retainedEarnings + '\'' +
                ", commonStock='" + commonStock + '\'' +
                ", commonStockSharesOutstanding='" + commonStockSharesOutstanding + '\'' +
                '}';
    }
}
