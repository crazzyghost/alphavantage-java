package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.NoneableDouble;
import com.crazzyghost.alphavantage.parser.NoneableLong;
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
    @Json(name="DividendDate")
    private String dividendDate;
    @Json(name="ExDividendDate")
    private String exDividendDate;

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

    public Long getCIK() {
        return cik;
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

    public String getFiscalYearEnd() {
        return fiscalYearEnd;
    }

    public String getLatestQuarter() {
        return latestQuarter;
    }

    public Long getMarketCapitalization() {
        return marketCapitalization;
    }

    public Long getEBITDA() {
        return ebitda;
    }

    public Double getPERatio() {
        return peRatio;
    }

    public Double getPEGRatio() {
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

    public Double getEPS() {
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

    public Long getRevenueTTM() {
        return revenueTTM;
    }

    public Long getGrossProfitTTM() {
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

    public String getDividendDate() {
        return dividendDate;
    }

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
                ", dividendDate='" + dividendDate + '\'' +
                ", exDividendDate='" + exDividendDate + '\'' +
                '}';
    }
}
