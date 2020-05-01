package indicator;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.indicator.response.MAMAResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.SeriesType;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;

public class IndicatorTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws FileNotFoundException {

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor).addInterceptor(mockInterceptor).build();

        config = Config.builder().key("demo").httpClient(client).build();
        AlphaVantage.api().init(config);

        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA")).respond(getJson("sma"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("EMA")).respond(getJson("ema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("WMA")).respond(getJson("wma"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("DEMA")).respond(getJson("dema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TEMA")).respond(getJson("tema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TRIMA")).respond(getJson("trima"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("KAMA")).respond(getJson("kama"));
        mockInterceptor.addRule().get(getMAMAUrl()).respond(getJson("mama"));
    }


    private String getPeriodicSeriesUrl(String function){
       return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getMAMAUrl(){
        return Config.BASE_URL + "series_type=open&fastlimit=0.1&slowlimit=0.5&function=MAMA&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private InputStream getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src", "test", "java", "indicator", "data", filename + ".json").toFile());
        return stream;
    }

    @Test
    public void testSMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                    lock.countDown();
                    ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testEMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ema()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testWMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .wma()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testDEMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .dema()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testTEMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .tema()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testTRIMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .trima()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testKAMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .kama()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }
    
    @Test
    public void testMAMA() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<MAMAResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .mama()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .seriesType(SeriesType.OPEN)
            .onFailure((e) -> lock.countDown())
            .onSuccess((MAMAResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }
}