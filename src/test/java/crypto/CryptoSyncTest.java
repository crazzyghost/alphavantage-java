package crypto;

import static org.junit.Assert.assertNull;
import static util.TestUtils.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.cryptocurrency.Crypto.RatingRequestProxy;
import com.crazzyghost.alphavantage.cryptocurrency.response.CryptoResponse;
import com.crazzyghost.alphavantage.cryptocurrency.response.RatingResponse;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;

public class CryptoSyncTest {
 
    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("crypto");
        
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

        mockInterceptor.addRule().get(cryptoRatingUrl("BTC")).respond(stream("rating"));
        mockInterceptor.addRule().get(cryptoUrl("daily", "BTC")).respond(stream("daily"));
        mockInterceptor.addRule().get(cryptoUrl("weekly", "BTC")).respond(stream("weekly"));
        mockInterceptor.addRule().get(cryptoUrl("monthly", "BTC")).respond(stream("monthly"));
        mockInterceptor.addRule().get(cryptoIntradayUrl("BTC")).respond(stream("intraday"));

    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet(){
        new Crypto(null)
            .daily()
            .forSymbol("BTC")
            .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet(){
        new Crypto(Config.builder().build())
            .rating()
            .forSymbol("BTC")
            .fetchSync();
    }   


    @Test 
    public void testRating() throws InterruptedException {

    
        Crypto crypto = AlphaVantage.api().crypto();
        RatingRequestProxy requestProxy = crypto.rating();
        RatingResponse response = requestProxy.forSymbol("BTC").fetchSync();
        
        assertNull(response.getErrorMessage());
    }

    @Test 
    public void testDaily() throws InterruptedException {
        Crypto crypto = AlphaVantage.api().crypto();
        Crypto.DailyRequestProxy requestProxy = crypto.daily();
        CryptoResponse response = requestProxy.forSymbol("BTC")
            .market("CNY")
            .fetchSync();
        
        assertNull(response.getErrorMessage());
    }



    @Test 
    public void testWeekly() throws InterruptedException {
        Crypto crypto = AlphaVantage.api().crypto();
        Crypto.WeeklyRequestProxy requestProxy = crypto.weekly();
        CryptoResponse response = requestProxy.forSymbol("BTC")
            .market("CNY")
            .fetchSync();
        
        assertNull(response.getErrorMessage());
    }

    @Test 
    public void testMonthly() throws InterruptedException {
        
        Crypto crypto = AlphaVantage.api().crypto();
        Crypto.MonthlyRequestProxy requestProxy = crypto.monthly();
        CryptoResponse response = requestProxy.forSymbol("BTC")
            .market("CNY")
            .fetchSync();
        
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testIntraday() throws InterruptedException {

        Crypto crypto = AlphaVantage.api().crypto();
        Crypto.IntradayRequestProxy requestProxy = crypto.intraday();
        CryptoResponse response = requestProxy.forSymbol("BTC")
                .market("CNY")
                .fetchSync();

        assertNull(response.getErrorMessage());
    }

}