package indicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.indicator.Indicator;
import com.crazzyghost.alphavantage.indicator.response.AROONResponse;
import com.crazzyghost.alphavantage.indicator.response.MACDEXTResponse;
import com.crazzyghost.alphavantage.indicator.response.MACDResponse;
import com.crazzyghost.alphavantage.indicator.response.MAMAResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.PriceOscillatorResponse;
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
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","AAPL")).respond(errorMessage).code(400);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","AAPL")).respond(errorMessage).code(400);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","MSFT")).delay(11000);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","MSFT")).delay(11000);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("EMA")).respond(getJson("ema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("WMA")).respond(getJson("wma"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("DEMA")).respond(getJson("dema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TEMA")).respond(getJson("tema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TRIMA")).respond(getJson("trima"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("KAMA")).respond(getJson("kama"));
        mockInterceptor.addRule().get(getMAMAUrl(null)).respond(getJson("mama"));
        mockInterceptor.addRule().get(getMAMAUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getMAMAUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("VWAP")).respond(getJson("vwap"));
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("VWAP","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("VWAP","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("T3")).respond(getJson("t3"));
        mockInterceptor.addRule().get(getMACDUrl(null)).respond(getJson("macd"));
        mockInterceptor.addRule().get(getMACDUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getMACDUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getMACDEXTUrl(null)).respond(getJson("macdext"));
        mockInterceptor.addRule().get(getMACDEXTUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getMACDEXTUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHUrl(null)).respond(getJson("stoch"));
        mockInterceptor.addRule().get(getSTOCHUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHFUrl(null)).respond(getJson("stochf"));
        mockInterceptor.addRule().get(getSTOCHFUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHFUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("RSI")).respond(getJson("rsi"));
        mockInterceptor.addRule().get(getSTOCHRSIUrl(null)).respond(getJson("stochrsi"));
        mockInterceptor.addRule().get(getSTOCHRSIUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHRSIUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("ADX")).respond(getJson("adx"));
        mockInterceptor.addRule().get(getPeriodicUrl("WILLR")).respond(getJson("willr"));
        mockInterceptor.addRule().get(getPeriodicUrl("ADXR")).respond(getJson("adxr"));
        mockInterceptor.addRule().get(getPriceOscillatorUrl("PPO")).respond(getJson("ppo"));
        mockInterceptor.addRule().get(getPriceOscillatorUrl("PPO","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPriceOscillatorUrl("PPO","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPriceOscillatorUrl("APO")).respond(getJson("apo"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("MOM")).respond(getJson("mom"));
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("BOP")).respond(getJson("bop"));
        mockInterceptor.addRule().get(getPeriodicUrl("CCI")).respond(getJson("cci"));
        mockInterceptor.addRule().get(getPeriodicUrl("CCI", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("CCI", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("CMO")).respond(getJson("cmo"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("ROC")).respond(getJson("roc"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("ROCR")).respond(getJson("rocr"));
        mockInterceptor.addRule().get(getPeriodicUrl("AROON")).respond(getJson("aroon"));
        mockInterceptor.addRule().get(getPeriodicUrl("AROON", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("AROON", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("AROONOSC")).respond(getJson("aroonosc"));
        mockInterceptor.addRule().get(getPeriodicUrl("MFI")).respond(getJson("mfi"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TRIX")).respond(getJson("trix"));
        mockInterceptor.addRule().get(getULTOSCUrl(null)).respond(getJson("ultosc"));
        mockInterceptor.addRule().get(getULTOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getULTOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("DX")).respond(getJson("dx"));
        mockInterceptor.addRule().get(getPeriodicUrl("MINUS_DI")).respond(getJson("minusdi"));
        mockInterceptor.addRule().get(getPeriodicUrl("PLUS_DI")).respond(getJson("plusdi"));
        mockInterceptor.addRule().get(getPeriodicUrl("MINUS_DM")).respond(getJson("minusdm"));
        mockInterceptor.addRule().get(getPeriodicUrl("PLUS_DM")).respond(getJson("plusdm"));   
    }


    private String getSimpleIndicatorRequestUrl(String function){
        return Config.BASE_URL + "function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getSimpleIndicatorRequestUrl(String function, String symbol){
        return Config.BASE_URL + "function=" + function + "&symbol="+ symbol +"&interval=weekly&datatype=json&apikey=demo";
    }

    private String getPeriodicSeriesUrl(String function){
       return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getPeriodicSeriesUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=" + symbol + "&interval=weekly&datatype=json&apikey=demo";
     }

    private String getPeriodicUrl(String function){
        return Config.BASE_URL + "time_period=60&function=" + function + "&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
 
    private String getPeriodicUrl(String function, String symbol){
        return Config.BASE_URL + "time_period=60&function=" + function + "&symbol=" + symbol + "&interval=daily&datatype=json&apikey=demo";
    }

    private String getMAMAUrl(String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&fastlimit=0.1&slowlimit=0.5&function=MAMA&symbol="+sym+"&interval=weekly&datatype=json&apikey=demo";
    }

    private String getMACDUrl(String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&function=MACD&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";
    }


    private String getMACDEXTUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&fastmatype=8&slowmatype=0&signalmatype=0&function=MACDEXT&symbol="+sym+"&interval=daily&datatype=json&apikey=demo";
    }

    private String getSTOCHUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastkperiod=5&slowkperiod=3&slowdperiod=3&slowkmatype=0&slowdmatype=0&function=STOCH&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    private String getSTOCHFUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHF&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    private String getSTOCHRSIUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "time_period=60&series_type=open&fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHRSI&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    private String getULTOSCUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "timeperiod1=7&timeperiod2=14&timeperiod3=28&function=ULTOSC&symbol="+ sym + "&interval=60min&datatype=json&apikey=demo";
    }

    private String getPriceOscillatorUrl(String function){
        return Config.BASE_URL + "series_type=open&fastperiod=0.1&slowperiod=0.2&matype=8&function=" + function +"&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }

    private String getPriceOscillatorUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&fastperiod=0.1&slowperiod=0.2&matype=8&function=" + function +"&symbol="+ symbol + "&interval=daily&datatype=json&apikey=demo";
    }

    private InputStream getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src", "test", "java", "indicator", "data", filename + ".json").toFile());
        return stream;
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet(){
        new Indicator(null)
            .sma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetch();
    }
    
    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet(){
        new Indicator(Config.builder().build())
            .sma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetch();
    }
    

    @Test
    public void testResponseUnsuccessful() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test(timeout=2000)
    public void testResponseUnsuccessfulWithoutFailureCallback() throws InterruptedException {
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetch();
    }


    @Test
    public void testResponseFailure() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("MSFT")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .onSuccess((e) -> lock.countDown())
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test(timeout = 11000)
    public void testResponseFailureWithoutFailureCallback() throws InterruptedException {
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("MSFT")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetch();
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
            .onSuccess((PeriodicSeriesResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSMAError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testSMAErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .sma()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure(null)
            .onSuccess((e) -> lock.countDown())
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNull(ref.get());
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
            .timePeriod(60)
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
            .onSuccess((MAMAResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMAMAError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .mama()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .seriesType(SeriesType.OPEN)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testMAMAErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .mama()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .onSuccess(e-> lock.countDown())
            .seriesType(SeriesType.OPEN)
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
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
            .onSuccess((SimpleIndicatorResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);

    }


    @Test
    public void testVWAPError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .vwap()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> { 
                lock.countDown();
                ref.set(e);
             })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());

    }

    @Test
    public void testVWAPErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .vwap()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .onSuccess((e) -> { 
                lock.countDown();
             })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());

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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .macd()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testMACDErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .macd()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onSuccess((e) -> lock.countDown())
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDEXTError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

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
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testMACDEXTErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

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
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onSuccess(e -> lock.countDown())
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

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
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }


    @Test
    public void testSTOCHErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

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
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onSuccess(e->lock.countDown())
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHFError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .stochf()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testSTOCHFErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .stochf()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onSuccess(e->lock.countDown())
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHRSIError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

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
            .forSymbol("GOOGL")
            .onFailure(( e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testSTOCHRSIErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

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
            .forSymbol("GOOGL")
            .onSuccess((e) -> {
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
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
    public void testPPO() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PriceOscillatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ppo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(0.1)
            .slowPeriod(0.2)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PriceOscillatorResponse e)-> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPPOError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ppo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(0.1)
            .slowPeriod(0.2)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testPPOErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ppo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(0.1)
            .slowPeriod(0.2)
            .forSymbol("GOOGL")
            .onSuccess((e) -> lock.countDown())
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
    }


    @Test
    public void testAPO() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PriceOscillatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .apo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(0.1)
            .slowPeriod(0.2)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PriceOscillatorResponse e)-> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }


    @Test
    public void testMOM() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .mom()
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
    public void testBOP() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SimpleIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .bop()
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);

    }

    @Test
    public void testCCI() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .cci()
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
    public void testCCIError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .cci()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testCCIErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .cci()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("GOOGL")
            .onSuccess((e) -> {
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
    }

    @Test
    public void testCMO() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .cmo()
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
    public void testROC() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .roc()
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
    public void testROCR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .rocr()
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
    public void testAROON() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AROONResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .aroon()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((AROONResponse e) -> {
                    lock.countDown();
                    ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAROONError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .aroon()
            .forSymbol("GOOGL")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testAROONErrorWithoutFallbackCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .aroon()
            .forSymbol("GOOGL")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onSuccess((e) -> {
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNull(ref.get());
    }

    @Test
    public void testAROONOSC() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .aroonosc()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                    lock.countDown();
                    ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMFI() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .mfi()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testTRIX() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .trix()
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
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testULTOSCError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ultosc()
            .interval(Interval.SIXTY_MIN)
            .timePeriod1(7)
            .timePeriod2(14)
            .timePeriod3(28)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testULTOSCErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .indicator()
            .ultosc()
            .interval(Interval.SIXTY_MIN)
            .timePeriod1(7)
            .timePeriod2(14)
            .timePeriod3(28)
            .forSymbol("GOOGL")
            .onSuccess((e) -> {
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
    }

    @Test
    public void testDX() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .dx()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }
    
    @Test
    public void testMINUSDI() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .minusdi()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }
    
    @Test
    public void testPLUSDI() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .plusdi()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }
    @Test
    public void testMINUSDM() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .minusdm()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPLUSDM() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .indicator()
            .plusdm()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                lock.countDown();
                ref.set(e);
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

}