package marketstatus;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.marketstatus.Market;
import com.crazzyghost.alphavantage.marketstatus.MarketStatus;
import com.crazzyghost.alphavantage.marketstatus.MarketStatusRequest;
import com.crazzyghost.alphavantage.marketstatus.MarketStatusResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static util.TestUtils.*;

public class MarketStatusTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("marketstatus");

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(4, TimeUnit.SECONDS)
                .callTimeout(4, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(mockInterceptor)
                .build();

        config = Config.builder()
                .key("demo")
                .httpClient(client)
                .build();

        AlphaVantage.api().init(config);
    }


    @Test
    public void testRequest() {
        String expected = "function=MARKET_STATUS&apikey=demo";
        MarketStatusRequest request = new MarketStatusRequest.Builder()
                .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testResponse() throws IOException {
        MarketStatusResponse response = MarketStatusResponse.of(json("data"));
        List<Market> markets = response.getMarkets();

        Market market = markets.get(0);
        assertEquals("market_type mismatch", "Equity", market.getMarketType());
        assertEquals("region mismatch", "United States", market.getRegion());
        assertEquals("primary_exchanges mismatch", "NASDAQ, NYSE, AMEX, BATS", market.getPrimaryExchanges());
        assertEquals("local_open mismatch", "09:30", market.getLocalOpen());
        assertEquals("local_close mismatch", "16:15", market.getLocalClose());
        assertEquals("current_status mismatch", "closed", market.getCurrentStatus());
        assertEquals("notes mismatch", "", market.getNotes());

        assertTrue(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testResponseError() throws IOException {
        MarketStatusResponse response = MarketStatusResponse.of(error());
        assertNotNull(response.getErrorMessage());
    }

    @Test
    public void testEmptyResponseError() throws IOException {
        MarketStatusResponse response = MarketStatusResponse.of(empty());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }


    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new MarketStatus(null)
                .fetch();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new MarketStatus(Config.builder().build())
                .fetch();
    }

    @Test
    public void testMarketStatus() throws InterruptedException, IOException {
        mockInterceptor.addRule().get(marketStatusUrl()).respond(stream("data"));

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<MarketStatusResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
                .marketStatus()
                .onSuccess((e) -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testMarketStatusError() throws InterruptedException {
        mockInterceptor.addRule().get(marketStatusUrl()).respond(errorMessage);

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
                .marketStatus()
                .onFailure(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testMarketStatusUnsuccessful() throws InterruptedException {
        mockInterceptor.addRule().get(marketStatusUrl()).respond(errorMessage).code(400);

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
                .marketStatus()
                .onFailure(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testMarketStatusFailure() throws InterruptedException {
        mockInterceptor.addRule().get(marketStatusUrl()).delay(6000).respond(errorMessage);

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
                .marketStatus()
                .onSuccess(e -> lock.countDown())
                .onFailure(e -> {
                    ref.set(e);
                    lock.countDown();
                })
                .fetch();

        lock.await();
        assertNotNull(ref.get());
    }


}
