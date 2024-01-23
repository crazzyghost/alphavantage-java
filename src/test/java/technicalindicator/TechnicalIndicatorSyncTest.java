package technicalindicator;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.technicalindicator.TechnicalIndicator;
import com.crazzyghost.alphavantage.technicalindicator.request.ULTOSCRequest;
import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ad.ADResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.adosc.ADOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.adx.ADXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.adxr.ADXRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.apo.APOResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.aroon.AROONResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.aroonosc.AROONOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.atr.ATRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.bbands.BBANDSResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.bop.BOPResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.cci.CCIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.cmo.CMOResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.dema.DEMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.dx.DXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ema.EMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htdcperiod.HTDCPERIODResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htdcphase.HTDCPHASEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htphasor.HTPHASORResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htsine.HTSINEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.httrendline.HTTRENDLINEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.httrendmode.HTTRENDMODEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.kama.KAMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDEXTResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mama.MAMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mfi.MFIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.midpoint.MIDPOINTResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.midprice.MIDPRICEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.minusdi.MINUSDIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.minusdm.MINUSDMResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mom.MOMResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.natr.NATRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.obv.OBVResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.plusdi.PLUSDIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.plusdm.PLUSDMResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ppo.PPOResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.roc.ROCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.rocr.ROCRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.rsi.RSIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.sar.SARResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.sma.SMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stoch.STOCHResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochf.STOCHFResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochrsi.STOCHRSIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.t3.T3Response;
import com.crazzyghost.alphavantage.technicalindicator.response.tema.TEMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trange.TRANGEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trima.TRIMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trix.TRIXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ultosc.ULTOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.vwap.VWAPResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.willr.WILLRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.wma.WMAResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static util.TestUtils.*;


public class TechnicalIndicatorSyncTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {

        TestUtils.forDirectory("technicalindicator");

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
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("SMA", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("EMA")).respond(stream("ema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("WMA")).respond(stream("wma"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("DEMA")).respond(stream("dema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TEMA")).respond(stream("tema"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("TRIMA")).respond(stream("trima"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("KAMA")).respond(stream("kama"));
        mockInterceptor.addRule().get(getMAMAUrl(null)).respond(stream("mama"));
        mockInterceptor.addRule().get(getMAMAUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("VWAP")).respond(stream("vwap"));
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("VWAP", "GOOGL")).respond(errorMessage);
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
        mockInterceptor.addRule().get(getPriceOscillatorUrl("PPO", "GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getPriceOscillatorUrl("APO")).respond(stream("apo"));
        mockInterceptor.addRule().get(getPeriodicSeriesUrl("MOM")).respond(stream("mom"));
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("BOP")).respond(stream("bop"));
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
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("TRANGE")).respond(stream("trange"));
        mockInterceptor.addRule().get(getPeriodicUrl("ATR")).respond(stream("atr"));
        mockInterceptor.addRule().get(getPeriodicUrl("NATR")).respond(stream("natr"));
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("AD")).respond(stream("ad"));
        mockInterceptor.addRule().get(getADOSCUrl(null)).respond(stream("adosc"));
        mockInterceptor.addRule().get(getADOSCUrl("GOOGL")).respond(errorMessage);
        mockInterceptor.addRule().get(getSimpleTechnicalIndicatorRequestUrl("OBV")).respond(stream("obv"));
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
    public void testConfigNotSet() {
        new TechnicalIndicator(null)
                .sma()
                .forSymbol("AAPL")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new TechnicalIndicator(Config.builder().build())
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
        PeriodicSeriesResponse response = AlphaVantage.api().technicalIndicator().sma()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSMAError() throws InterruptedException {

        TechnicalIndicator.PeriodicSeriesRequestProxy<SMAResponse> sma = AlphaVantage.api().technicalIndicator().sma();
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

        TechnicalIndicator.PeriodicSeriesRequestProxy<EMAResponse> ema = AlphaVantage.api().technicalIndicator().ema();
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
        TechnicalIndicator.PeriodicSeriesRequestProxy<WMAResponse> wma = AlphaVantage.api().technicalIndicator().wma();
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
        TechnicalIndicator.PeriodicSeriesRequestProxy<DEMAResponse> dema = AlphaVantage.api().technicalIndicator().dema();
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

        TechnicalIndicator.PeriodicSeriesRequestProxy<TEMAResponse> tema = AlphaVantage.api().technicalIndicator().tema();
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
        TechnicalIndicator.PeriodicSeriesRequestProxy<TRIMAResponse> trima = AlphaVantage.api().technicalIndicator().trima();
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
        TechnicalIndicator.PeriodicSeriesRequestProxy<KAMAResponse> kama = AlphaVantage.api().technicalIndicator().kama();
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
        TechnicalIndicator.MAMARequestProxy mama = AlphaVantage.api().technicalIndicator().mama();
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
        TechnicalIndicator.MAMARequestProxy mama = AlphaVantage.api().technicalIndicator().mama();
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

        VWAPResponse response = AlphaVantage
                .api()
                .technicalIndicator()
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

        SimpleTechnicalIndicatorResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .vwap()
                .forSymbol("GOOGL")
                .interval(Interval.WEEKLY)
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response.getErrorMessage());

    }

