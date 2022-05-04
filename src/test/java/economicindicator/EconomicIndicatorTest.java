package economicindicator;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.economicindicator.EconomicIndicator;
import com.crazzyghost.alphavantage.economicindicator.response.EconomicIndicatorResponse;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.Maturity;
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

import static org.junit.Assert.*;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

public class EconomicIndicatorTest {
    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setup() throws IOException {
        TestUtils.forDirectory("economicindicator");

        loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(4, TimeUnit.SECONDS)
                .callTimeout(4, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(mockInterceptor)
                .build();

        config = Config.builder().key("demo").httpClient(client).build();

        AlphaVantage.api().init(config);

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?interval=quarterly&function=REAL_GDP&datatype=json&apikey=demo")
                .respond(errorMessage)
                .code(400);

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=REAL_GDP&datatype=json&apikey=demo")
                .respond(stream("realgdp"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?interval=annual&function=REAL_GDP&datatype=json&apikey=demo")
                .respond(stream("realgdp"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=REAL_GDP_PER_CAPITA&datatype=json&apikey=demo")
                .respond(stream("realgdppercapita"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?interval=monthly&maturity=7year&function=TREASURY_YIELD&datatype=json&apikey=demo")
                .respond(stream("treasuryyield"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?interval=monthly&function=FEDERAL_FUNDS_RATE&datatype=json&apikey=demo")
                .respond(stream("federalfundsrate"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?interval=monthly&function=CPI&datatype=json&apikey=demo")
                .respond(stream("cpi"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=INFLATION&datatype=json&apikey=demo")
                .respond(stream("inflation"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=INFLATION_EXPECTATION&datatype=json&apikey=demo")
                .respond(stream("inflationexpectation"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=CONSUMER_SENTIMENT&datatype=json&apikey=demo")
                .respond(stream("consumersentiment"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=RETAIL_SALES&datatype=json&apikey=demo")
                .respond(stream("retailsales"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=DURABLES&datatype=json&apikey=demo")
                .respond(stream("durables"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=UNEMPLOYMENT&datatype=json&apikey=demo")
                .respond(stream("unemployment"));

        mockInterceptor.addRule()
                .get("https://www.alphavantage.co/query?function=NONFARM_PAYROLL&datatype=json&apikey=demo")
                .respond(stream("nonfarmpayroll"));

    }


    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new EconomicIndicator(null)
                .realGdp()
                .interval(Interval.QUARTERLY)
                .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new EconomicIndicator(Config.builder().build())
                .realGdp()
                .interval(Interval.QUARTERLY)
                .fetchSync();
    }

    @Test
    public void testResponseUnsuccessful() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .realGdp()
                .interval(Interval.QUARTERLY)
                .onFailure(e-> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testReadGdp() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .realGdp()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testRealGdpSync() {

       EconomicIndicatorResponse response =  AlphaVantage
                .api()
                .economicIndicator()
                .realGdp()
                .interval(Interval.ANNUAL)
                .fetchSync();

        assertNotNull(response);
    }

    @Test
    public void testRealGdpPerCapita() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .realGdpPerCapita()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testTreasuryYield() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .treasuryYield()
                .interval(Interval.MONTHLY)
                .maturity(Maturity.SEVEN_YEAR)
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testFederalFundsRate() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .federalFundsRate()
                .interval(Interval.MONTHLY)
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testCpi() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .cpi()
                .interval(Interval.MONTHLY)
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testInflation() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .inflation()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testInflationExpectation() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .inflationExpectation()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testConsumerSentiment() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .consumerSentiment()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testRetailSales() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .retailSales()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testDurableGoodsOrders() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .durables()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testUnemploymentRate() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .unemployment()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

    @Test
    public void testNonFarmPayroll() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<EconomicIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
                .api()
                .economicIndicator()
                .nonFarmPayroll()
                .onSuccess(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();
        lock.await();

        assertNotNull(ref.get());
        EconomicIndicatorResponse response = ref.get();

        assertNull(response.getErrorMessage());
        assertEquals(response.getData().size(), 2);
        assertNotNull(response.toString());
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());

    }

}
