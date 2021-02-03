package fundamentaldata;


import com.crazzyghost.alphavantage.fundamentaldata.response.BalanceSheetResponse;
import com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverviewResponse;
import com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverviewUnit;
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
        CompanyOverviewResponse response = CompanyOverviewResponse.of(parseJSON("companyoverview", CompanyOverviewUnit.class));
        assertNull(response.getErrorMessage());
        assertNotNull(response.getOverview());
        assertEquals(response.getSymbol(), "IBM");
    }

    @Test
    public void testCompanyOverviewResponseEmpty() throws IOException {
        CompanyOverviewResponse response = CompanyOverviewResponse.of(empty());
        assertNotNull(response.getErrorMessage());
    }


}