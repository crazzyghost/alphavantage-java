package search;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.search.Search;
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
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.*;

public class SearchSyncTest {

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

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new Search(null)
                .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new Search(Config.builder().build())
                .fetchSync();
    }

    @Test
    public void testSearch() throws InterruptedException, IOException {
        mockInterceptor.addRule().get(searchUrl("tesco")).respond(stream("data"));

        SearchResponse response = AlphaVantage.api()
                .search()
                .keywords("tesco")
                .fetchSync();
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testSearchError() throws InterruptedException {
        mockInterceptor.addRule().get(searchUrl("")).respond(errorMessage);

        SearchResponse response = AlphaVantage.api()
                .search()
                .fetchSync();

        assertNotNull(response.getErrorMessage());

    }

    @Test
    public void testSearchUnsuccessful() throws InterruptedException {
        mockInterceptor.addRule().get(searchUrl("x.905")).respond(errorMessage).code(400);

        Search search = AlphaVantage.api().search();
        SearchResponse response = search
                .keywords("x.905")
                .fetchSync();

        assertNotNull(response.getErrorMessage());
    }

    @Test(expected = AlphaVantageException.class)
    public void testSearchFailure() throws InterruptedException {
        mockInterceptor.addRule().get(searchUrl("CIDR.BLOCK.50")).delay(6000).respond(errorMessage);

        Search search = AlphaVantage.api().search();
        SearchResponse response = search
                .keywords("CIDR.BLOCK.50")
                .fetchSync();

        assertNotNull(response.getErrorMessage());
    }


}