package forex;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.forex.Forex;
import com.crazzyghost.alphavantage.forex.response.ForexResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;
import static org.junit.Assert.assertNull;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

public class ForexTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("forex");

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(4, TimeUnit.SECONDS)
                .callTimeout(4, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(mockInterceptor)
                .build();

        config = Config.builder().key("demo").httpClient(client).build();

        AlphaVantage.api().init(config);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo")
            .respond(errorMessage)
            .code(400);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=GHS&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("monthly"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol=GHS&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("weekly"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_DAILY&outputsize=full&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("daily"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=AFN&to_symbol=USD&datatype=json&apikey=demo")
            .respond(errorMessage);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=USD&to_symbol=AFN&datatype=json&apikey=demo")
            .respond(errorMessage);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?interval=5min&function=FX_INTRADAY&outputsize=full&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("intraday"));
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new Forex(null).daily().fromSymbol("USD").toSymbol("EUR").fetch();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new Forex(Config.builder().build()).daily().fromSymbol("USD").toSymbol("EUR").fetch();
    }

    @Test
    public void testResponseUnsuccessful() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .monthly()
            .fromSymbol("EUR")
            .toSymbol("USD")
            .onFailure(e -> {
                lock.countDown();
                ref.set(e);
            }).fetch();
        lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testMonthly() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ForexResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .monthly()
            .fromSymbol("GHS")
            .toSymbol("USD")
            .dataType(DataType.JSON)
            .onSuccess(e -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    
    @Test
    public void testWeekly() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ForexResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .weekly()
            .fromSymbol("GHS")
            .toSymbol("USD")
            .dataType(DataType.JSON)
            .onSuccess(e -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testDaily() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ForexResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .daily()
            .fromSymbol("EUR")
            .toSymbol("USD")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testMonthlyError() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ForexResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .monthly()
            .fromSymbol("AFN")
            .toSymbol("USD")
            .dataType(DataType.JSON)
            .onFailure(e-> lock.countDown())
            .fetch();
            lock.await();
        assertNull(ref.get());

    }

    @Test
    public void testMonthlyErrorWithNoFailureCallback() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .monthly()
            .fromSymbol("USD")
            .toSymbol("AFN")
            .dataType(DataType.JSON)
            .onSuccess(e->lock.countDown())
            .fetch();
            lock.await();
        assertNull(ref.get());

    }

    @Test
    public void testIntraday() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ForexResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .intraday()
            .toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN) 
            .outputSize(OutputSize.FULL) 
            .onSuccess(e -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

}