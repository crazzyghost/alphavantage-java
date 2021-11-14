package fundamentaldata;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.fundamentaldata.FundamentalData;
import com.crazzyghost.alphavantage.fundamentaldata.response.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FundamentalDataSyncTest {
    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setup() throws IOException {
        TestUtils.forDirectory("fundamentaldata");

        loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(4, TimeUnit.SECONDS)
                .callTimeout(4, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(mockInterceptor)
                .build();

        config = Config.builder().key("demo").httpClient(client).build();

        AlphaVantage.api().init(config);

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBT&apikey=demo")
                .respond(errorMessage)
                .code(400);

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=demo")
                .respond(stream("companyoverview"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=BALANCE_SHEET&symbol=IBM&apikey=demo")
                .respond(stream("balancesheet"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=CASH_FLOW&symbol=IBM&apikey=demo")
                .respond(stream("cashflow"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=EARNINGS&symbol=IBM&apikey=demo")
                .respond(stream("earnings"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=INCOME_STATEMENT&symbol=IBM&apikey=demo")
                .respond(stream("incomestatement"));
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new FundamentalData(null)
                .companyOverview()
                .forSymbol("IBM")
                .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new FundamentalData(Config.builder().build())
                .companyOverview()
                .forSymbol("IBM")
                .fetchSync();
    }

    @Test
    public void testCompanyOverviewError() {
        CompanyOverviewResponse response = AlphaVantage
                .api()
                .fundamentalData()
                .companyOverview()
                .forSymbol("IBT")
                .fetchSync();

        assertNotNull(response.getErrorMessage());

    }

    @Test
    public void testCompanyOverview() {
        CompanyOverviewResponse response = AlphaVantage
                .api()
                .fundamentalData()
                .companyOverview()
                .forSymbol("IBM")
                .fetchSync();

        assertNull(response.getErrorMessage());
    }

    @Test
    public void testBalanceSheet() {
        BalanceSheetResponse response = AlphaVantage
                .api()
                .fundamentalData()
                .balanceSheet()
                .forSymbol("IBM")
                .fetchSync();

        assertNull(response.getErrorMessage());
    }

    @Test
    public void testIncomeStatement() {
        IncomeStatementResponse response = AlphaVantage
                .api()
                .fundamentalData()
                .incomeStatement()
                .forSymbol("IBM")
                .fetchSync();

        assertNull(response.getErrorMessage());
    }

    @Test
    public void testEarnings() {
        EarningsResponse response = AlphaVantage
                .api()
                .fundamentalData()
                .earnings()
                .forSymbol("IBM")
                .fetchSync();

        assertNull(response.getErrorMessage());
    }

    @Test
    public void testCashflow() {
        CashFlowResponse response = AlphaVantage
                .api()
                .fundamentalData()
                .cashFlow()
                .forSymbol("IBM")
                .fetchSync();

        assertNull(response.getErrorMessage());
    }

}
