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
import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.adx.ADXResponse;
import com.crazzyghost.alphavantage.indicator.response.stoch.STOCHResponse;
import com.crazzyghost.alphavantage.indicator.response.sma.SMAResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;

public class MultipleAsyncRequestTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;
    String errorMessage = "{" + 
        "\"Information\":" + "\"The **demo** API key is for demo purposes only. Please claim your free API key at (https://www.alphavantage.co/support/#api-key) to explore our full API offerings. It takes fewer than 20 seconds, and we are committed to making it free forever.\"" +
    "}";

    @Before
    public void setUp() throws FileNotFoundException {

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mockInterceptor)
            .build();

        config = Config.builder()
            .key("demo")
            .httpClient(client)
            .build();
        
        AlphaVantage.api().init(config);

        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA")).respond(getJson("sma"));
        mockInterceptor.addRule().get(getSTOCHUrl(null)).respond(getJson("stoch"));
        mockInterceptor.addRule().get(getPeriodicUrl("ADX")).respond(getJson("adx"));
    }


    private String getPeriodicSeriesUrl(String function){
       return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getPeriodicUrl(String function){
        return Config.BASE_URL + "time_period=60&function=" + function + "&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
 
    private String getSTOCHUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastkperiod=5&slowkperiod=3&slowdperiod=3&slowkmatype=0&slowdmatype=0&function=STOCH&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    private InputStream getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src", "test", "java", "indicator", "data", filename + ".json").toFile());
        return stream;
    }


    @Test 
    public void testMultipleCalls() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(3);
        AtomicReference<PeriodicSeriesResponse> smaRef = new AtomicReference<>();
        AtomicReference<PeriodicResponse> adxRef = new AtomicReference<>();
        AtomicReference<STOCHResponse> stochRef = new AtomicReference<>();

        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess((PeriodicSeriesResponse e) -> {
                smaRef.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        AlphaVantage
            .api()
            .indicator()
            .stoch()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .slowKPeriod(3)
            .slowDPeriod(3)
            .slowKMaType(MAType.SMA)
            .slowDMaType(MAType.SMA)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onFailure((e) -> lock.countDown())
            .onSuccess((STOCHResponse e) -> {
                stochRef.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        AlphaVantage
            .api()
            .indicator()
            .adx()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                adxRef.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(smaRef.get().getClass(), SMAResponse.class);
        assertEquals(stochRef.get().getClass(), STOCHResponse.class);
        assertEquals(adxRef.get().getClass(), ADXResponse.class);
    }

   
}