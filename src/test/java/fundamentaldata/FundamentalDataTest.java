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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertNotNull;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

public class FundamentalDataTest {
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
    public void testResponseUnsuccessful() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .fundamentalData()
                .companyOverview()
                .forSymbol("IBT")
                .onFailure(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testCompanyOverview() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<CompanyOverviewResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .fundamentalData()
                .companyOverview()
                .forSymbol("IBM")
                .onSuccess((CompanyOverviewResponse e) -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testBalanceSheet() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<BalanceSheetResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .fundamentalData()
                .balanceSheet()
                .forSymbol("IBM")
                .onSuccess((BalanceSheetResponse e) -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testIncomeStatement() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<IncomeStatementResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .fundamentalData()
                .incomeStatement()
                .forSymbol("IBM")
                .onSuccess((IncomeStatementResponse e) -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testEarnings() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EarningsResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .fundamentalData()
                .earnings()
                .forSymbol("IBM")
                .onSuccess((EarningsResponse e) -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testCashFlow() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<CashFlowResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .fundamentalData()
                .cashFlow()
                .forSymbol("IBM")
                .onSuccess((CashFlowResponse e) -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

}
