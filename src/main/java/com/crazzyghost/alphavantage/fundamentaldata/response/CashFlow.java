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
 * A single annual or quarterly cash flow statement for a company, as filed
 * with the SEC — cash generated and consumed by operating, investing and
 * financing activity during the period ended {@link #getFiscalDateEnding()}.
 * <p>
 * Every monetary figure is a raw, as-reported amount in {@link #getReportedCurrency()},
 * not normalized or scaled by Alpha Vantage. A line item the company did not
 * report for the period comes back as {@code null} rather than zero; see
 * {@link NoneableLong} for why that distinction survives JSON parsing.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
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
     * Returns net cash generated by (or used in) core operating activities.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return operating cash flow, in the reporting currency
     */
    public Long getOperatingCashflow() {
        return operatingCashflow;
    }

    /**
     * Returns cash paid out for day-to-day operating activities. {@code null}
     * if not reported for the period (see {@link NoneableLong}).
     *
     * @return payments for operating activities, in the reporting currency
     */
    public Long getPaymentsForOperatingActivities() {
        return paymentsForOperatingActivities;
    }

    /**
     * Returns cash received from operating activities other than core sales.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return proceeds from operating activities, in the reporting currency
     */
    public Long getProceedsFromOperatingActivities() {
        return proceedsFromOperatingActivities;
    }

    /**
     * Returns the period-over-period change in operating liabilities, an
     * adjustment used when deriving operating cash flow indirectly from net
     * income. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return change in operating liabilities, in the reporting currency
     */
    public Long getChangeInOperatingLiabilities() {
        return changeInOperatingLiabilities;
    }

    /**
     * Returns the period-over-period change in operating assets, an
     * adjustment used when deriving operating cash flow indirectly from net
     * income. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return change in operating assets, in the reporting currency
     */
    public Long getChangeInOperatingAssets() {
        return changeInOperatingAssets;
    }

    /**
     * Returns depreciation, depletion and amortization expense added back
     * when deriving operating cash flow from net income. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return depreciation, depletion and amortization, in the reporting
     *         currency
     */
    public Long getDepreciationDepletionAndAmortization() {
        return depreciationDepletionAndAmortization;
    }

    /**
     * Returns cash spent acquiring or upgrading physical assets such as
     * property, plant and equipment. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return capital expenditures, in the reporting currency
     */
    public Long getCapitalExpenditures() {
        return capitalExpenditures;
    }

    /**
     * Returns the period-over-period change in accounts receivable.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return change in receivables, in the reporting currency
     */
    public Long getChangeInReceivables() {
        return changeInReceivables;
    }

    /**
     * Returns the period-over-period change in inventory. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return change in inventory, in the reporting currency
     */
    public Long getChangeInInventory() {
        return changeInInventory;
    }

    /**
     * Returns net profit or loss for the period, the starting point for the
     * indirect-method operating-activities reconciliation. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return profit or loss, in the reporting currency
     */
    public Long getProfitLoss() {
        return profitLoss;
    }

    /**
     * Returns net cash generated by (or used in) investing activities, such
     * as buying or selling securities and long-term assets. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return cash flow from investing activities, in the reporting currency
     */
    public Long getCashflowFromInvestment() {
        return cashflowFromInvestment;
    }

    /**
     * Returns net cash generated by (or used in) financing activities, such
     * as issuing or repaying debt and equity, and paying dividends.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return cash flow from financing activities, in the reporting currency
     */
    public Long getCashflowFromFinancing() {
        return cashflowFromFinancing;
    }

    /**
     * Returns the net cash effect of issuing and repaying short-term debt.
     * {@code null} if not reported for the period (see {@link NoneableLong}).
     *
     * @return proceeds from (or repayments of) short-term debt, in the
     *         reporting currency
     */
    public Long getProceedsFromRepaymentsOfShortTermDebt() {
        return proceedsFromRepaymentsOfShortTermDebt;
    }

    /**
     * Returns cash spent repurchasing common stock. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return payments for repurchase of common stock, in the reporting
     *         currency
     */
    public Long getPaymentsForRepurchaseOfCommonStock() {
        return paymentsForRepurchaseOfCommonStock;
    }

    /**
     * Returns cash spent repurchasing equity of any class. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return payments for repurchase of equity, in the reporting currency
     */
    public Long getPaymentsForRepurchaseOfEquity() {
        return paymentsForRepurchaseOfEquity;
    }

    /**
     * Returns cash spent repurchasing preferred stock. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return payments for repurchase of preferred stock, in the reporting
     *         currency
     */
    public Long getPaymentsForRepurchaseOfPreferredStock() {
        return paymentsForRepurchaseOfPreferredStock;
    }

    /**
     * Returns total cash dividends paid to all shareholders. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return dividend payout, in the reporting currency
     */
    public Long getDividendPayout() {
        return dividendPayout;
    }

    /**
     * Returns cash dividends paid to common shareholders. {@code null} if not
     * reported for the period (see {@link NoneableLong}).
     *
     * @return dividend payout to common stock, in the reporting currency
     */
    public Long getDividendPayoutCommonStock() {
        return dividendPayoutCommonStock;
    }

    /**
     * Returns cash dividends paid to preferred shareholders. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return dividend payout to preferred stock, in the reporting currency
     */
    public Long getDividendPayoutPreferredStock() {
        return dividendPayoutPreferredStock;
    }

    /**
     * Returns cash received from issuing new common stock. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return proceeds from issuance of common stock, in the reporting
     *         currency
     */
    public Long getProceedsFromIssuanceOfCommonStock() {
        return proceedsFromIssuanceOfCommonStock;
    }

    /**
     * Returns net cash received from issuing long-term debt and capital
     * securities. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return proceeds from issuance of long-term debt and capital
     *         securities, in the reporting currency
     */
    public Long getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet() {
        return proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
    }

    /**
     * Returns cash received from issuing new preferred stock. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return proceeds from issuance of preferred stock, in the reporting
     *         currency
     */
    public Long getProceedsFromIssuanceOfPreferredStock() {
        return proceedsFromIssuanceOfPreferredStock;
    }

    /**
     * Returns cash received from repurchasing (or, depending on the filing,
     * reselling) equity. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return proceeds from repurchase of equity, in the reporting currency
     */
    public Long getProceedsFromRepurchaseOfEquity() {
        return proceedsFromRepurchaseOfEquity;
    }

    /**
     * Returns cash received from reselling shares previously held as treasury
     * stock. {@code null} if not reported for the period (see
     * {@link NoneableLong}).
     *
     * @return proceeds from sale of treasury stock, in the reporting currency
     */
    public Long getProceedsFromSaleOfTreasuryStock() {
        return proceedsFromSaleOfTreasuryStock;
    }

    /**
     * Returns the net change in cash and cash equivalents over the period,
     * combining operating, investing and financing activity. {@code null} if
     * not reported for the period (see {@link NoneableLong}).
     *
     * @return change in cash and cash equivalents, in the reporting currency
     */
    public Long getChangeInCashAndCashEquivalents() {
        return changeInCashAndCashEquivalents;
    }

    /**
     * Returns the effect of foreign-exchange-rate movements on cash balances
     * held in other currencies. {@code null} if not reported for the period
     * (see {@link NoneableLong}).
     *
     * @return change in exchange rate, in the reporting currency
     */
    public Long getChangeInExchangeRate() {
        return changeInExchangeRate;
    }

    /**
     * Returns net income for the period. {@code null} if not reported for the
     * period (see {@link NoneableLong}).
     *
     * @return net income, in the reporting currency
     */
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
