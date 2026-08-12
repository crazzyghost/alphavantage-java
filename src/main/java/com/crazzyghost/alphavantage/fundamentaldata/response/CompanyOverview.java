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

import com.crazzyghost.alphavantage.parser.NoneableDouble;
import com.crazzyghost.alphavantage.parser.NoneableLong;
import com.squareup.moshi.Json;

/**
 * A company profile and its headline valuation, profitability, dividend and
 * price-performance metrics, as returned by the {@code OVERVIEW} endpoint.
 * <p>
 * Unlike {@link BalanceSheet}, {@link CashFlow} and {@link IncomeStatement},
 * this record mixes point-in-time reference data (name, sector, exchange)
 * with metrics computed as of the data provider's last refresh — trailing
 * twelve month ("TTM") ratios and moving averages are not tied to a single
 * fiscal period the way a balance sheet line item is. A ratio or metric the
 * provider has not yet computed, for example because the company has not
 * filed the underlying financials, comes back as {@code null} rather than
 * zero; see {@link NoneableLong} and {@link NoneableDouble} for why that
 * distinction survives JSON parsing.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class CompanyOverview {

    @Json(name="Symbol")
    private String symbol;
    @Json(name="AssetType")
    private String assetType;
    @Json(name="Name")
    private String name;
    @Json(name="Description")
    private String description;
    @Json(name="CIK")
    @NoneableLong
    private Long cik;
    @Json(name="Exchange")
    private String exchange;
    @Json(name="Currency")
    private String currency;
    @Json(name="Country")
    private String country;
    @Json(name="Sector")
    private String sector;
    @Json(name="Industry")
    private String industry;
    @Json(name="Address")
    private String address;
    @Json(name="OfficialSite")
    private String officialSite;
    @Json(name="FiscalYearEnd")
    private String fiscalYearEnd;
    @Json(name="LatestQuarter")
    private String latestQuarter;
    @Json(name="MarketCapitalization")
    @NoneableLong
    private Long marketCapitalization;
    @Json(name="EBITDA")
    @NoneableLong
    private Long ebitda;
    @Json(name="PERatio")
    @NoneableDouble
    private Double peRatio;
    @Json(name="PEGRatio")
    @NoneableDouble
    private Double pegRatio;
    @Json(name="BookValue")
    @NoneableDouble
    private Double bookValue;
    @Json(name="DividendPerShare")
    @NoneableDouble
    private Double dividendPerShare;
    @Json(name="DividendYield")
    @NoneableDouble
    private Double dividendYield;
    @Json(name="EPS")
    @NoneableDouble
    private Double eps;
    @Json(name="RevenuePerShareTTM")
    @NoneableDouble
    private Double revenuePerShareTTM;
    @Json(name="ProfitMargin")
    @NoneableDouble
    private Double profitMargin;
    @Json(name="OperatingMarginTTM")
    @NoneableDouble
    private Double operatingMarginTTM;
    @Json(name="ReturnOnAssetsTTM")
    @NoneableDouble
    private Double returnOnAssetsTTM;
    @Json(name="ReturnOnEquityTTM")
    @NoneableDouble
    private Double returnOnEquityTTM;
    @Json(name="RevenueTTM")
    @NoneableLong
    private Long revenueTTM;
    @Json(name="GrossProfitTTM")
    @NoneableLong
    private Long grossProfitTTM;
    @Json(name="DilutedEPSTTM")
    @NoneableDouble
    private Double dilutedEpsTTM;
    @Json(name="QuarterlyEarningsGrowthYOY")
    @NoneableDouble
    private Double quarterlyEarningsGrowthYOY;
    @Json(name="QuarterlyRevenueGrowthYOY")
    @NoneableDouble
    private Double quarterlyRevenueGrowthYOY;
    @Json(name="AnalystTargetPrice")
    @NoneableDouble
    private Double analystTargetPrice;
    @Json(name="AnalystRatingStrongBuy")
    @NoneableLong
    private Long analystRatingStrongBuy;
    @Json(name="AnalystRatingBuy")
    @NoneableLong
    private Long analystRatingBuy;
    @Json(name="AnalystRatingHold")
    @NoneableLong
    private Long analystRatingHold;
    @Json(name="AnalystRatingSell")
    @NoneableLong
    private Long analystRatingSell;
    @Json(name="AnalystRatingStrongSell")
    @NoneableLong
    private Long analystRatingStrongSell;
    @Json(name="TrailingPE")
    @NoneableDouble
    private Double trailingPE;
    @Json(name="ForwardPE")
    @NoneableDouble
    private Double forwardPE;
    @Json(name="PriceToSalesRatioTTM")
    @NoneableDouble
    private Double priceToSaleRatioTTM;
    @Json(name="PriceToBookRatio")
    @NoneableDouble
    private Double priceToBookRatio;
    @Json(name="EVToRevenue")
    @NoneableDouble
    private Double evToRevenue;
    @Json(name="EVToEBITDA")
    @NoneableDouble
    private Double evToEBITDA;
    @Json(name="Beta")
    @NoneableDouble
    private Double beta;
    @Json(name="52WeekHigh")
    @NoneableDouble
    private Double fiftyTwoWeekHigh;
    @Json(name="52WeekLow")
    @NoneableDouble
    private Double fiftyTwoWeekLow;
    @Json(name="50DayMovingAverage")
    @NoneableDouble
    private Double fiftyDayMovingAverage;
    @Json(name="200DayMovingAverage")
    @NoneableDouble
    private Double twoHundredDayMovingAverage;
    @Json(name="SharesOutstanding")
    @NoneableLong
    private Long sharesOutstanding;
    @Json(name="SharesFloat")
    @NoneableLong
    private Long sharesFloat;
    @Json(name="PercentInsiders")
    @NoneableDouble
    private Double percentInsiders;
    @Json(name="PercentInstitutions")
    @NoneableDouble
    private Double percentInstitutions;
    @Json(name="DividendDate")
    private String dividendDate;
    @Json(name="ExDividendDate")
    private String exDividendDate;

    /**
     * Returns the ticker symbol.
     *
     * @return the ticker symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the type of security, for example {@code Common Stock}.
     *
     * @return the asset type
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * Returns the company's registered name.
     *
     * @return the company name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a prose summary of the company's business.
     *
     * @return the company description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the SEC Central Index Key (CIK) that uniquely identifies this
     * filer in EDGAR. {@code null} if not available (see {@link NoneableLong}).
     *
     * @return the SEC CIK number
     */
    public Long getCIK() {
        return cik;
    }

    /**
     * Returns the exchange the security is listed on, for example
     * {@code NASDAQ}.
     *
     * @return the listing exchange
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Returns the ISO currency code the company reports its financials in,
     * for example {@code USD}.
     *
     * @return the reporting currency code
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns the country the company is headquartered or incorporated in.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the company's broad economic sector, for example
     * {@code TECHNOLOGY}.
     *
     * @return the sector
     */
    public String getSector() {
        return sector;
    }

    /**
     * Returns the company's specific industry classification, a finer-grained
     * category than {@link #getSector()}.
     *
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Returns the company's registered address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the company's official website URL.
     *
     * @return the official website URL
     */
    public String getOfficialSite() {
        return officialSite;
    }

    /**
     * Returns the month the company's fiscal year ends in, for example
     * {@code December}.
     *
     * @return the fiscal year end month
     */
    public String getFiscalYearEnd() {
        return fiscalYearEnd;
    }

    /**
     * Returns the end date of the most recent fiscal quarter the company has
     * reported financials for.
     *
     * @return the latest reported quarter's end date, in {@code yyyy-MM-dd}
     *         form
     */
    public String getLatestQuarter() {
        return latestQuarter;
    }

    /**
     * Returns market capitalization: share price multiplied by shares
     * outstanding. {@code null} if not available (see {@link NoneableLong}).
     *
     * @return market capitalization, in {@link #getCurrency()}
     */
    public Long getMarketCapitalization() {
        return marketCapitalization;
    }

    /**
     * Returns trailing earnings before interest, taxes, depreciation and
     * amortization (EBITDA). {@code null} if not available (see
     * {@link NoneableLong}).
     *
     * @return EBITDA, in {@link #getCurrency()}
     */
    public Long getEBITDA() {
        return ebitda;
    }

    /**
     * Returns the price-to-earnings (P/E) ratio: share price divided by
     * earnings per share. {@code null} if the company has no positive
     * trailing earnings to divide by, or the ratio is otherwise unavailable
     * (see {@link NoneableDouble}).
     *
     * @return the P/E ratio
     */
    public Double getPERatio() {
        return peRatio;
    }

    /**
     * Returns the price/earnings-to-growth (PEG) ratio: the P/E ratio divided
     * by expected earnings growth. {@code null} if not available (see
     * {@link NoneableDouble}).
     *
     * @return the PEG ratio
     */
    public Double getPEGRatio() {
        return pegRatio;
    }

    /**
     * Returns book value per share: shareholder equity divided by shares
     * outstanding. {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return book value per share, in {@link #getCurrency()}
     */
    public Double getBookValue() {
        return bookValue;
    }

    /**
     * Returns the most recent annualized dividend per share. {@code null} if
     * the company does not pay a dividend, or the figure is otherwise
     * unavailable (see {@link NoneableDouble}).
     *
     * @return dividend per share, in {@link #getCurrency()}
     */
    public Double getDividendPerShare() {
        return dividendPerShare;
    }

    /**
     * Returns dividend yield: annual dividend per share divided by share
     * price. {@code null} if the company does not pay a dividend, or the
     * figure is otherwise unavailable (see {@link NoneableDouble}).
     *
     * @return dividend yield, as a decimal fraction (for example {@code 0.02}
     *         for 2%)
     */
    public Double getDividendYield() {
        return dividendYield;
    }

    /**
     * Returns trailing twelve-month earnings per share (EPS). {@code null} if
     * not available (see {@link NoneableDouble}).
     *
     * @return earnings per share, in {@link #getCurrency()}
     */
    public Double getEPS() {
        return eps;
    }

    /**
     * Returns trailing twelve-month revenue per share. {@code null} if not
     * available (see {@link NoneableDouble}).
     *
     * @return revenue per share (TTM), in {@link #getCurrency()}
     */
    public Double getRevenuePerShareTTM() {
        return revenuePerShareTTM;
    }

    /**
     * Returns profit margin: net income divided by revenue. {@code null} if
     * not available (see {@link NoneableDouble}).
     *
     * @return profit margin, as a decimal fraction
     */
    public Double getProfitMargin() {
        return profitMargin;
    }

    /**
     * Returns trailing twelve-month operating margin: operating income
     * divided by revenue. {@code null} if not available (see
     * {@link NoneableDouble}).
     *
     * @return operating margin (TTM), as a decimal fraction
     */
    public Double getOperatingMarginTTM() {
        return operatingMarginTTM;
    }

    /**
     * Returns trailing twelve-month return on assets: net income divided by
     * total assets. {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return return on assets (TTM), as a decimal fraction
     */
    public Double getReturnOnAssetsTTM() {
        return returnOnAssetsTTM;
    }

    /**
     * Returns trailing twelve-month return on equity: net income divided by
     * shareholder equity. {@code null} if not available (see
     * {@link NoneableDouble}).
     *
     * @return return on equity (TTM), as a decimal fraction
     */
    public Double getReturnOnEquityTTM() {
        return returnOnEquityTTM;
    }

    /**
     * Returns trailing twelve-month total revenue. {@code null} if not
     * available (see {@link NoneableLong}).
     *
     * @return total revenue (TTM), in {@link #getCurrency()}
     */
    public Long getRevenueTTM() {
        return revenueTTM;
    }

    /**
     * Returns trailing twelve-month gross profit. {@code null} if not
     * available (see {@link NoneableLong}).
     *
     * @return gross profit (TTM), in {@link #getCurrency()}
     */
    public Long getGrossProfitTTM() {
        return grossProfitTTM;
    }

    /**
     * Returns trailing twelve-month diluted earnings per share, which accounts
     * for the dilutive effect of convertible securities and options.
     * {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return diluted EPS (TTM), in {@link #getCurrency()}
     */
    public Double getDilutedEpsTTM() {
        return dilutedEpsTTM;
    }

    /**
     * Returns quarterly earnings growth, year over year. {@code null} if not
     * available (see {@link NoneableDouble}).
     *
     * @return quarterly earnings growth (YoY), as a decimal fraction
     */
    public Double getQuarterlyEarningsGrowthYOY() {
        return quarterlyEarningsGrowthYOY;
    }

    /**
     * Returns quarterly revenue growth, year over year. {@code null} if not
     * available (see {@link NoneableDouble}).
     *
     * @return quarterly revenue growth (YoY), as a decimal fraction
     */
    public Double getQuarterlyRevenueGrowthYOY() {
        return quarterlyRevenueGrowthYOY;
    }

    /**
     * Returns the mean analyst price target. {@code null} if no analyst
     * coverage is available (see {@link NoneableDouble}).
     *
     * @return the analyst target price, in {@link #getCurrency()}
     */
    public Double getAnalystTargetPrice() {
        return analystTargetPrice;
    }

    /**
     * Returns the number of analysts rating the stock a strong buy.
     * {@code null} if no analyst coverage is available (see
     * {@link NoneableLong}).
     *
     * @return the strong buy analyst rating count
     */
    public Long getAnalystRatingStrongBuy() {
        return analystRatingStrongBuy;
    }

    /**
     * Returns the number of analysts rating the stock a buy. {@code null} if
     * no analyst coverage is available (see {@link NoneableLong}).
     *
     * @return the buy analyst rating count
     */
    public Long getAnalystRatingBuy() {
        return analystRatingBuy;
    }

    /**
     * Returns the number of analysts rating the stock a hold. {@code null} if
     * no analyst coverage is available (see {@link NoneableLong}).
     *
     * @return the hold analyst rating count
     */
    public Long getAnalystRatingHold() {
        return analystRatingHold;
    }

    /**
     * Returns the number of analysts rating the stock a sell. {@code null} if
     * no analyst coverage is available (see {@link NoneableLong}).
     *
     * @return the sell analyst rating count
     */
    public Long getAnalystRatingSell() {
        return analystRatingSell;
    }

    /**
     * Returns the number of analysts rating the stock a strong sell.
     * {@code null} if no analyst coverage is available (see
     * {@link NoneableLong}).
     *
     * @return the strong sell analyst rating count
     */
    public Long getAnalystRatingStrongSell() {
        return analystRatingStrongSell;
    }

    /**
     * Returns the trailing price-to-earnings ratio, computed from the last
     * twelve months of reported earnings. {@code null} if not available (see
     * {@link NoneableDouble}).
     *
     * @return the trailing P/E ratio
     */
    public Double getTrailingPE() {
        return trailingPE;
    }

    /**
     * Returns the forward price-to-earnings ratio, computed from projected
     * next-twelve-months earnings. {@code null} if not available (see
     * {@link NoneableDouble}).
     *
     * @return the forward P/E ratio
     */
    public Double getForwardPE() {
        return forwardPE;
    }

    /**
     * Returns trailing twelve-month price-to-sales ratio: market
     * capitalization divided by revenue. {@code null} if not available (see
     * {@link NoneableDouble}).
     *
     * @return the price-to-sales ratio (TTM)
     */
    public Double getPriceToSaleRatioTTM() {
        return priceToSaleRatioTTM;
    }

    /**
     * Returns the price-to-book ratio: share price divided by book value per
     * share. {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the price-to-book ratio
     */
    public Double getPriceToBookRatio() {
        return priceToBookRatio;
    }

    /**
     * Returns enterprise value to revenue: enterprise value (market
     * capitalization plus net debt) divided by revenue. {@code null} if not
     * available (see {@link NoneableDouble}).
     *
     * @return the EV/revenue ratio
     */
    public Double getEvToRevenue() {
        return evToRevenue;
    }

    /**
     * Returns enterprise value to EBITDA: enterprise value (market
     * capitalization plus net debt) divided by EBITDA. {@code null} if not
     * available (see {@link NoneableDouble}).
     *
     * @return the EV/EBITDA ratio
     */
    public Double getEvToEBITDA() {
        return evToEBITDA;
    }

    /**
     * Returns beta: the security's price volatility relative to the broader
     * market, where {@code 1.0} moves in line with the market. {@code null}
     * if not available (see {@link NoneableDouble}).
     *
     * @return beta
     */
    public Double getBeta() {
        return beta;
    }

    /**
     * Returns the highest closing price over the trailing 52 weeks.
     * {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the 52-week high, in {@link #getCurrency()}
     */
    public Double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    /**
     * Returns the lowest closing price over the trailing 52 weeks.
     * {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the 52-week low, in {@link #getCurrency()}
     */
    public Double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    /**
     * Returns the 50-day simple moving average of the closing price.
     * {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the 50-day moving average, in {@link #getCurrency()}
     */
    public Double getFiftyDayMovingAverage() {
        return fiftyDayMovingAverage;
    }

    /**
     * Returns the 200-day simple moving average of the closing price.
     * {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the 200-day moving average, in {@link #getCurrency()}
     */
    public Double getTwoHundredDayMovingAverage() {
        return twoHundredDayMovingAverage;
    }

    /**
     * Returns the number of shares currently outstanding. {@code null} if not
     * available (see {@link NoneableLong}).
     *
     * @return shares outstanding
     */
    public Long getSharesOutstanding() {
        return sharesOutstanding;
    }

    /**
     * Returns the number of shares available for public trading, excluding
     * closely held and restricted shares. {@code null} if not available (see
     * {@link NoneableLong}).
     *
     * @return the floating share count
     */
    public Long getSharesFloat() {
        return sharesFloat;
    }

    /**
     * Returns the percentage of outstanding shares held by company insiders.
     * {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the percentage of shares held by insiders
     */
    public Double getPercentInsiders() {
        return percentInsiders;
    }

    /**
     * Returns the percentage of outstanding shares held by institutional
     * investors. {@code null} if not available (see {@link NoneableDouble}).
     *
     * @return the percentage of shares held by institutions
     */
    public Double getPercentInstitutions() {
        return percentInstitutions;
    }

    /**
     * Returns the date the most recent dividend was (or will be) paid.
     *
     * @return the dividend payment date, in {@code yyyy-MM-dd} form
     */
    public String getDividendDate() {
        return dividendDate;
    }

    /**
     * Returns the ex-dividend date: the first trading day a buyer no longer
     * receives the most recently declared dividend.
     *
     * @return the ex-dividend date, in {@code yyyy-MM-dd} form
     */
    public String getExDividendDate() {
        return exDividendDate;
    }


    @Override
    public String toString() {
        return "CompanyOverview{" +
                "symbol='" + symbol + '\'' +
                ", assetType='" + assetType + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cik='" + cik + '\'' +
                ", exchange='" + exchange + '\'' +
                ", currency='" + currency + '\'' +
                ", country='" + country + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", address='" + address + '\'' +
                ", officialSite='" + officialSite + '\'' +
                ", fiscalYearEnd='" + fiscalYearEnd + '\'' +
                ", latestQuarter='" + latestQuarter + '\'' +
                ", marketCapitalization=" + marketCapitalization +
                ", ebitda=" + ebitda +
                ", peRatio=" + peRatio +
                ", pegRatio=" + pegRatio +
                ", bookValue=" + bookValue +
                ", dividendPerShare=" + dividendPerShare +
                ", dividendYield=" + dividendYield +
                ", eps=" + eps +
                ", revenuePerShareTTM=" + revenuePerShareTTM +
                ", profitMargin=" + profitMargin +
                ", operatingMarginTTM=" + operatingMarginTTM +
                ", returnOnAssetsTTM=" + returnOnAssetsTTM +
                ", returnOnEquityTTM=" + returnOnEquityTTM +
                ", revenueTTM=" + revenueTTM +
                ", grossProfitTTM=" + grossProfitTTM +
                ", dilutedEpsTTM=" + dilutedEpsTTM +
                ", quarterlyEarningsGrowthYOY=" + quarterlyEarningsGrowthYOY +
                ", quarterlyRevenueGrowthYOY=" + quarterlyRevenueGrowthYOY +
                ", analystTargetPrice=" + analystTargetPrice +
                ", analystRatingStrongBuy=" + analystRatingStrongBuy +
                ", analystRatingBuy=" + analystRatingBuy +
                ", analystRatingHold=" + analystRatingHold +
                ", analystRatingSell=" + analystRatingSell +
                ", analystRatingStrongSell=" + analystRatingStrongSell +
                ", trailingPE=" + trailingPE +
                ", forwardPE=" + forwardPE +
                ", priceToSaleRatioTTM=" + priceToSaleRatioTTM +
                ", priceToBookRatio=" + priceToBookRatio +
                ", evToRevenue=" + evToRevenue +
                ", evToEBITDA=" + evToEBITDA +
                ", beta=" + beta +
                ", fiftyTwoWeekHigh=" + fiftyTwoWeekHigh +
                ", fiftyTwoWeekLow=" + fiftyTwoWeekLow +
                ", fiftyDayMovingAverage=" + fiftyDayMovingAverage +
                ", twoHundredDayMovingAverage=" + twoHundredDayMovingAverage +
                ", sharesOutstanding=" + sharesOutstanding +
                ", sharesFloat=" + sharesFloat +
                ", percentInsiders=" + percentInsiders +
                ", percentInstitutions=" + percentInstitutions +
                ", dividendDate='" + dividendDate + '\'' +
                ", exDividendDate='" + exDividendDate + '\'' +
                '}';
    }
}
