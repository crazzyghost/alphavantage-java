package crypto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.cryptoRatingUrl;
import static util.TestUtils.cryptoUrl;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
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

public class CryptoTest {
 
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

        mockInterceptor.addRule().get(cryptoRatingUrl("BSV")).respond(errorMessage).code(400);
        mockInterceptor.addRule().get(cryptoRatingUrl("BTC")).respond(stream("rating"));
        mockInterceptor.addRule().get(cryptoRatingUrl("XPR")).respond(errorMessage);
        mockInterceptor.addRule().get(cryptoRatingUrl("BCH")).respond(errorMessage);
        mockInterceptor.addRule().get(cryptoUrl("daily", "BTC")).respond(stream("daily"));
        mockInterceptor.addRule().get(cryptoUrl("daily", "XPR")).respond(errorMessage);
        mockInterceptor.addRule().get(cryptoUrl("daily", "LTC")).respond(errorMessage);
        mockInterceptor.addRule().get(cryptoUrl("weekly", "BTC")).respond(stream("weekly"));
        mockInterceptor.addRule().get(cryptoUrl("monthly", "BTC")).respond(stream("monthly"));

    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet(){
        new Crypto(null)
            .daily()
            .forSymbol("BTC")
            .fetch();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet(){
        new Crypto(Config.builder().build())
            .rating()
            .forSymbol("BTC")
            .fetch();
    }   

    @Test 
    public void testResponseUnsuccessful() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .rating()
            .forSymbol("BSV")
            .onFailure(e ->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());

    }


    @Test 
    public void testRating() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<RatingResponse> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .rating()
            .forSymbol("BTC")
            .onSuccess((RatingResponse e)->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test 
    public void testRatingError() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .rating()
            .forSymbol("XPR")
            .onFailure(e ->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());

    }

    @Test 
    public void testRatingErrorWithNoFailureCallback() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .rating()
            .forSymbol("BCH")
            .onSuccess(e->lock.countDown())
            .fetch();
        lock.await();
        assertNull(ref.get());

    }

    @Test 
    public void testDaily() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<CryptoResponse> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .daily()
            .forSymbol("BTC")
            .market("CNY")
            .onSuccess((CryptoResponse e)->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }


    @Test 
    public void testDailyError() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .daily()
            .forSymbol("XPR")
            .market("CNY")
            .onFailure(e ->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test 
    public void testDailyErrorWithoutFailureCallback() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .daily()
            .forSymbol("LTC")
            .market("CNY")
            .onSuccess(e->lock.countDown())
            .fetch();
        lock.await();
        assertNull(ref.get());
    }


    @Test 
    public void testWeekly() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<CryptoResponse> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .weekly()
            .forSymbol("BTC")
            .market("CNY")
            .onSuccess((CryptoResponse e)->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test 
    public void testMonthly() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<CryptoResponse> ref = new AtomicReference<>();
        
        AlphaVantage.api()
            .crypto()
            .monthly()
            .forSymbol("BTC")
            .market("CNY")
            .onSuccess((CryptoResponse e)->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

}