    @Test
    public void testT3() throws InterruptedException {

        T3Response response = AlphaVantage
                .api()
                .technicalIndicator()
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

        TechnicalIndicator.MACDRequestProxy macd = AlphaVantage.api().technicalIndicator().macd();
        MACDResponse response = macd.interval(Interval.DAILY)
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
        TechnicalIndicator.MACDRequestProxy macd = AlphaVantage.api().technicalIndicator().macd();
        MACDResponse response = macd.interval(Interval.DAILY)
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
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDEXTError() throws InterruptedException {

        MACDEXTResponse response = AlphaVantage
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
                .fetchSync();

        assertNotNull(response.getErrorMessage());
    }


    @Test
    public void testSTOCH() throws InterruptedException {

        STOCHResponse response = AlphaVantage
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
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHError() throws InterruptedException {

        STOCHResponse response = AlphaVantage
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
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }


    @Test
    public void testSTOCHF() throws InterruptedException {

        STOCHFResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .stochf()
                .interval(Interval.SIXTY_MIN)
                .fastKPeriod(5)
                .fastDPeriod(3)
                .fastDMaType(MAType.MAMA)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHFError() throws InterruptedException {

        STOCHFResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .stochf()
                .interval(Interval.SIXTY_MIN)
                .fastKPeriod(5)
                .fastDPeriod(3)
                .fastDMaType(MAType.MAMA)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }


    @Test
    public void testRSI() throws InterruptedException {

        RSIResponse response = AlphaVantage.api()
                .technicalIndicator()
                .rsi()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHRSI() throws InterruptedException {

        STOCHRSIResponse response = AlphaVantage
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
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHRSIError() throws InterruptedException {
        STOCHRSIResponse response = AlphaVantage
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
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }


    @Test
    public void testWILLR() throws InterruptedException {
        WILLRResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .willr()
                .interval(Interval.DAILY)
                .timePeriod(60)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADX() throws InterruptedException {
        ADXResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .adx()
                .interval(Interval.DAILY)
                .timePeriod(60)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADXR() throws InterruptedException {
        ADXRResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .adxr()
                .interval(Interval.DAILY)
                .timePeriod(60)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPPO() throws InterruptedException {

        PPOResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .ppo()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .maType(MAType.MAMA)
                .fastPeriod(10)
                .slowPeriod(26)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPPOError() throws InterruptedException {

        PPOResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .ppo()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .maType(MAType.MAMA)
                .fastPeriod(10)
                .slowPeriod(26)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();
        assertNotNull(response);
    }


    @Test
    public void testAPO() throws InterruptedException {

        APOResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .apo()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .maType(MAType.MAMA)
                .fastPeriod(10)
                .slowPeriod(26)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }


    @Test
    public void testMOM() throws InterruptedException {

        MOMResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .mom()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBOP() throws InterruptedException {

        BOPResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .bop()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);

    }

    @Test
    public void testCCI() throws InterruptedException {
        CCIResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .cci()
                .interval(Interval.DAILY)
                .timePeriod(60)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testCCIError() throws InterruptedException {
        
        CCIResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .cci()
                .interval(Interval.DAILY)
                .timePeriod(60)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();
        assertNotNull(response);
    }


    @Test
    public void testCMO() throws InterruptedException {
        CMOResponse response = AlphaVantage.api()
                .technicalIndicator()
                .cmo()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testROC() throws InterruptedException {
        ROCResponse response = AlphaVantage.api()
                .technicalIndicator()
                .roc()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testROCR() throws InterruptedException {
        ROCRResponse response = AlphaVantage.api()
                .technicalIndicator()
                .rocr()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAROON() throws InterruptedException {
        AROONResponse response = AlphaVantage.api()
                .technicalIndicator()
                .aroon()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAROONError() throws InterruptedException {
        AROONResponse response = AlphaVantage.api()
                .technicalIndicator()
                .aroon()
                .forSymbol("GOOGL")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertNotNull(response);
    }

    @Test
    public void testAROONOSC() throws InterruptedException {
        AROONOSCResponse response = AlphaVantage.api()
                .technicalIndicator()
                .aroonosc()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMFI() throws InterruptedException {
        MFIResponse response = AlphaVantage.api()
                .technicalIndicator()
                .mfi()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testTRIX() throws InterruptedException {
        TRIXResponse response = AlphaVantage.api()
                .technicalIndicator()
                .trix()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testULTOSC() throws InterruptedException {
        ULTOSCResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .ultosc()
                .interval(Interval.SIXTY_MIN)
                .timePeriod1(7)
                .timePeriod2(14)
                .timePeriod3(28)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testULTOSCError() throws InterruptedException {

        ULTOSCResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .ultosc()
                .interval(Interval.SIXTY_MIN)
                .timePeriod1(7)
                .timePeriod2(14)
                .timePeriod3(28)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();
        assertNotNull(response);
    }

    @Test
    public void testDX() throws InterruptedException {
        DXResponse response = AlphaVantage.api()
                .technicalIndicator()
                .dx()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMINUSDI() throws InterruptedException {
        MINUSDIResponse response = AlphaVantage.api()
                .technicalIndicator()
                .minusdi()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPLUSDI() throws InterruptedException {
        PLUSDIResponse response = AlphaVantage.api()
                .technicalIndicator()
                .plusdi()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMINUSDM() throws InterruptedException {
        MINUSDMResponse response = AlphaVantage.api()
                .technicalIndicator()
                .minusdm()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPLUSDM() throws InterruptedException {
        PLUSDMResponse response = AlphaVantage.api()
                .technicalIndicator()
                .plusdm()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBBANDS() throws InterruptedException {

        BBANDSResponse response = AlphaVantage
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
                .dataType(DataType.JSON)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBBANDSError() throws InterruptedException {

        BBANDSResponse response = AlphaVantage
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
                .dataType(DataType.JSON)
                .fetchSync();
        assertNotNull(response);
    }

    @Test
    public void testMIDPOINT() throws InterruptedException {

        MIDPOINTResponse response = AlphaVantage.api()
                .technicalIndicator()
                .midpoint()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .seriesType(SeriesType.OPEN)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMIDPRICE() throws InterruptedException {

        MIDPRICEResponse response = AlphaVantage.api()
                .technicalIndicator()
                .midprice()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSAR() throws InterruptedException {

        SARResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .sar()
                .interval(Interval.DAILY)
                .acceleration(0.02)
                .maximum(0.50)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSARError() throws InterruptedException {

        SARResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .sar()
                .interval(Interval.DAILY)
                .acceleration(0.02)
                .maximum(0.50)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }

    @Test
    public void testTRANGE() throws InterruptedException {

        TRANGEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .trange()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);

    }

    @Test
    public void testATR() throws InterruptedException {
        ATRResponse response = AlphaVantage.api()
                .technicalIndicator()
                .atr()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testNATR() throws InterruptedException {
        NATRResponse response = AlphaVantage.api()
                .technicalIndicator()
                .natr()
                .forSymbol("IBM")
                .interval(Interval.DAILY)
                .timePeriod(60)
                .dataType(DataType.JSON)
                .fetchSync();
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAD() throws InterruptedException {

        ADResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .ad()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);

    }

    @Test
    public void testADOSC() throws InterruptedException {

        ADOSCResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .adosc()
                .interval(Interval.DAILY)
                .fastPeriod(3)
                .slowPeriod(10)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADOSCError() throws InterruptedException {

        ADOSCResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .adosc()
                .interval(Interval.DAILY)
                .fastPeriod(3)
                .slowPeriod(10)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }

    @Test
    public void testOBV() throws InterruptedException {

        OBVResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .obv()
                .forSymbol("IBM")
                .interval(Interval.WEEKLY)
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);

    }

    @Test
    public void testHTTRENDLINE() throws InterruptedException {

        HTTRENDLINEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .httrendline()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTTRENDLINEError() throws InterruptedException {

        HTTRENDLINEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .httrendline()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }

    @Test
    public void testHTSINE() throws InterruptedException {

        HTSINEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .htsine()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTSINEError() throws InterruptedException {

        HTSINEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .htsine()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }

    @Test
    public void testHTRENDMODE() throws InterruptedException {

        HTTRENDMODEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .httrendmode()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTDCPERIOD() throws InterruptedException {

        HTDCPERIODResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .htdcperiod()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTDCPHASE() throws InterruptedException {

        HTDCPHASEResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .htdcphase()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTPHASOR() throws InterruptedException {

        HTPHASORResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .htphasor()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .fetchSync();

        assertTrue(response.toString().matches("(.*),indicatorUnits=2(.*)"));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testHTPHASORError() throws InterruptedException {

        HTPHASORResponse response = AlphaVantage
                .api()
                .technicalIndicator()
                .htphasor()
                .interval(Interval.DAILY)
                .seriesType(SeriesType.OPEN)
                .forSymbol("GOOGL")
                .dataType(DataType.JSON)
                .fetchSync();

        assertNotNull(response);
    }


}