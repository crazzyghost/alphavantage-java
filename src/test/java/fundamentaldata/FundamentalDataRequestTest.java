package fundamentaldata;

import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.request.CryptoRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.DigitalCurrencyRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.RatingRequest;
import com.crazzyghost.alphavantage.fundamentaldata.request.*;
import com.crazzyghost.alphavantage.parameters.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FundamentalDataRequestTest {

    @Test
    public void testBalanceSheetRequest() {
        String expected = "https://www.alphavantage.co/query?function=BALANCE_SHEET&symbol=IBM&apikey=demo";
        FundamentalDataRequest request = new BalanceSheetRequest.Builder()
            .symbol("IBM")
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testCashFlowRequest() {
        String expected = "https://www.alphavantage.co/query?function=CASH_FLOW&symbol=IBM&apikey=demo";
        FundamentalDataRequest request = new CashFlowRequest.Builder()
                .symbol("IBM")
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testCompanyOverviewRequest() {
        String expected = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=demo";
        FundamentalDataRequest request = new CompanyOverviewRequest.Builder()
                .symbol("IBM")
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testEarningsRequest() {
        String expected = "https://www.alphavantage.co/query?function=EARNINGS&symbol=IBM&apikey=demo";
        FundamentalDataRequest request = new EarningsRequest.Builder()
                .symbol("IBM")
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testIncomeStatementRequest() {
        String expected = "https://www.alphavantage.co/query?function=INCOME_STATEMENT&symbol=IBM&apikey=demo";
        FundamentalDataRequest request = new IncomeStatementRequest.Builder()
                .symbol("IBM")
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }
}