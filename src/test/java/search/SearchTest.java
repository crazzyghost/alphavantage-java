package search;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.marketstatus.MarketStatus;
import com.crazzyghost.alphavantage.search.SearchRequest;
import com.crazzyghost.alphavantage.search.SearchResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
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
import static util.TestUtils.*;

public class SearchTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("search");

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
        String expected = "function=SYMBOL_SEARCH&keywords=microsft&apikey=demo";
        SearchRequest request = new SearchRequest.Builder()
                .keywords("microsft")
                .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testResponse() throws IOException {
        SearchResponse response = SearchResponse.of(json("data"));
        assertTrue(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testResponseError() throws IOException {
        SearchResponse response = SearchResponse.of(error());
        assertNotNull(response.getErrorMessage());
    }

    @Test
    public void testEmptyResponseError() throws IOException {
        SearchResponse response = SearchResponse.of(empty());
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
        mockInterceptor.addRule().get(searchUrl("tesco")).respond(stream("data"));

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SearchResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
                .search()
                .keywords("tesco")
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
        mockInterceptor.addRule().get(searchUrl("")).respond(errorMessage);

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
                .search()
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
        mockInterceptor.addRule().get(searchUrl("x.905")).respond(errorMessage).code(400);

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
                .search()
                .keywords("x.905")
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
        mockInterceptor.addRule().get(searchUrl("CIDR.BLOCK.50")).delay(6000).respond(errorMessage);

        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
                .search()
                .keywords("CIDR.BLOCK.50")
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
