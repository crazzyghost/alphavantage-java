package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.squareup.moshi.Json;

public class CompanyOverview {

    @Json(name="Symbol")
    private String symbol;
    @Json(name="AssetType")
    private String assetType;
    @Json(name="Name")
    private String name;
    @Json(name="Description")
    private String description;
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
    @Json(name="FullTimeEmployees")
    private Long fullTimeEmployees;
    @Json(name="FiscalYearEnd")
    private String fiscalYearEnd;
    @Json(name="LatestQuarter")
    private String latestQuarter;
    @Json(name="MarketCapitalization")
    private Double marketCapitalization;
    @Json(name="EBITDA")
    private Double ebitda;
    @Json(name="PERatio")
    private Double peRatio;
    @Json(name="PEGRatio")
    private Double pegRatio;
    @Json(name="BookValue")
    private Double bookValue;
    @Json(name="DividendPerShare")
    private Double dividendPerShare;
    @Json(name="DividendYield")
    private Double dividendYield;
    @Json(name="EPS")
    private Double eps;
    @Json(name="RevenuePerShareTTM")
    private Double revenuePerShareTTM;
    @Json(name="ProfitMargin")
    private Double profitMargin;
    @Json(name="OperatingMarginTTM")
    private Double operatingMarginTTM;
    @Json(name="ReturnOnAssetsTTM")
    private Double returnOnAssetsTTM;
    @Json(name="ReturnOnEquityTTM")
    private Double returnOnEquityTTM;
    @Json(name="RevenueTTM")
    private Double revenueTTM;
    @Json(name="GrossProfitTTM")
    private Double grossProfitTTM;
    @Json(name="DilutedEPSTTM")
    private Double dilutedEpsTTM;
    @Json(name="QuarterlyEarningsGrowthYOY")
    private Double quarterlyEarningsGrowthYOY;
    @Json(name="QuarterlyRevenueGrowthYOY")
    private Double quarterlyRevenueGrowthYOY;
    @Json(name="AnalystTargetPrice")
    private Double analystTargetPrice;
    @Json(name="TrailingPE")
    private Double trailingPE;
    @Json(name="ForwardPE")
    private Double forwardPE;
    @Json(name="PriceToSalesRatioTTM")
    private Double priceToSaleRatioTTM;
    @Json(name="PriceToBookRatio")
    private Double priceToBookRatio;
    @Json(name="EVToRevenue")
    private Double evToRevenue;
    @Json(name="EVToEBITDA")
    private Double evToEBITDA;
    @Json(name="Beta")
    private Double beta;
    @Json(name="52WeekHigh")
    private Double fiftyTwoWeekHigh;
    @Json(name="52WeekLow")
    private Double fiftyTwoWeekLow;
    @Json(name="50DayMovingAverage")
    private Double fiftyDayMovingAverage;
    @Json(name="200DayMovingAverage")
    private Double twoHundredDayMovingAverage;
    @Json(name="SharesOutstanding")
    private Long sharesOutstanding;
    @Json(name="SharesFloat")
    private Long sharesFloat;
    @Json(name="SharesShort")
    private Long sharesShort;
    @Json(name="SharesShortPriorMonth")
    private Long sharesShortPriorMonth;
    @Json(name="ShortRatio")
    private Double shortRatio;
    @Json(name="ShortPercentOutstanding")
    private Double shortPercentOutstanding;
    @Json(name="ShortPercentFloat")
    private Double shortPercentFloat;
    @Json(name="PercentInsiders")
    private Double percentInsiders;
    @Json(name="PercentInstitutions")
    private Double percentInstitutions;
    @Json(name="ForwardAnnualDividendRate")
    private Double ForwardAnnualDividendRate;
    @Json(name="ForwardAnnualDividendYield")
    private Double ForwardAnnualDividendYield;
    @Json(name="PayoutRatio")
    private Double payoutRatio;
    @Json(name="DividendDate")
    private String dividendDate;
    @Json(name="ExDividendDate")
    private String exDividendDate;
    @Json(name="LastSplitFactor")
    private String lastSplitFactor;
    @Json(name="LastSplitDate")
    private String lastSplitDate;

