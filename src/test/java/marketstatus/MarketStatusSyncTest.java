package marketstatus;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.marketstatus.MarketStatus;
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
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.*;

public class MarketStatusSyncTest {

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

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new MarketStatus(null)
                .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new MarketStatus(Config.builder().build())
                .fetchSync();
    }

    @Test
    public void testMarketStatus() throws InterruptedException, IOException {
        mockInterceptor.addRule().get(marketStatusUrl()).respond(stream("data"));

        MarketStatusResponse response = AlphaVantage.api()
                .marketStatus()
                .fetchSync();
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testMarketStatusError() throws InterruptedException {
        mockInterceptor.addRule().get(marketStatusUrl()).respond(errorMessage);

        MarketStatusResponse response = AlphaVantage.api()
                .marketStatus()
                .fetchSync();

        assertNotNull(response.getErrorMessage());

    }

    @Test
    public void testMarketStatusUnsuccessful() throws InterruptedException {
        mockInterceptor.addRule().get(marketStatusUrl()).respond(errorMessage).code(400);

        MarketStatus marketStatus = AlphaVantage.api().marketStatus();
        MarketStatusResponse response = marketStatus
                .fetchSync();

        assertNotNull(response.getErrorMessage());
    }

    @Test(expected = AlphaVantageException.class)
    public void testMarketStatusFailure() throws InterruptedException {
        mockInterceptor.addRule().get(marketStatusUrl()).delay(6000).respond(errorMessage);

        MarketStatus marketStatus = AlphaVantage.api().marketStatus();
        MarketStatusResponse response = marketStatus
                .fetchSync();

        assertNotNull(response.getErrorMessage());
    }


}