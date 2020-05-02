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
import com.crazzyghost.alphavantage.indicator.response.MACDEXTResponse;
import com.crazzyghost.alphavantage.indicator.response.MACDResponse;
import com.crazzyghost.alphavantage.indicator.response.MAMAResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.STOCHFResponse;
import com.crazzyghost.alphavantage.indicator.response.STOCHRSIResponse;
import com.crazzyghost.alphavantage.indicator.response.STOCHResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.ULTOSCResponse;
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
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("VWAP")).respond(getJson("vwap"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("T3")).respond(getJson("t3"));
        mockInterceptor.addRule().get(getMACDUrl()).respond(getJson("macd"));
        mockInterceptor.addRule().get(getMACDEXTUrl()).respond(getJson("macdext"));
        mockInterceptor.addRule().get(getSTOCHUrl()).respond(getJson("stoch"));
        mockInterceptor.addRule().get(getSTOCHFUrl()).respond(getJson("stochf"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("RSI")).respond(getJson("rsi"));
        mockInterceptor.addRule().get(getSTOCHRSIUrl()).respond(getJson("stochrsi"));
        mockInterceptor.addRule().get(getPeriodicUrl("ADX")).respond(getJson("adx"));
        mockInterceptor.addRule().get(getPeriodicUrl("WILLR")).respond(getJson("willr"));
        mockInterceptor.addRule().get(getPeriodicUrl("ADXR")).respond(getJson("adxr"));
        mockInterceptor.addRule().get(getULTOSCUrl()).respond(getJson("ultosc"));
    }


    private String getSimpleIndicatorRequestUrl(String function){
        return Config.BASE_URL + "function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getPeriodicSeriesUrl(String function){
       return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getPeriodicUrl(String function){
        return Config.BASE_URL + "time_period=60&function=" + function + "&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
 
    private String getMAMAUrl(){
        return Config.BASE_URL + "series_type=open&fastlimit=0.1&slowlimit=0.5&function=MAMA&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getMACDUrl(){
        return Config.BASE_URL + "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&function=MACD&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
    
    private String getMACDEXTUrl(){
        return Config.BASE_URL + "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&fastmatype=8&slowmatype=0&signalmatype=0&function=MACDEXT&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }

    private String getSTOCHUrl(){
        return Config.BASE_URL + "fastkperiod=5&slowkperiod=3&slowdperiod=3&slowkmatype=0&slowdmatype=0&function=STOCH&symbol=IBM&interval=60min&datatype=json&apikey=demo";
    }
    
    private String getSTOCHFUrl(){
        return Config.BASE_URL + "fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHF&symbol=IBM&interval=60min&datatype=json&apikey=demo";
    }

    private String getSTOCHRSIUrl(){
        return Config.BASE_URL + "time_period=60&series_type=open&fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHRSI&symbol=IBM&interval=60min&datatype=json&apikey=demo";
    }

    private String getULTOSCUrl(){
        return Config.BASE_URL + "timeperiod1=7&timeperiod2=14&timeperiod3=28&function=ULTOSC&symbol=IBM&interval=60min&datatype=json&apikey=demo";
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

    @Test
    public void testVWAP() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SimpleIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .vwap()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> lock.countDown())
            .onSuccess((SimpleIndicatorResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);

    }

    @Test
    public void testT3() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .t3()
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
    public void testMACD() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<MACDResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .macd()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onFailure((e) -> lock.countDown())
            .onSuccess((MACDResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDEXT() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<MACDEXTResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .macdext()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .slowMaType(MAType.SMA)
            .fastMaType(MAType.MAMA)
            .signalMaType(MAType.SMA)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onFailure((e) -> lock.countDown())
            .onSuccess((MACDEXTResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCH() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<STOCHResponse> ref = new AtomicReference<>();

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
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHF() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<STOCHFResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .stochf()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onFailure((e) -> lock.countDown())
            .onSuccess((STOCHFResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testRSI() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .rsi()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHRSI() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<STOCHRSIResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .stochrsi()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((STOCHRSIResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testWILLR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .willr()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADX() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .adx()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADXR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .adxr()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testULTOSC() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ULTOSCResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ultosc()
            .interval(Interval.SIXTY_MIN)
            .timePeriod1(7)
            .timePeriod2(14)
            .timePeriod3(28)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((ULTOSCResponse e)-> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }
}