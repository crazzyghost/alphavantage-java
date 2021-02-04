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

    private String fiscalEndingDate;
    private String reportedCurrency;
    private String totalAssets;
    private String intangibleAssets;
    private String otherCurrentAssets;
    private String totalLiabilities;
    private String totalShareholderEquity;
    private String deferredLongTermLiabilities;
    private String otherCurrentLiabilities;
    private String commonStock;
    private String retainedEarnings;
    private String otherLiabilities;
    private String goodWill;
    private String otherAssets;
    private String cash;
    private String totalCurrentLiabilities;
    private String shortTermDebt;
    private String currentLongTermDebt;
    private String otherShareholderEquity;
    private String propertyPlantEquipment;
    private String totalCurrentAssets;
    private String longTermInvestments;
    private String netTangibleAsses;
    private String shortTermInvestments;
    private String netReceivables;
    private String longTermDebt;
    private String inventory;
    private String totalPermanentEquity;
    private String additionalPaidInCapital;
    private String commonStockTotalEquity;
    private String preferredStockTotalEquity;
    private String retainedEarningsTotalEquity;
    private String treasuryStock;
    private String accumulatedAmortization;
    @Json(name = "otherNonCurrrentAssets") private String otherNonCurrentAssets;
    private String deferredLongTermAssetCharges;
    private String totalNonCurrentAssets;
    private String capitalLeaseObligations;
    private String totalLongTermDebt;
    private String otherNonCurrentLiabilities;
    private String totalNonCurrentLiabilities;
    private String negativeGoodwill;
    private String warrants;
    private String preferredStockRedeemable;
    private String capitalSurplus;
    private String liabilitiesAndShareholderEquity;
    private String cashAndShortTermInvestments;
    private String accumulatedDepreciation;
    private String commonStockSharesOutstanding;

    public String getFiscalEndingDate() {
        return fiscalEndingDate;
    }

    public String getReportedCurrency() {
        return reportedCurrency;
    }

    public String getTotalAssets() {
        return totalAssets;
    }

    public String getIntangibleAssets() {
        return intangibleAssets;
    }

    public String getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public String getTotalLiabilities() {
        return totalLiabilities;
    }

    public String getTotalShareholderEquity() {
        return totalShareholderEquity;
    }

    public String getDeferredLongTermLiabilities() {
        return deferredLongTermLiabilities;
    }

    public String getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }

    public String getCommonStock() {
        return commonStock;
    }

    public String getRetainedEarnings() {
        return retainedEarnings;
    }

    public String getOtherLiabilities() {
        return otherLiabilities;
    }

    public String getGoodWill() {
        return goodWill;
    }

    public String getOtherAssets() {
        return otherAssets;
    }

    public String getCash() {
        return cash;
    }

    public String getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    public String getShortTermDebt() {
        return shortTermDebt;
    }

    public String getCurrentLongTermDebt() {
        return currentLongTermDebt;
    }

    public String getOtherShareholderEquity() {
        return otherShareholderEquity;
    }

    public String getPropertyPlantEquipment() {
        return propertyPlantEquipment;
    }

    public String getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    public String getLongTermInvestments() {
        return longTermInvestments;
    }

    public String getNetTangibleAsses() {
        return netTangibleAsses;
    }

    public String getShortTermInvestments() {
        return shortTermInvestments;
    }

    public String getNetReceivables() {
        return netReceivables;
    }

    public String getLongTermDebt() {
        return longTermDebt;
    }

    public String getInventory() {
        return inventory;
    }

    public String getTotalPermanentEquity() {
        return totalPermanentEquity;
    }

    public String getAdditionalPaidInCapital() {
        return additionalPaidInCapital;
    }

    public String getCommonStockTotalEquity() {
        return commonStockTotalEquity;
    }

    public String getPreferredStockTotalEquity() {
        return preferredStockTotalEquity;
    }

    public String getRetainedEarningsTotalEquity() {
        return retainedEarningsTotalEquity;
    }

    public String getTreasuryStock() {
        return treasuryStock;
    }

    public String getAccumulatedAmortization() {
        return accumulatedAmortization;
    }

    public String getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }

    public String getDeferredLongTermAssetCharges() {
        return deferredLongTermAssetCharges;
    }

    public String getTotalNonCurrentAssets() {
        return totalNonCurrentAssets;
    }

    public String getCapitalLeaseObligations() {
        return capitalLeaseObligations;
    }

    public String getTotalLongTermDebt() {
        return totalLongTermDebt;
    }

    public String getOtherNonCurrentLiabilities() {
        return otherNonCurrentLiabilities;
    }

    public String getTotalNonCurrentLiabilities() {
        return totalNonCurrentLiabilities;
    }

    public String getNegativeGoodwill() {
        return negativeGoodwill;
    }

    public String getWarrants() {
        return warrants;
    }

    public String getPreferredStockRedeemable() {
        return preferredStockRedeemable;
    }

    public String getCapitalSurplus() {
        return capitalSurplus;
    }

    public String getLiabilitiesAndShareholderEquity() {
        return liabilitiesAndShareholderEquity;
    }

    public String getCashAndShortTermInvestments() {
        return cashAndShortTermInvestments;
    }

    public String getAccumulatedDepreciation() {
        return accumulatedDepreciation;
    }

    public String getCommonStockSharesOutstanding() {
        return commonStockSharesOutstanding;
    }

    @Override
    public String toString() {
        return "BalanceSheetUnit{" +
                "fiscalEndingDate='" + fiscalEndingDate + '\'' +
                ", reportedCurrency='" + reportedCurrency + '\'' +
                ", totalAssets='" + totalAssets + '\'' +
                ", intangibleAssets='" + intangibleAssets + '\'' +
                ", otherCurrentAssets='" + otherCurrentAssets + '\'' +
                ", totalLiabilities='" + totalLiabilities + '\'' +
                ", totalShareholderEquity='" + totalShareholderEquity + '\'' +
                ", deferredLongTermLiabilities='" + deferredLongTermLiabilities + '\'' +
                ", otherCurrentLiabilities='" + otherCurrentLiabilities + '\'' +
                ", commonStock='" + commonStock + '\'' +
                ", retainedEarnings='" + retainedEarnings + '\'' +
                ", otherLiabilities='" + otherLiabilities + '\'' +
                ", goodWill='" + goodWill + '\'' +
                ", otherAssets='" + otherAssets + '\'' +
                ", cash='" + cash + '\'' +
                ", totalCurrentLiabilities='" + totalCurrentLiabilities + '\'' +
                ", shortTermDebt='" + shortTermDebt + '\'' +
                ", currentLongTermDebt='" + currentLongTermDebt + '\'' +
                ", otherShareholderEquity='" + otherShareholderEquity + '\'' +
                ", propertyPlantEquipment='" + propertyPlantEquipment + '\'' +
                ", totalCurrentAssets='" + totalCurrentAssets + '\'' +
                ", longTermInvestments='" + longTermInvestments + '\'' +
                ", netTangibleAsses='" + netTangibleAsses + '\'' +
                ", shortTermInvestments='" + shortTermInvestments + '\'' +
                ", netReceivables='" + netReceivables + '\'' +
                ", longTermDebt='" + longTermDebt + '\'' +
                ", inventory='" + inventory + '\'' +
                ", totalPermanentEquity='" + totalPermanentEquity + '\'' +
                ", additionalPaidInCapital='" + additionalPaidInCapital + '\'' +
                ", commonStockTotalEquity='" + commonStockTotalEquity + '\'' +
                ", preferredStockTotalEquity='" + preferredStockTotalEquity + '\'' +
                ", retainedEarningsTotalEquity='" + retainedEarningsTotalEquity + '\'' +
                ", treasuryStock='" + treasuryStock + '\'' +
                ", accumulatedAmortization='" + accumulatedAmortization + '\'' +
                ", otherNonCurrentAssets='" + otherNonCurrentAssets + '\'' +
                ", deferredLongTermAssetCharges='" + deferredLongTermAssetCharges + '\'' +
                ", totalNonCurrentAssets='" + totalNonCurrentAssets + '\'' +
                ", capitalLeaseObligations='" + capitalLeaseObligations + '\'' +
                ", totalLongTermDebt='" + totalLongTermDebt + '\'' +
                ", otherNonCurrentLiabilities='" + otherNonCurrentLiabilities + '\'' +
                ", totalNonCurrentLiabilities='" + totalNonCurrentLiabilities + '\'' +
                ", negativeGoodwill='" + negativeGoodwill + '\'' +
                ", warrants='" + warrants + '\'' +
                ", preferredStockRedeemable='" + preferredStockRedeemable + '\'' +
                ", capitalSurplus='" + capitalSurplus + '\'' +
                ", liabilitiesAndShareholderEquity='" + liabilitiesAndShareholderEquity + '\'' +
                ", cashAndShortTermInvestments='" + cashAndShortTermInvestments + '\'' +
                ", accumulatedDepreciation='" + accumulatedDepreciation + '\'' +
                ", commonStockSharesOutstanding='" + commonStockSharesOutstanding + '\'' +
                '}';
    }
}
