package timeseries;


import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.timeseries.TimeSeries;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;


public class TimeSeriesTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("timeseries");

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(4, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mockInterceptor)
            .build();

        config = Config.builder()
            .key("demo")
            .httpClient(client)
            .build();

        AlphaVantage.api().init(config);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY_ADJUSTED&symbol=GOOGL&datatype=json&apikey=demo")
            .respond(errorMessage)
            .code(400);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("monthly"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("weekly"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?outputsize=full&function=TIME_SERIES_DAILY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("daily"));
        
        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY_ADJUSTED&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("monthlyadjusted"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY_ADJUSTED&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("weeklyadjusted"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?outputsize=full&function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("dailyadjusted"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?interval=5min&outputsize=full&function=TIME_SERIES_INTRADAY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("intraday"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("globalquote"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=AAPL&datatype=json&apikey=demo")
            .respond(errorMessage);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=MSFT&datatype=json&apikey=demo")
            .respond(errorMessage);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=GOOGL&datatype=json&apikey=demo")
            .respond(errorMessage);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=AAPL&datatype=json&apikey=demo")
            .respond(errorMessage);


    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new TimeSeries(null)
            .daily()
            .forSymbol("USD")
            .fetch();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new TimeSeries(Config.builder().build())
            .daily()
            .forSymbol("USD")
            .fetch();
    }

    @Test
    public void testResponseUnsuccessful() throws InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .monthly()
            .adjusted()
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
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
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .monthly()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onSuccess((TimeSeriesResponse e) -> {
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
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .weekly()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onSuccess((TimeSeriesResponse e) -> {
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
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .daily()
            .forSymbol("IBM")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess((TimeSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testMonthlyAdjusted() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .monthly()
            .adjusted()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onSuccess((TimeSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    
    @Test
    public void testWeeklyAdjusted() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .weekly()
            .adjusted()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onSuccess((TimeSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testDailyAdjusted() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .daily()
            .adjusted()
            .forSymbol("IBM")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess((TimeSeriesResponse e) -> {
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
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .monthly()
            .forSymbol("AAPL")
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
            .timeSeries()
            .monthly()
            .forSymbol("MSFT")
            .dataType(DataType.JSON)
            .onSuccess(e->lock.countDown())
            .fetch();
            lock.await();
        assertNull(ref.get());

    }

    @Test
    public void testIntraday() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<TimeSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN) 
            .outputSize(OutputSize.FULL) 
            .onSuccess((TimeSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testGlobalQuote() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<QuoteResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .quote()
            .forSymbol("IBM")
            .onSuccess(e -> {
                lock.countDown();
                ref.set((QuoteResponse)e);
            })
            .fetch();
            lock.await();
        assertNotNull(ref.get());

    }


    @Test
    public void testGlobalQuoteError() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<QuoteResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .quote()
            .forSymbol("GOOGL")
            .onFailure(e->lock.countDown())
            .fetch();
            lock.await();
        assertNull(ref.get());

    }

    @Test
    public void testGlobalQuoteErrorWithNoFailureCallback() throws InterruptedException {

        final CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .timeSeries()
            .quote()
            .forSymbol("AAPL")
            .onSuccess(e->lock.countDown())
            .fetch();
            lock.await();
        assertNull(ref.get());

    }

}
