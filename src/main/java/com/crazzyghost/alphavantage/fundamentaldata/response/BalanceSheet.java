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

import com.squareup.moshi.Json;

public class BalanceSheet {
    @Json(name = "fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name = "reportedCurrency")
    private String reportedCurrency;
    @Json(name = "totalAssets")
    private String totalAssets;
    @Json(name = "totalCurrentAssets")
    private String totalCurrentAssets;
    @Json(name = "cashAndCashEquivalentsAtCarryingValue")
    private String cashAndCashEquivalentsAtCarryingValue;
    @Json(name = "cashAndShortTermInvestments")
    private String cashAndShortTermInvestments;
    @Json(name = "inventory")
    private String inventory;
    @Json(name = "currentNetReceivables")
    private String currentNetReceivables;
    @Json(name = "totalNonCurrentAssets")
    private String totalNonCurrentAssets;
    @Json(name = "propertyPlantEquipment")
    private String propertyPlantEquipment;
    @Json(name = "intangibleAssets")
    private String intangibleAssets;
    @Json(name = "intangibleAssetsExcludingGoodwill")
    private String intangibleAssetsExcludingGoodwill;
    @Json(name = "goodwill")
    private String goodWill;
    @Json(name = "investments")
    private String investments;
    @Json(name = "longTermInvestments")
    private String longTermInvestments;
    @Json(name = "shortTermInvestments")
    private String shortTermInvestments;
    @Json(name = "otherCurrentAssets")
    private String otherCurrentAssets;
    @Json(name = "otherNonCurrrentAssets")
    private String otherNonCurrentAssets;
    @Json(name = "totalLiabilities")
    private String totalLiabilities;
    @Json(name = "totalCurrentLiabilities")
    private String totalCurrentLiabilities;
    @Json(name = "currentAccountsPayable")
    private String currentAccountsPayable;
    @Json(name = "deferredRevenue")
    private String deferredRevenue;
    @Json(name = "currentDebt")
    private String currentDebt;
    @Json(name = "shortTermDebt")
    private String shortTermDebt;
    @Json(name = "totalNonCurrentLiabilities")
    private String totalNonCurrentLiabilities;
    @Json(name = "capitalLeaseObligations")
    private String capitalLeaseObligations;
    @Json(name = "longTermDebt")
    private String longTermDebt;
    @Json(name = "currentLongTermDebt")
    private String currentLongTermDebt;
    @Json(name = "longTermDebtNoncurrent")
    private String longTermDebtNonCurrent;
    @Json(name = "shortLongTermDebtTotal")
    private String shortLongTermDebtTotal;
    @Json(name = "otherCurrentLiabilities")
    private String otherCurrentLiabilities;
    @Json(name = "otherNonCurrentLiabilities")
    private String otherNonCurrentLiabilities;
    @Json(name = "totalShareholderEquity")
    private String totalShareholderEquity;
    @Json(name = "treasuryStock")
    private String treasuryStock;
    @Json(name = "retainedEarnings")
    private String retainedEarnings;
    @Json(name = "commonStock")
    private String commonStock;
    @Json(name = "commonStockSharesOutstanding")
    private String commonStockSharesOutstanding;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public String getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    public String getCashAndCashEquivalentsAtCarryingValue() {
        return cashAndCashEquivalentsAtCarryingValue;
    }

    public String getCashAndShortTermInvestments() {
        return cashAndShortTermInvestments;
    }

    public String getInventory() {
        return inventory;
    }

    public String getCurrentNetReceivables() {
        return currentNetReceivables;
    }

    public String getTotalNonCurrentAssets() {
        return totalNonCurrentAssets;
    }

    public String getPropertyPlantEquipment() {
        return propertyPlantEquipment;
    }

    public String getIntangibleAssets() {
        return intangibleAssets;
    }

    public String getIntangibleAssetsExcludingGoodwill() {
        return intangibleAssetsExcludingGoodwill;
    }

    public String getGoodWill() {
        return goodWill;
    }

    public String getInvestments() {
        return investments;
    }

    public String getLongTermInvestments() {
        return longTermInvestments;
    }

    public String getShortTermInvestments() {
        return shortTermInvestments;
    }

    public String getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public String getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }

    public String getTotalLiabilities() {
        return totalLiabilities;
    }

    public String getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    public String getCurrentAccountsPayable() {
        return currentAccountsPayable;
    }

    public String getDeferredRevenue() {
        return deferredRevenue;
    }

    public String getCurrentDebt() {
        return currentDebt;
    }

    public String getShortTermDebt() {
        return shortTermDebt;
    }

    public String getTotalNonCurrentLiabilities() {
        return totalNonCurrentLiabilities;
    }

    public String getCapitalLeaseObligations() {
        return capitalLeaseObligations;
    }

    public String getLongTermDebt() {
        return longTermDebt;
    }

    public String getCurrentLongTermDebt() {
        return currentLongTermDebt;
    }

    public String getLongTermDebtNonCurrent() {
        return longTermDebtNonCurrent;
    }

    public String getShortLongTermDebtTotal() {
        return shortLongTermDebtTotal;
    }

    public String getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    public String getOtherNonCurrentLiabilities() {
        return otherNonCurrentLiabilities;
    }

    public String getTotalShareholderEquity() {
        return totalShareholderEquity;
    }

    public String getTreasuryStock() {
        return treasuryStock;
    }

    public String getRetainedEarnings() {
        return retainedEarnings;
    }

    public String getCommonStock() {
        return commonStock;
    }

    public String getCommonStockSharesOutstanding() {
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
