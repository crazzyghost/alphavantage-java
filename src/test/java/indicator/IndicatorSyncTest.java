package indicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static util.TestUtils.errorMessage;
import static util.TestUtils.getADOSCUrl;
import static util.TestUtils.getBBANDSUrl;
import static util.TestUtils.getMACDEXTUrl;
import static util.TestUtils.getMACDUrl;
import static util.TestUtils.getMAMAUrl;
import static util.TestUtils.getPeriodicSeriesUrl;
import static util.TestUtils.getPeriodicUrl;
import static util.TestUtils.getPriceOscillatorUrl;
import static util.TestUtils.getSARUrl;
import static util.TestUtils.getSTOCHFUrl;
import static util.TestUtils.getSTOCHRSIUrl;
import static util.TestUtils.getSTOCHUrl;
import static util.TestUtils.getSeriesUrl;
import static util.TestUtils.getSimpleIndicatorRequestUrl;
import static util.TestUtils.getULTOSCUrl;
import static util.TestUtils.stream;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.indicator.Indicator;
import com.crazzyghost.alphavantage.indicator.response.ADOSCResponse;
import com.crazzyghost.alphavantage.indicator.response.AROONResponse;
import com.crazzyghost.alphavantage.indicator.response.BBANDSResponse;
import com.crazzyghost.alphavantage.indicator.response.HTPHASORResponse;
import com.crazzyghost.alphavantage.indicator.response.HTSINEResponse;
import com.crazzyghost.alphavantage.indicator.response.MACDEXTResponse;
import com.crazzyghost.alphavantage.indicator.response.MACDResponse;
import com.crazzyghost.alphavantage.indicator.response.MAMAResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SARResponse;
import com.crazzyghost.alphavantage.indicator.response.STOCHFResponse;
import com.crazzyghost.alphavantage.indicator.response.STOCHRSIResponse;
import com.crazzyghost.alphavantage.indicator.response.STOCHResponse;
import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
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
import util.TestUtils;


