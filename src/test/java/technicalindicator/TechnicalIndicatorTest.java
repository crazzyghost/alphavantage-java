package technicalindicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.technicalindicator.TechnicalIndicator;
import com.crazzyghost.alphavantage.technicalindicator.response.adosc.ADOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.aroon.AROONResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.bbands.BBANDSResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ema.EMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htphasor.HTPHASORResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htsine.HTSINEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDEXTResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mama.MAMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.sar.SARResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochf.STOCHFResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochrsi.STOCHRSIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stoch.STOCHResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ultosc.ULTOSCResponse;
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

public class TechnicalIndicatorTest {

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
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("VWAP")).respond(getJson("vwap"));
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("VWAP","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("VWAP","GOOGL")).respond(errorMessage);
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
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("BOP")).respond(getJson("bop"));
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
        mockInterceptor.addRule().get(getBBANDSUrl(null)).respond(getJson("bbands"));
        mockInterceptor.addRule().get(getBBANDSUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getBBANDSUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("MIDPOINT")).respond(getJson("midpoint"));
        mockInterceptor.addRule().get(getPeriodicUrl("MIDPRICE")).respond(getJson("midprice"));
        mockInterceptor.addRule().get(getSARUrl(null)).respond(getJson("sar"));
        mockInterceptor.addRule().get(getSARUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSARUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("TRANGE")).respond(getJson("trange"));
        mockInterceptor.addRule().get(getPeriodicUrl("ATR")).respond(getJson("atr"));
        mockInterceptor.addRule().get(getPeriodicUrl("NATR")).respond(getJson("natr"));
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("AD")).respond(getJson("ad"));
        mockInterceptor.addRule().get(getADOSCUrl(null)).respond(getJson("adosc"));
        mockInterceptor.addRule().get(getADOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getADOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("OBV")).respond(getJson("obv"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDLINE")).respond(getJson("httrendline"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDLINE", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDLINE", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_SINE")).respond(getJson("htsine"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_SINE", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_SINE", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDMODE")).respond(getJson("httrendmode"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_DCPERIOD")).respond(getJson("htdcperiod"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_DCPHASE")).respond(getJson("htdcphase"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_PHASOR")).respond(getJson("htphasor"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_PHASOR", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_PHASOR", "GOOGL")).respond(errorMessage);

    }


    private String getSimpleTechnicalIndicatorRequestUrl(String function){
        return Config.BASE_URL + "function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    private String getSimpleTechnicalIndicatorRequestUrl(String function, String symbol){
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

    private String getSeriesUrl(String function){
        return Config.BASE_URL + "series_type=open&function=" + function + "&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
 
    private String getSeriesUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&function=" + function + "&symbol=" + symbol + "&interval=daily&datatype=json&apikey=demo";
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
        return Config.BASE_URL + "series_type=open&fastperiod=10&slowperiod=26&matype=8&function=" + function +"&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }

    private String getPriceOscillatorUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&fastperiod=10&slowperiod=26&matype=8&function=" + function +"&symbol="+ symbol + "&interval=daily&datatype=json&apikey=demo";
    }

    private String getBBANDSUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&time_period=60&nbdevup=4&nbdevdn=4&matype=0&function=BBANDS&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";    
    }

    private String getSARUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "acceleration=0.02&maximum=0.5&function=SAR&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";    
    }

    private String getADOSCUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastperiod=3&slowperiod=10&function=ADOSC&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";    
    }


    private InputStream getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src", "test", "java", "technicalindicator", "data", filename + ".json").toFile());
        return stream;
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet(){
        new TechnicalIndicator(null)
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
        new TechnicalIndicator(Config.builder().build())
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
            .technicalIndicator()
            .sma()
            .forSymbol("AAPL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test(timeout=2000)
    public void testResponseUnsuccessfulWithoutFailureCallback() throws InterruptedException {
        AlphaVantage.api()
            .technicalIndicator()
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
            .technicalIndicator()
            .sma()
            .forSymbol("MSFT")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .sma()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .sma()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .ema()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess((EMAResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .wma()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .dema()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .tema()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .trima()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60).onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .kama()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .mama()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .onSuccess((MAMAResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .mama()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .seriesType(SeriesType.OPEN)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
        AtomicReference<SimpleTechnicalIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .vwap()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .onSuccess((SimpleTechnicalIndicatorResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .vwap()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> { 
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .t3()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .macd()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
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
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
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
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .stochf()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .onFailure((e) -> lock.countDown())
            .onSuccess((STOCHFResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .stochf()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .rsi()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .stochrsi()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("GOOGL")
            .onFailure(( e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .willr()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .adx()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .adxr()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .ppo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(10)
            .slowPeriod(26)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PriceOscillatorResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .ppo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(10)
            .slowPeriod(26)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .ppo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(10)
            .slowPeriod(26)
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
            .technicalIndicator()
            .apo()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(10)
            .slowPeriod(26)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PriceOscillatorResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .mom()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBOP() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SimpleTechnicalIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .bop()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> lock.countDown())
            .onSuccess((SimpleTechnicalIndicatorResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .cci()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .cci()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .cmo()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .roc()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .rocr()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .aroon()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((AROONResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .aroon()
            .forSymbol("GOOGL")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .aroonosc()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .mfi()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .trix()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .ultosc()
            .interval(Interval.SIXTY_MIN)
            .timePeriod1(7)
            .timePeriod2(14)
            .timePeriod3(28)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((ULTOSCResponse e)-> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .ultosc()
            .interval(Interval.SIXTY_MIN)
            .timePeriod1(7)
            .timePeriod2(14)
            .timePeriod3(28)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
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
            .technicalIndicator()
            .dx()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .minusdi()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .plusdi()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .minusdm()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
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
            .technicalIndicator()
            .plusdm()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBBANDS() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<BBANDSResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .bbands()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .seriesType(SeriesType.OPEN)
            .nbdevdn(4)
            .nbdevup(4)
            .maType(MAType.SMA)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((BBANDSResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBBANDSError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .bbands()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .seriesType(SeriesType.OPEN)
            .nbdevdn(4)
            .nbdevup(4)
            .maType(MAType.SMA)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testBBANDSErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .bbands()
            .interval(Interval.DAILY)
            .timePeriod(60)
            .seriesType(SeriesType.OPEN)
            .nbdevdn(4)
            .nbdevup(4)
            .maType(MAType.SMA)
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
    public void testMIDPOINT() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .technicalIndicator()
            .midpoint()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .onSuccess((PeriodicSeriesResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMIDPRICE() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .technicalIndicator()
            .midprice()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSAR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SARResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .sar()
            .interval(Interval.DAILY)
            .acceleration(0.02)
            .maximum(0.50)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((SARResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSARError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .sar()
            .interval(Interval.DAILY)
            .acceleration(0.02)
            .maximum(0.50)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testSARErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .sar()
            .interval(Interval.DAILY)
            .acceleration(0.02)
            .maximum(0.50)
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
    public void testTRANGE() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SimpleTechnicalIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .trange()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> lock.countDown())
            .onSuccess((SimpleTechnicalIndicatorResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);

    }

    @Test
    public void testATR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .technicalIndicator()
            .atr()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testNATR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
        AlphaVantage.api()
            .technicalIndicator()
            .natr()
            .forSymbol("IBM")
            .interval(Interval.DAILY)
            .timePeriod(60)
            .onFailure((e) -> lock.countDown())
            .onSuccess((PeriodicResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();
        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAD() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SimpleTechnicalIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .ad()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> lock.countDown())
            .onSuccess((SimpleTechnicalIndicatorResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);

    }

    @Test
    public void testADOSC() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ADOSCResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .adosc()
            .interval(Interval.DAILY)
            .fastPeriod(3)
            .slowPeriod(10)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((ADOSCResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADOSCError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .adosc()
            .interval(Interval.DAILY)
            .fastPeriod(3)
            .slowPeriod(10)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testADOSCErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .adosc()
            .interval(Interval.DAILY)
            .fastPeriod(3)
            .slowPeriod(10)
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
    public void testOBV() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SimpleTechnicalIndicatorResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .obv()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .onFailure((e) -> lock.countDown())
            .onSuccess((SimpleTechnicalIndicatorResponse e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);

    }

    @Test
    public void testHTTRENDLINE() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .httrendline()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((SeriesResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTTRENDLINEError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .httrendline()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testHTTRENDLINEErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .httrendline()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
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
    public void testHTSINE() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<HTSINEResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htsine()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((HTSINEResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTSINEError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htsine()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testHTSINEErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htsine()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
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
    public void testHTRENDMODE() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .httrendmode()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((SeriesResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTDCPERIOD() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htdcperiod()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((SeriesResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTDCPHASE() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SeriesResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htdcphase()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((SeriesResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTPHASOR() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<HTPHASORResponse> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htphasor()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .onFailure((e) -> lock.countDown())
            .onSuccess((HTPHASORResponse e)-> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertTrue(ref.get().toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(ref.get().getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTPHASORError() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htphasor()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("GOOGL")
            .onFailure((e) -> {
                ref.set(e);
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testHTPHASORErrorWithoutFailureCallback() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage
            .api()
            .technicalIndicator()
            .htphasor()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("GOOGL")
            .onSuccess((e) -> {
                lock.countDown();
            })
            .dataType(DataType.JSON)
            .fetch();

        lock.await();
        assertNull(ref.get());
    }


}