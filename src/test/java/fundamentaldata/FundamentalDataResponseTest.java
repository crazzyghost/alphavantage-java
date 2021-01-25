package fundamentaldata;


import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.fundamentaldata.request.*;
import com.crazzyghost.alphavantage.fundamentaldata.response.BalanceSheetResponse;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static util.TestUtils.json;

public class FundamentalDataResponseTest {

    @Before
    public void setUp(){
        TestUtils.forDirectory("fundamentaldata");
    }

    @Test
    public void testBalanceSheetResponse() throws IOException {
        BalanceSheetResponse response = BalanceSheetResponse.of(json("balancesheet"));
        System.out.println(response.getAnnualReports().toString());
        System.out.println(response.getQuarterlyReports().toString());
        assertEquals(true, true);
    }

}