    public String getSymbol() {
        return symbol;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExchange() {
        return exchange;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCountry() {
        return country;
    }

    public String getSector() {
        return sector;
    }

    public String getIndustry() {
        return industry;
    }

    public String getAddress() {
        return address;
    }

    public Long getFullTimeEmployees() {
        return fullTimeEmployees;
    }

    public String getFiscalYearEnd() {
        return fiscalYearEnd;
    }

    public String getLatestQuarter() {
        return latestQuarter;
    }

    public Double getMarketCapitalization() {
        return marketCapitalization;
    }

    public Double getEbitda() {
        return ebitda;
    }

    public Double getPeRatio() {
        return peRatio;
    }

    public Double getPegRatio() {
        return pegRatio;
    }

    public Double getBookValue() {
        return bookValue;
    }

    public Double getDividendPerShare() {
        return dividendPerShare;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public Double getEps() {
        return eps;
    }

    public Double getRevenuePerShareTTM() {
        return revenuePerShareTTM;
    }

    public Double getProfitMargin() {
        return profitMargin;
    }

    public Double getOperatingMarginTTM() {
        return operatingMarginTTM;
    }

    public Double getReturnOnAssetsTTM() {
        return returnOnAssetsTTM;
    }

    public Double getReturnOnEquityTTM() {
        return returnOnEquityTTM;
    }

    public Double getRevenueTTM() {
        return revenueTTM;
    }

    public Double getGrossProfitTTM() {
        return grossProfitTTM;
    }

    public Double getDilutedEpsTTM() {
        return dilutedEpsTTM;
    }

    public Double getQuarterlyEarningsGrowthYOY() {
        return quarterlyEarningsGrowthYOY;
    }

    public Double getQuarterlyRevenueGrowthYOY() {
        return quarterlyRevenueGrowthYOY;
    }

    public Double getAnalystTargetPrice() {
        return analystTargetPrice;
    }

    public Double getTrailingPE() {
        return trailingPE;
    }

    public Double getForwardPE() {
        return forwardPE;
    }

    public Double getPriceToSaleRatioTTM() {
        return priceToSaleRatioTTM;
    }

    public Double getPriceToBookRatio() {
        return priceToBookRatio;
    }

    public Double getEvToRevenue() {
        return evToRevenue;
    }

    public Double getEvToEBITDA() {
        return evToEBITDA;
    }

    public Double getBeta() {
        return beta;
    }

    public Double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public Double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public Double getFiftyDayMovingAverage() {
        return fiftyDayMovingAverage;
    }

    public Double getTwoHundredDayMovingAverage() {
        return twoHundredDayMovingAverage;
    }

    public Long getSharesOutstanding() {
        return sharesOutstanding;
    }

    public Long getSharesFloat() {
        return sharesFloat;
    }

    public Long getSharesShort() {
        return sharesShort;
    }

    public Long getSharesShortPriorMonth() {
        return sharesShortPriorMonth;
    }

    public Double getShortRatio() {
        return shortRatio;
    }

    public Double getShortPercentOutstanding() {
        return shortPercentOutstanding;
    }

    public Double getShortPercentFloat() {
        return shortPercentFloat;
    }

    public Double getPercentInsiders() {
        return percentInsiders;
    }

    public Double getPercentInstitutions() {
        return percentInstitutions;
    }

    public Double getForwardAnnualDividendRate() {
        return ForwardAnnualDividendRate;
    }

    public Double getForwardAnnualDividendYield() {
        return ForwardAnnualDividendYield;
    }

    public Double getPayoutRatio() {
        return payoutRatio;
    }

    public String getDividendDate() {
        return dividendDate;
    }

    public String getExDividendDate() {
        return exDividendDate;
    }

    public String getLastSplitFactor() {
        return lastSplitFactor;
    }

    public String getLastSplitDate() {
        return lastSplitDate;
    }

    @Override
    public String toString() {
        return "CompanyOverview{" +
                "symbol='" + symbol + '\'' +
                ", assetType='" + assetType + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exchange='" + exchange + '\'' +
                ", currency='" + currency + '\'' +
                ", country='" + country + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", address='" + address + '\'' +
                ", fullTimeEmployees=" + fullTimeEmployees +
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
                ", sharesShort=" + sharesShort +
                ", sharesShortPriorMonth=" + sharesShortPriorMonth +
                ", shortRatio=" + shortRatio +
                ", shortPercentOutstanding=" + shortPercentOutstanding +
                ", shortPercentFloat=" + shortPercentFloat +
                ", percentInsiders=" + percentInsiders +
                ", percentInstitutions=" + percentInstitutions +
                ", ForwardAnnualDividendRate=" + ForwardAnnualDividendRate +
                ", ForwardAnnualDividendYield=" + ForwardAnnualDividendYield +
                ", payoutRatio=" + payoutRatio +
                ", dividendDate='" + dividendDate + '\'' +
                ", exDividendDate='" + exDividendDate + '\'' +
                ", lastSplitFactor='" + lastSplitFactor + '\'' +
                ", lastSplitDate='" + lastSplitDate + '\'' +
                '}';
    }
}
