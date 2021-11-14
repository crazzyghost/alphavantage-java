package fundamentaldata;


import com.crazzyghost.alphavantage.fundamentaldata.response.BalanceSheetResponse;
import com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverviewResponse;
import com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverview;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import java.io.IOException;

import static org.junit.Assert.*;
import static util.TestUtils.*;

public class FundamentalDataResponseTest {

    @Before
    public void setUp(){
        TestUtils.forDirectory("fundamentaldata");
    }

    @Test
    public void testBalanceSheetResponse() throws IOException {
        BalanceSheetResponse response = BalanceSheetResponse.of(json("balancesheet"));
        assertEquals(response.getAnnualReports().size(), 2);
        assertEquals(response.getQuarterlyReports().size(), 3);
        assertNull(response.getErrorMessage());
        assertEquals(response.getSymbol(), "IBM");
    }

    @Test
    public void testCompanyOverviewResponse() throws IOException {
        CompanyOverviewResponse response = CompanyOverviewResponse.of(json("companyoverview"));
        CompanyOverview overview = response.getOverview();
        assertNull(response.getErrorMessage());
        assertEquals(overview.getSymbol(), "IBM");
        assertEquals(overview.getAssetType(), "Common Stock");
        assertEquals(overview.getName(), "International Business Machines Corporation");
        assertEquals(overview.getDescription(), "International Business Machines Corporation provides integrated solutions and services worldwide.");
        assertEquals(overview.getExchange(), "NYSE");
        assertEquals(overview.getCurrency(), "USD");
        assertEquals(overview.getCountry(), "USA");
        assertEquals(overview.getSector(), "Technology");
        assertEquals(overview.getIndustry(), "Information Technology Services");
        assertEquals(overview.getAddress(), "One New Orchard Road, Armonk, NY, United States, 10504");
        assertEquals(overview.getFullTimeEmployees(), 352600L, 0.0);
        assertEquals(overview.getFiscalYearEnd(), "December");
        assertEquals(overview.getLatestQuarter(), "2020-09-30");
        assertEquals(overview.getMarketCapitalization(), 117307654144D, 0.0);
        assertEquals(overview.getEbitda(), 15690000384D, 0.0);
        assertEquals(overview.getPeRatio(), 14.9212, 0.0);
        assertEquals(overview.getPegRatio(), 9.5324, 0.0);
        assertEquals(overview.getBookValue(), 23.801, 0.0);
        assertEquals(overview.getDividendPerShare(), 6.52, 0.0);
        assertEquals(overview.getDividendYield(), 0.0501, 0.0);
        assertEquals(overview.getEps(), 8.823, 0.0);
        assertEquals(overview.getRevenuePerShareTTM(), 84.402, 0.0);
        assertEquals(overview.getProfitMargin(), 0.1053, 0.0);
        assertEquals(overview.getOperatingMarginTTM(), 0.1205, 0.0);
        assertEquals(overview.getReturnOnAssetsTTM(), 0.0372, 0.0);
        assertEquals(overview.getReturnOnEquityTTM(), 0.401, 0.0);
        assertEquals(overview.getRevenueTTM(), 75030003712D, 0.0);
        assertEquals(overview.getGrossProfitTTM(), 36489000000D, 0.0);
        assertEquals(overview.getDilutedEpsTTM(), 8.823, 0.0);
        assertEquals(overview.getQuarterlyEarningsGrowthYOY(), 0.011, 0.0);
        assertEquals(overview.getQuarterlyRevenueGrowthYOY(), -0.026, 0.0);
        assertEquals(overview.getAnalystTargetPrice(), 138.2, 0.0);
        assertEquals(overview.getTrailingPE(), 14.9212, 0.0);
        assertEquals(overview.getForwardPE(), 10.9649, 0.0);
        assertEquals(overview.getPriceToSaleRatioTTM(), 1.532, 0.0);
        assertEquals(overview.getPriceToBookRatio(), 5.3943, 0.0);
        assertEquals(overview.getEvToRevenue(), 2.255, 0.0);
        assertEquals(overview.getEvToEBITDA(), 11.0499, 0.0);
        assertEquals(overview.getBeta(), 1.2416, 0.0);
        assertEquals(overview.getFiftyTwoWeekHigh(), 150.8394, 0.0);
        assertEquals(overview.getFiftyTwoWeekLow(), 86.9458, 0.0);
        assertEquals(overview.getFiftyDayMovingAverage(), 126.0836, 0.0);
        assertEquals(overview.getTwoHundredDayMovingAverage(), 122.5964, 0.0);
        assertEquals(overview.getSharesOutstanding(), 891057024L, 0.0);
        assertEquals(overview.getSharesFloat(), 889471034L, 0.0);
        assertEquals(overview.getSharesShort(), 24592416L, 0.0);
        assertEquals(overview.getSharesShortPriorMonth(), 22028993L, 0.0);
        assertEquals(overview.getShortRatio(), 5.2, 0.0);
        assertEquals(overview.getShortPercentOutstanding(), 0.03, 0.0);
        assertEquals(overview.getShortPercentFloat(), 0.0276, 0.0);
        assertEquals(overview.getPercentInsiders(), 0.128, 0.0);
        assertEquals(overview.getPercentInstitutions(), 58.584, 0.0);
        assertEquals(overview.getForwardAnnualDividendRate(), 6.52, 0.0);
        assertEquals(overview.getForwardAnnualDividendYield(), 0.0501, 0.0);
        assertEquals(overview.getPayoutRatio(), 0.4865, 0.0);
        assertEquals(overview.getDividendDate(), "2020-12-10");
        assertEquals(overview.getExDividendDate(), "2020-11-09");
        assertEquals(overview.getLastSplitFactor(), "2:1");
        assertEquals(overview.getLastSplitDate(), "1999-05-27");

    }

    @Test
    public void testCompanyOverviewResponseEmpty() throws IOException {
        CompanyOverviewResponse response = CompanyOverviewResponse.of(empty());
        assertNotNull(response.getErrorMessage());
    }


}