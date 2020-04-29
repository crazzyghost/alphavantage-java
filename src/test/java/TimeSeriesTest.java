import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


public class TimeSeriesTest {

    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setUp(){

        final String API_KEY = System.getenv("ALPHAVANTAGE_API_KEY");

        Config config = Config.builder()
                .key(API_KEY)
                .timeOut(10)
                .build();

        AlphaVantage.api().init(config);

    }

    @AfterClass
    public static void tearDown(){

    }

    @Test
    public void timeSeriesDaily() throws InterruptedException {

        AtomicReference<TimeSeriesResponse> response = new AtomicReference<>();

        Fetcher.FailureCallback failureCallback = (e) -> lock.countDown();

        Fetcher.SuccessCallback<TimeSeriesResponse> successCallback = (e) -> {
            lock.countDown();
            response.set(e);
        };

        AlphaVantage.api()
                .timeSeries()
                .daily()
                .forSymbol("GOOGL")
                .outputSize(OutputSize.COMPACT)
                .onFailure(failureCallback)
                .onSuccess(successCallback)
                .fetch();

        lock.await(10, TimeUnit.SECONDS);
        assertNotNull(response.get());
    }

    @Test
    public void timeSeriesDailyAdjusted() throws InterruptedException {

        AtomicReference<TimeSeriesResponse> response = new AtomicReference<>();

        Fetcher.FailureCallback failureCallback = (e) -> lock.countDown();

        Fetcher.SuccessCallback<TimeSeriesResponse> successCallback = (e) -> {
            lock.countDown();
            response.set(e);
        };

        AlphaVantage.api()
                .timeSeries()
                .daily()
                .adjusted()
                .forSymbol("GOOGL")
                .outputSize(OutputSize.COMPACT)
                .onFailure(failureCallback)
                .onSuccess(successCallback)
                .fetch();

        lock.await(10, TimeUnit.SECONDS);
        assertNotNull(response.get());
    }

    @Test
    public void timeSeriesWeekly() throws InterruptedException {

        AtomicReference<TimeSeriesResponse> response = new AtomicReference<>();

        Fetcher.FailureCallback failureCallback = (e) -> lock.countDown();

        Fetcher.SuccessCallback<TimeSeriesResponse> successCallback = (e) -> {
            lock.countDown();
            response.set(e);
        };

        AlphaVantage.api()
                .timeSeries()
                .weekly()
                .forSymbol("GOOGL")
                .onFailure(failureCallback)
                .onSuccess(successCallback)
                .fetch();

        lock.await(10, TimeUnit.SECONDS);
        assertNotNull(response.get());
    }

    @Test
    public void timeSeriesIntraday() throws InterruptedException {

        AtomicReference<TimeSeriesResponse> response = new AtomicReference<>();

        Fetcher.FailureCallback failureCallback = (e) -> lock.countDown();

        Fetcher.SuccessCallback<TimeSeriesResponse> successCallback = (e) -> {
            lock.countDown();
            response.set(e);
        };

        AlphaVantage.api()
                .timeSeries()
                .intraday()
                .forSymbol("MSFT")
                .onFailure(failureCallback)
                .onSuccess(successCallback)
                .fetch();

        lock.await(10, TimeUnit.SECONDS);
        assertNotNull(response.get());
    }

    @Test
    public void timeSeriesMonthly() throws InterruptedException {

        AtomicReference<TimeSeriesResponse> response = new AtomicReference<>();

        Fetcher.FailureCallback failureCallback = (e) -> lock.countDown();

        Fetcher.SuccessCallback<TimeSeriesResponse> successCallback = (e) -> {
            lock.countDown();
            response.set(e);
        };

        AlphaVantage.api()
                .timeSeries()
                .monthly()
                .dataType(DataType.JSON)
                .forSymbol("MSFT")
                .onFailure(failureCallback)
                .onSuccess(successCallback)
                .fetch();

        lock.await(10, TimeUnit.SECONDS);
        assertNotNull(response.get());
    }

}
