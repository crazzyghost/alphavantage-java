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

import com.crazzyghost.alphavantage.parser.Noneable;
import com.squareup.moshi.Json;

public class BalanceSheet {
    @Json(name = "fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name = "reportedCurrency")
    private String reportedCurrency;
    @Json(name = "totalAssets")
    @Noneable
    private Long totalAssets;
    @Json(name = "totalCurrentAssets")
    @Noneable
    private Long totalCurrentAssets;
    @Json(name = "cashAndCashEquivalentsAtCarryingValue")
    @Noneable
    private Long cashAndCashEquivalentsAtCarryingValue;
    @Json(name = "cashAndShortTermInvestments")
    @Noneable
    private Long cashAndShortTermInvestments;
    @Json(name = "inventory")
    @Noneable
    private Long inventory;
    @Json(name = "currentNetReceivables")
    @Noneable
    private Long currentNetReceivables;
    @Json(name = "totalNonCurrentAssets")
    @Noneable
    private Long totalNonCurrentAssets;
    @Json(name = "propertyPlantEquipment")
    @Noneable
    private Long propertyPlantEquipment;
    @Json(name = "accumulatedDepreciationAmortizationPPE")
    @Noneable
    private Long accumulatedDepreciationAmortizationPPE;
    @Json(name = "intangibleAssets")
    @Noneable
    private Long intangibleAssets;
    @Json(name = "intangibleAssetsExcludingGoodwill")
    @Noneable
    private Long intangibleAssetsExcludingGoodwill;
    @Json(name = "goodwill")
    @Noneable
    private Long goodWill;
    @Json(name = "investments")
    @Noneable
    private Long investments;
    @Json(name = "longTermInvestments")
    @Noneable
    private Long longTermInvestments;
    @Json(name = "shortTermInvestments")
    @Noneable
    private Long shortTermInvestments;
    @Json(name = "otherCurrentAssets")
    @Noneable
    private Long otherCurrentAssets;
    @Json(name = "otherNonCurrrentAssets")
    @Noneable
    private Long otherNonCurrentAssets;
    @Json(name = "totalLiabilities")
    @Noneable
    private Long totalLiabilities;
    @Json(name = "totalCurrentLiabilities")
    @Noneable
    private Long totalCurrentLiabilities;
    @Json(name = "currentAccountsPayable")
    @Noneable
    private Long currentAccountsPayable;
    @Json(name = "deferredRevenue")
    @Noneable
    private Long deferredRevenue;
    @Json(name = "currentDebt")
    @Noneable
    private Long currentDebt;
    @Json(name = "shortTermDebt")
    @Noneable
    private Long shortTermDebt;
    @Json(name = "totalNonCurrentLiabilities")
    @Noneable
    private Long totalNonCurrentLiabilities;
    @Json(name = "capitalLeaseObligations")
    @Noneable
    private Long capitalLeaseObligations;
    @Json(name = "longTermDebt")
    @Noneable
    private Long longTermDebt;
    @Json(name = "currentLongTermDebt")
    @Noneable
    private Long currentLongTermDebt;
    @Json(name = "longTermDebtNoncurrent")
    @Noneable
    private Long longTermDebtNonCurrent;
    @Json(name = "shortLongTermDebtTotal")
    @Noneable
    private Long shortLongTermDebtTotal;
    @Json(name = "otherCurrentLiabilities")
    @Noneable
    private Long otherCurrentLiabilities;
    @Json(name = "otherNonCurrentLiabilities")
    @Noneable
    private Long otherNonCurrentLiabilities;
    @Json(name = "totalShareholderEquity")
    @Noneable
    private Long totalShareholderEquity;
    @Json(name = "treasuryStock")
    @Noneable
    private Long treasuryStock;
    @Json(name = "retainedEarnings")
    @Noneable
    private Long retainedEarnings;
    @Json(name = "commonStock")
    @Noneable
    private Long commonStock;
    @Json(name = "commonStockSharesOutstanding")
    @Noneable
    private Long commonStockSharesOutstanding;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public Long getTotalAssets() {
        return totalAssets;
    }

    public Long getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    public Long getCashAndCashEquivalentsAtCarryingValue() {
        return cashAndCashEquivalentsAtCarryingValue;
    }

    public Long getCashAndShortTermInvestments() {
        return cashAndShortTermInvestments;
    }

    public Long getInventory() {
        return inventory;
    }

    public Long getCurrentNetReceivables() {
        return currentNetReceivables;
    }

    public Long getTotalNonCurrentAssets() {
        return totalNonCurrentAssets;
    }

    public Long getPropertyPlantEquipment() {
        return propertyPlantEquipment;
    }

    public Long getAccumulatedDepreciationAmortizationPPE() {
        return accumulatedDepreciationAmortizationPPE;
    }

    public Long getIntangibleAssets() {
        return intangibleAssets;
    }

    public Long getIntangibleAssetsExcludingGoodwill() {
        return intangibleAssetsExcludingGoodwill;
    }

    public Long getGoodWill() {
        return goodWill;
    }

    public Long getInvestments() {
        return investments;
    }

    public Long getLongTermInvestments() {
        return longTermInvestments;
    }

    public Long getShortTermInvestments() {
        return shortTermInvestments;
    }

    public Long getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public Long getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }

    public Long getTotalLiabilities() {
        return totalLiabilities;
    }

    public Long getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    public Long getCurrentAccountsPayable() {
        return currentAccountsPayable;
    }

    public Long getDeferredRevenue() {
        return deferredRevenue;
    }

    public Long getCurrentDebt() {
        return currentDebt;
    }

    public Long getShortTermDebt() {
        return shortTermDebt;
    }

    public Long getTotalNonCurrentLiabilities() {
        return totalNonCurrentLiabilities;
    }

    public Long getCapitalLeaseObligations() {
        return capitalLeaseObligations;
    }

    public Long getLongTermDebt() {
        return longTermDebt;
    }

    public Long getCurrentLongTermDebt() {
        return currentLongTermDebt;
    }

    public Long getLongTermDebtNonCurrent() {
        return longTermDebtNonCurrent;
    }

    public Long getShortLongTermDebtTotal() {
        return shortLongTermDebtTotal;
    }

    public Long getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    public Long getOtherNonCurrentLiabilities() {
        return otherNonCurrentLiabilities;
    }

    public Long getTotalShareholderEquity() {
        return totalShareholderEquity;
    }

    public Long getTreasuryStock() {
        return treasuryStock;
    }

    public Long getRetainedEarnings() {
        return retainedEarnings;
    }

    public Long getCommonStock() {
        return commonStock;
    }

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