public class IndicatorSyncTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;
    
    @Before
    public void setUp() throws IOException {

        TestUtils.forDirectory("indicator");

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

        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA")).respond(stream("sma"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("EMA")).respond(stream("ema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("WMA")).respond(stream("wma"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("DEMA")).respond(stream("dema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TEMA")).respond(stream("tema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TRIMA")).respond(stream("trima"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("KAMA")).respond(stream("kama"));
        mockInterceptor.addRule().get(getMAMAUrl(null)).respond(stream("mama"));
        mockInterceptor.addRule().get(getMAMAUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("VWAP")).respond(stream("vwap"));
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("VWAP","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("T3")).respond(stream("t3"));
        mockInterceptor.addRule().get(getMACDUrl(null)).respond(stream("macd"));
        mockInterceptor.addRule().get(getMACDUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getMACDEXTUrl(null)).respond(stream("macdext"));
        mockInterceptor.addRule().get(getMACDEXTUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHUrl(null)).respond(stream("stoch"));
        mockInterceptor.addRule().get(getSTOCHUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSTOCHFUrl(null)).respond(stream("stochf"));
        mockInterceptor.addRule().get(getSTOCHFUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("RSI")).respond(stream("rsi"));
        mockInterceptor.addRule().get(getSTOCHRSIUrl(null)).respond(stream("stochrsi"));
        mockInterceptor.addRule().get(getSTOCHRSIUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("ADX")).respond(stream("adx"));
        mockInterceptor.addRule().get(getPeriodicUrl("WILLR")).respond(stream("willr"));
        mockInterceptor.addRule().get(getPeriodicUrl("ADXR")).respond(stream("adxr"));
        mockInterceptor.addRule().get(getPriceOscillatorUrl("PPO")).respond(stream("ppo"));
        mockInterceptor.addRule().get(getPriceOscillatorUrl("PPO","GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPriceOscillatorUrl("APO")).respond(stream("apo"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("MOM")).respond(stream("mom"));
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("BOP")).respond(stream("bop"));
        mockInterceptor.addRule().get(getPeriodicUrl("CCI")).respond(stream("cci"));
        mockInterceptor.addRule().get(getPeriodicUrl("CCI", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("CMO")).respond(stream("cmo"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("ROC")).respond(stream("roc"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("ROCR")).respond(stream("rocr"));
        mockInterceptor.addRule().get(getPeriodicUrl("AROON")).respond(stream("aroon"));
        mockInterceptor.addRule().get(getPeriodicUrl("AROON", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("AROONOSC")).respond(stream("aroonosc"));
        mockInterceptor.addRule().get(getPeriodicUrl("MFI")).respond(stream("mfi"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TRIX")).respond(stream("trix"));
        mockInterceptor.addRule().get(getULTOSCUrl(null)).respond(stream("ultosc"));
        mockInterceptor.addRule().get(getULTOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicUrl("DX")).respond(stream("dx"));
        mockInterceptor.addRule().get(getPeriodicUrl("MINUS_DI")).respond(stream("minusdi"));
        mockInterceptor.addRule().get(getPeriodicUrl("PLUS_DI")).respond(stream("plusdi"));
        mockInterceptor.addRule().get(getPeriodicUrl("MINUS_DM")).respond(stream("minusdm"));
        mockInterceptor.addRule().get(getPeriodicUrl("PLUS_DM")).respond(stream("plusdm"));   
        mockInterceptor.addRule().get(getBBANDSUrl(null)).respond(stream("bbands"));
        mockInterceptor.addRule().get(getBBANDSUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("MIDPOINT")).respond(stream("midpoint"));
        mockInterceptor.addRule().get(getPeriodicUrl("MIDPRICE")).respond(stream("midprice"));
        mockInterceptor.addRule().get(getSARUrl(null)).respond(stream("sar"));
        mockInterceptor.addRule().get(getSARUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("TRANGE")).respond(stream("trange"));
        mockInterceptor.addRule().get(getPeriodicUrl("ATR")).respond(stream("atr"));
        mockInterceptor.addRule().get(getPeriodicUrl("NATR")).respond(stream("natr"));
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("AD")).respond(stream("ad"));
        mockInterceptor.addRule().get(getADOSCUrl(null)).respond(stream("adosc"));
        mockInterceptor.addRule().get(getADOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleIndicatorRequestUrl("OBV")).respond(stream("obv"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDLINE")).respond(stream("httrendline"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDLINE", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_SINE")).respond(stream("htsine"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_SINE", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSeriesUrl("HT_TRENDMODE")).respond(stream("httrendmode"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_DCPERIOD")).respond(stream("htdcperiod"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_DCPHASE")).respond(stream("htdcphase"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_PHASOR")).respond(stream("htphasor"));
        mockInterceptor.addRule().get(getSeriesUrl("HT_PHASOR", "GOOGL")).respond(errorMessage);

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
            .fetchSync();
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
            .fetchSync();
    }
    
    @Test
    public void testSMA() throws InterruptedException {
        Indicator.PeriodicSeriesRequestProxy sma = AlphaVantage.api().indicator().sma();
        PeriodicSeriesResponse response = sma.forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSMAError() throws InterruptedException {
        
        Indicator.PeriodicSeriesRequestProxy sma = AlphaVantage.api().indicator().sma();
        PeriodicSeriesResponse response = sma.forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertNotNull(response.getErrorMessage());
    }

    @Test
    public void testEMA() throws InterruptedException {
        
        Indicator.PeriodicSeriesRequestProxy ema = AlphaVantage.api().indicator().ema();
        PeriodicSeriesResponse response = ema.forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testWMA() throws InterruptedException {
        Indicator.PeriodicSeriesRequestProxy wma = AlphaVantage.api().indicator().wma();
        PeriodicSeriesResponse response = wma.forSymbol("IBM")
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testDEMA() throws InterruptedException {
        Indicator.PeriodicSeriesRequestProxy dema = AlphaVantage.api().indicator().dema();
        PeriodicSeriesResponse response = dema.forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testTEMA() throws InterruptedException {

        Indicator.PeriodicSeriesRequestProxy tema = AlphaVantage.api().indicator().tema();
        PeriodicSeriesResponse response = tema.forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testTRIMA() throws InterruptedException {
        Indicator.PeriodicSeriesRequestProxy trima = AlphaVantage.api().indicator().trima();
        PeriodicSeriesResponse response = trima.forSymbol("IBM")
        .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testKAMA() throws InterruptedException {
        Indicator.PeriodicSeriesRequestProxy kama = AlphaVantage.api().indicator().kama();
        PeriodicSeriesResponse response = kama.forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }
    
    @Test
    public void testMAMA() throws InterruptedException {
        Indicator.MAMARequestProxy mama = AlphaVantage.api().indicator().mama();
        MAMAResponse response = mama.forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .dataType(DataType.JSON)
            .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMAMAError() throws InterruptedException {
        Indicator.MAMARequestProxy mama = AlphaVantage.api().indicator().mama();
        MAMAResponse response = mama.forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .dataType(DataType.JSON)
            .fetchSync();
        assertNotNull(response.getErrorMessage());
    }



    @Test
    public void testVWAP() throws InterruptedException {

        SimpleIndicatorResponse response = AlphaVantage
            .api()
            .indicator()
            .vwap()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .dataType(DataType.JSON)
            .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);

    }


    @Test
    public void testVWAPError() throws InterruptedException {
    
        SimpleIndicatorResponse response = AlphaVantage
            .api()
            .indicator()
            .vwap()
            .forSymbol("GOOGL")
            .interval(Interval.WEEKLY)
            .dataType(DataType.JSON)
            .fetchSync();

        assertNotNull(response.getErrorMessage());

    }

    @Test
    public void testT3() throws InterruptedException {
     
        PeriodicSeriesResponse response = AlphaVantage
            .api()
            .indicator()
            .t3()
            .forSymbol("IBM")
            .interval(Interval.WEEKLY)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .dataType(DataType.JSON)
            .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACD() throws InterruptedException {
        
        Indicator.MACDRequestProxy macd = AlphaVantage.api().indicator().macd();
        MACDResponse response =  macd.interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .dataType(DataType.JSON)
            .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDError() throws InterruptedException {
        Indicator.MACDRequestProxy macd = AlphaVantage.api().indicator().macd();
        MACDResponse response =  macd.interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("GOOGL")
            .dataType(DataType.JSON)
            .fetchSync();

        assertNotNull(response.getErrorMessage());
    }

    @Test
    public void testMACDEXT() throws InterruptedException {
    
        MACDEXTResponse response = AlphaVantage
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
            .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDEXTError() throws InterruptedException {
        
        MACDEXTResponse response = AlphaVantage
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
            .fetchSync();

        assertNotNull(response.getErrorMessage());
    }



    // @Test
    // public void testSTOCH() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<STOCHResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .stoch()
    //         .interval(Interval.SIXTY_MIN)
    //         .fastKPeriod(5)
    //         .slowKPeriod(3)
    //         .slowDPeriod(3)
    //         .slowKMaType(MAType.SMA)
    //         .slowDMaType(MAType.SMA)
    //         .forSymbol("IBM")
    //         .dataType(DataType.JSON)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((STOCHResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testSTOCHError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .stoch()
    //         .interval(Interval.SIXTY_MIN)
    //         .fastKPeriod(5)
    //         .slowKPeriod(3)
    //         .slowDPeriod(3)
    //         .slowKMaType(MAType.SMA)
    //         .slowDMaType(MAType.SMA)
    //         .forSymbol("GOOGL")
    //         .dataType(DataType.JSON)
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }



    // @Test
    // public void testSTOCHF() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<STOCHFResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .stochf()
    //         .interval(Interval.SIXTY_MIN)
    //         .fastKPeriod(5)
    //         .fastDPeriod(3)
    //         .fastDMaType(MAType.MAMA)
    //         .forSymbol("IBM")
    //         .dataType(DataType.JSON)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((STOCHFResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testSTOCHFError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .stochf()
    //         .interval(Interval.SIXTY_MIN)
    //         .fastKPeriod(5)
    //         .fastDPeriod(3)
    //         .fastDMaType(MAType.MAMA)
    //         .forSymbol("GOOGL")
    //         .dataType(DataType.JSON)
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }


    // @Test
    // public void testRSI() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .rsi()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testSTOCHRSI() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<STOCHRSIResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .stochrsi()
    //         .interval(Interval.SIXTY_MIN)
    //         .fastKPeriod(5)
    //         .fastDPeriod(3)
    //         .fastDMaType(MAType.MAMA)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((STOCHRSIResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testSTOCHRSIError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .stochrsi()
    //         .interval(Interval.SIXTY_MIN)
    //         .fastKPeriod(5)
    //         .fastDPeriod(3)
    //         .fastDMaType(MAType.MAMA)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .forSymbol("GOOGL")
    //         .onFailure(( e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }


    // @Test
    // public void testWILLR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .willr()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testADX() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .adx()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testADXR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .adxr()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testPPO() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PriceOscillatorResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .ppo()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .maType(MAType.MAMA)
    //         .fastPeriod(10)
    //         .slowPeriod(26)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PriceOscillatorResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testPPOError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .ppo()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .maType(MAType.MAMA)
    //         .fastPeriod(10)
    //         .slowPeriod(26)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }


    // @Test
    // public void testAPO() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PriceOscillatorResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .apo()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .maType(MAType.MAMA)
    //         .fastPeriod(10)
    //         .slowPeriod(26)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PriceOscillatorResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }


    // @Test
    // public void testMOM() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .mom()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testBOP() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SimpleIndicatorResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .bop()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SimpleIndicatorResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);

    // }

    // @Test
    // public void testCCI() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .cci()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testCCIError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .cci()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }


    // @Test
    // public void testCMO() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .cmo()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testROC() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .roc()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testROCR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .rocr()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testAROON() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AROONResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .aroon()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((AROONResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testAROONError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .aroon()
    //         .forSymbol("GOOGL")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testAROONErrorWithoutFallbackCallback() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .aroon()
    //         .forSymbol("GOOGL")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onSuccess((e) -> {
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertNull(response);
    // }

    // @Test
    // public void testAROONOSC() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .aroonosc()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testMFI() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .mfi()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testTRIX() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .trix()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testULTOSC() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<ULTOSCResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .ultosc()
    //         .interval(Interval.SIXTY_MIN)
    //         .timePeriod1(7)
    //         .timePeriod2(14)
    //         .timePeriod3(28)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((ULTOSCResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testULTOSCError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .ultosc()
    //         .interval(Interval.SIXTY_MIN)
    //         .timePeriod1(7)
    //         .timePeriod2(14)
    //         .timePeriod3(28)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testDX() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .dx()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }
    
    // @Test
    // public void testMINUSDI() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .minusdi()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }
    
    // @Test
    // public void testPLUSDI() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .plusdi()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }
    // @Test
    // public void testMINUSDM() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .minusdm()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testPLUSDM() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .plusdm()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testBBANDS() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<BBANDSResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .bbands()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .seriesType(SeriesType.OPEN)
    //         .nbdevdn(4)
    //         .nbdevup(4)
    //         .maType(MAType.SMA)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((BBANDSResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testBBANDSError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .bbands()
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .seriesType(SeriesType.OPEN)
    //         .nbdevdn(4)
    //         .nbdevup(4)
    //         .maType(MAType.SMA)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testMIDPOINT() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicSeriesResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .midpoint()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .seriesType(SeriesType.OPEN)
    //         .timePeriod(60)
    //         .onSuccess((PeriodicSeriesResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testMIDPRICE() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .midprice()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testSAR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SARResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .sar()
    //         .interval(Interval.DAILY)
    //         .acceleration(0.02)
    //         .maximum(0.50)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SARResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testSARError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .sar()
    //         .interval(Interval.DAILY)
    //         .acceleration(0.02)
    //         .maximum(0.50)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testTRANGE() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SimpleIndicatorResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .trange()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SimpleIndicatorResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);

    // }

    // @Test
    // public void testATR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .atr()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testNATR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<PeriodicResponse> ref = new AtomicReference<>();
    //     AlphaVantage.api()
    //         .indicator()
    //         .natr()
    //         .forSymbol("IBM")
    //         .interval(Interval.DAILY)
    //         .timePeriod(60)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((PeriodicResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();
    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testAD() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SimpleIndicatorResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .ad()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SimpleIndicatorResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);

    // }

    // @Test
    // public void testADOSC() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<ADOSCResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .adosc()
    //         .interval(Interval.DAILY)
    //         .fastPeriod(3)
    //         .slowPeriod(10)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((ADOSCResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testADOSCError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .adosc()
    //         .interval(Interval.DAILY)
    //         .fastPeriod(3)
    //         .slowPeriod(10)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testOBV() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SimpleIndicatorResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .obv()
    //         .forSymbol("IBM")
    //         .interval(Interval.WEEKLY)
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SimpleIndicatorResponse e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);

    // }

    // @Test
    // public void testHTTRENDLINE() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SeriesResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .httrendline()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SeriesResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testHTTRENDLINEError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .httrendline()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testHTSINE() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<HTSINEResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .htsine()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((HTSINEResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testHTSINEError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .htsine()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }

    // @Test
    // public void testHTRENDMODE() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SeriesResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .httrendmode()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SeriesResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testHTDCPERIOD() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SeriesResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .htdcperiod()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SeriesResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testHTDCPHASE() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<SeriesResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .htdcphase()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((SeriesResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testHTPHASOR() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<HTPHASORResponse> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .htphasor()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("IBM")
    //         .onFailure((e) -> lock.countDown())
    //         .onSuccess((HTPHASORResponse e)-> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
    //     assertEquals(response.getIndicatorUnits().size(), 2);
    // }

    // @Test
    // public void testHTPHASORError() throws InterruptedException {
    //     CountDownLatch lock = new CountDownLatch(1);
    //     AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

    //     AlphaVantage
    //         .api()
    //         .indicator()
    //         .htphasor()
    //         .interval(Interval.DAILY)
    //         .seriesType(SeriesType.OPEN)
    //         .forSymbol("GOOGL")
    //         .onFailure((e) -> {
    //             ref.set(e);
    //             lock.countDown();
    //         })
    //         .dataType(DataType.JSON)
    //         .fetch();

    //     lock.await();
    //     assertNotNull(response);
    // }


}