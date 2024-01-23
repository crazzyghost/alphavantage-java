package technicalindicator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

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
import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.PriceOscillatorResponse;
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
import com.crazzyghost.alphavantage.technicalindicator.response.stochf.STOCHFResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochrsi.STOCHRSIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stoch.STOCHResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.t3.T3Response;
import com.crazzyghost.alphavantage.technicalindicator.response.tema.TEMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trange.TRANGEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trima.TRIMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trix.TRIXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ultosc.ULTOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.sma.SMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.vwap.VWAPResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.willr.WILLRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.wma.WMAResponse;
import com.crazzyghost.alphavantage.parser.Parser;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Test;

import okio.BufferedSource;
import okio.Okio;

import static org.junit.Assert.*;
import static util.TestUtils.emptyMessage;

public class TechnicalIndicatorResponseTest {

    String errorMessage = "{" +
        "\"Information\":" + "\"The **demo** API key is for demo purposes only. Please claim your free API key at (https://www.alphavantage.co/support/#api-key) to explore our full API offerings. It takes fewer than 20 seconds, and we are committed to making it free forever.\"" +
    "}";

    private BufferedSource getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src","test","java","technicalindicator","data", filename + ".json").toFile());
        return Okio.buffer(Okio.source(stream));
    }

    private JsonAdapter<Map<String,Object>> getJsonAdapter() {
        final Moshi moshi = new Moshi.Builder().build();
        final Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
        return moshi.adapter(type);
    }

    @Test
    public void testEmptyResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicSeriesResponse response = SMAResponse.of(adapter.fromJson(emptyMessage));
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testPeriodicSeriesResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicSeriesResponse response = SMAResponse.of(adapter.fromJson(getJson("sma")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testErrorResponseBody() throws IOException {
        Parser.parseJSON(null);
    }

    @Test
    public void testPeriodicSeriesResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicSeriesResponse response = SMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response2 = EMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response3 = WMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response4 = DEMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response5 = TEMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response6 = TRIMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response7 = KAMAResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response8 = T3Response.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response9 = RSIResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response10 = MOMResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response11 = CMOResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response12 = ROCResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response13 = ROCRResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response14 = TRIXResponse.of(adapter.fromJson(errorMessage));
        PeriodicSeriesResponse response15 = MIDPOINTResponse.of(adapter.fromJson(errorMessage));

        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertNotNull(response2.getErrorMessage());
        assertNotNull(response3.getErrorMessage());
        assertNotNull(response4.getErrorMessage());
        assertNotNull(response5.getErrorMessage());
        assertNotNull(response6.getErrorMessage());
        assertNotNull(response7.getErrorMessage());
        assertNotNull(response8.getErrorMessage());
        assertNotNull(response9.getErrorMessage());
        assertNotNull(response10.getErrorMessage());
        assertNotNull(response11.getErrorMessage());
        assertNotNull(response12.getErrorMessage());
        assertNotNull(response13.getErrorMessage());
        assertNotNull(response14.getErrorMessage());
        assertNotNull(response15.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSeriesType(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimePeriod(), 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testMAMAResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MAMAResponse response = MAMAResponse.of(adapter.fromJson(getJson("mama")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMAMAResponseFails() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MAMAResponse response = MAMAResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSeriesType(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getSlowLimit(), 0.1, 0);
        assertEquals(response.getMetaData().getFastLimit(), 0.1, 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testMACDResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MACDResponse response = MACDResponse.of(adapter.fromJson(getJson("macd")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MACDResponse response = MACDResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getSeriesType(),"");
        assertEquals(response.getMetaData().getFastPeriod(), 12, 0);
        assertEquals(response.getMetaData().getSlowPeriod(), 26, 0);
        assertEquals(response.getMetaData().getSignalPeriod(), 9, 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }


    @Test
    public void testMACDEXTResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MACDEXTResponse response = MACDEXTResponse.of(adapter.fromJson(getJson("macdext")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDEXTResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MACDEXTResponse response = MACDEXTResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getSeriesType(),"");
        assertEquals(response.getMetaData().getFastPeriod(), 12, 0);
        assertEquals(response.getMetaData().getSlowPeriod(), 26, 0);
        assertEquals(response.getMetaData().getSignalPeriod(), 9, 0);
        assertEquals(response.getMetaData().getFastMaType(), 0, 0);
        assertEquals(response.getMetaData().getSlowMaType(), 0, 0);
        assertEquals(response.getMetaData().getSignalMaType(), 0, 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testSTOCHResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHResponse response = STOCHResponse.of(adapter.fromJson(getJson("stoch")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHResponse response = STOCHResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getFastKPeriod(), 5, 0);
        assertEquals(response.getMetaData().getSlowKPeriod(), 3, 0);
        assertEquals(response.getMetaData().getSlowKMaType(), 0, 0);
        assertEquals(response.getMetaData().getSlowDPeriod(), 3, 0);
        assertEquals(response.getMetaData().getSlowDMaType(), 0, 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testSTOCHFResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHFResponse response = STOCHFResponse.of(adapter.fromJson(getJson("stochf")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHFResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHFResponse response = STOCHFResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getFastKPeriod(), 5, 0);
        assertEquals(response.getMetaData().getFastDPeriod(), 3, 0);
        assertEquals(response.getMetaData().getFastDMaType(), 0, 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testSTOCHRSIResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHRSIResponse response = STOCHRSIResponse.of(adapter.fromJson(getJson("stochrsi")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHRSIResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHRSIResponse response = STOCHRSIResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimePeriod(), 10, 0);
        assertEquals(response.getMetaData().getFastKPeriod(), 5, 0);
        assertEquals(response.getMetaData().getFastDPeriod(), 3, 0);
        assertEquals(response.getMetaData().getFastDMaType(), 0, 0);
        assertEquals(response.getMetaData().getSeriesType(),"");
        assertEquals(response.getMetaData().getTimeZone(), "");
    }


    @Test
    public void testPeriodicResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicResponse response = ADXResponse.of(adapter.fromJson(getJson("adx")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPeriodicResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicResponse response = ADXResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response2 = WILLRResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response3 = ADXRResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response4 = CCIResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response5 = AROONOSCResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response6 = MFIResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response7 = DXResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response8 = PLUSDIResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response9 = PLUSDMResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response10 = MINUSDIResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response11 = MINUSDMResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response12 = MIDPRICEResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response13 = ATRResponse.of(adapter.fromJson(errorMessage));
        PeriodicResponse response14 = NATRResponse.of(adapter.fromJson(errorMessage));

        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertNotNull(response2.getErrorMessage());
        assertNotNull(response3.getErrorMessage());
        assertNotNull(response4.getErrorMessage());
        assertNotNull(response5.getErrorMessage());
        assertNotNull(response6.getErrorMessage());
        assertNotNull(response7.getErrorMessage());
        assertNotNull(response8.getErrorMessage());
        assertNotNull(response9.getErrorMessage());
        assertNotNull(response10.getErrorMessage());
        assertNotNull(response11.getErrorMessage());
        assertNotNull(response12.getErrorMessage());
        assertNotNull(response13.getErrorMessage());
        assertNotNull(response14.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimePeriod(), 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testPPO() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PriceOscillatorResponse response = PPOResponse.of(adapter.fromJson(getJson("ppo")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAPO() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PriceOscillatorResponse response = APOResponse.of(adapter.fromJson(getJson("apo")));
        assertEquals(response.getIndicatorUnits().size(), 2);

    }

    @Test
    public void testPOError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PriceOscillatorResponse response = PPOResponse.of(adapter.fromJson(errorMessage));
        PriceOscillatorResponse response2 = APOResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertNotNull(response2.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getFastPeriod(), 0);
        assertEquals(response.getMetaData().getSlowPeriod(), 0);
        assertEquals(response.getMetaData().getMaType(), 0);
        assertEquals(response.getMetaData().getSeriesType(), "");
        assertEquals(response.getMetaData().getTimeZone(), "");
    }


    @Test
    public void testAROON() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        AROONResponse response = AROONResponse.of(adapter.fromJson(getJson("aroon")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAROONError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        AROONResponse response = AROONResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimePeriod(), 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testSimpleTechnicalIndicatorResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SimpleTechnicalIndicatorResponse response = VWAPResponse.of(adapter.fromJson(getJson("vwap")));
        SimpleTechnicalIndicatorResponse response2 = BOPResponse.of(adapter.fromJson(errorMessage));
        SimpleTechnicalIndicatorResponse response3 = TRANGEResponse.of(adapter.fromJson(errorMessage));
        SimpleTechnicalIndicatorResponse response4 = ADResponse.of(adapter.fromJson(errorMessage));
        SimpleTechnicalIndicatorResponse response5 = OBVResponse.of(adapter.fromJson(errorMessage));
        assertNotNull(response2.getErrorMessage());
        assertNotNull(response3.getErrorMessage());
        assertNotNull(response4.getErrorMessage());
        assertNotNull(response5.getErrorMessage());
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSimpleTechnicalIndicatorResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SimpleTechnicalIndicatorResponse response = VWAPResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);    
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testULTOSC() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        ULTOSCResponse response = ULTOSCResponse.of(adapter.fromJson(getJson("ultosc")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testULTOSCError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        ULTOSCResponse response = ULTOSCResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimePeriod1(), 0);
        assertEquals(response.getMetaData().getTimePeriod2(), 0);
        assertEquals(response.getMetaData().getTimePeriod3(), 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testSeriesResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SeriesResponse response = HTTRENDLINEResponse.of(adapter.fromJson(getJson("httrendline")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSeriesResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SeriesResponse response = HTTRENDLINEResponse.of(adapter.fromJson(errorMessage));
        SeriesResponse response2 = HTTRENDMODEResponse.of(adapter.fromJson(errorMessage));
        SeriesResponse response3 = HTDCPHASEResponse.of(adapter.fromJson(errorMessage));
        SeriesResponse response4 = HTDCPERIODResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertNotNull(response2.getErrorMessage());
        assertNotNull(response3.getErrorMessage());
        assertNotNull(response4.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSeriesType(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getTimeZone(), "");

    }


    @Test
    public void testBBANDS() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        BBANDSResponse response = BBANDSResponse.of(adapter.fromJson(getJson("bbands")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testBBANDSError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        BBANDSResponse response = BBANDSResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(), "");
        assertEquals(response.getMetaData().getInterval(), "");
        assertEquals(response.getMetaData().getLastRefreshed(), "");
        assertEquals(response.getMetaData().getSymbol(), "");
        assertEquals(response.getMetaData().getTimePeriod(), 0);
        assertEquals(response.getMetaData().getSeriesType(), "");
        assertEquals(response.getMetaData().getMaType(), 0);
        assertEquals(response.getMetaData().getNbdevdn(), 0);
        assertEquals(response.getMetaData().getNbdevup(), 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }


    @Test
    public void testSAR() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SARResponse response = SARResponse.of(adapter.fromJson(getJson("sar")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSARError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SARResponse response = SARResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(), "");
        assertEquals(response.getMetaData().getInterval(), "");
        assertEquals(response.getMetaData().getLastRefreshed(), "");
        assertEquals(response.getMetaData().getSymbol(), "");
        assertEquals(response.getMetaData().getAcceleration(), 0.0, 0);
        assertEquals(response.getMetaData().getMaximum(), 0.0, 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testADOSC() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        ADOSCResponse response = ADOSCResponse.of(adapter.fromJson(getJson("adosc")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testADOSCError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        ADOSCResponse response = ADOSCResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
        assertEquals(response.getMetaData().getIndicator(),"");
        assertEquals(response.getMetaData().getInterval(),"");
        assertEquals(response.getMetaData().getLastRefreshed(),"");
        assertEquals(response.getMetaData().getSymbol(),"");
        assertEquals(response.getMetaData().getFastKPeriod(), 0);
        assertEquals(response.getMetaData().getSlowKPeriod(), 0);
        assertEquals(response.getMetaData().getTimeZone(), "");
    }

    @Test
    public void testHTSINEResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        HTSINEResponse response = HTSINEResponse.of(adapter.fromJson(getJson("htsine")));
        assertEquals(response.getIndicatorUnits().size(), 2);
        assertNotEquals(response.getMetaData().getIndicator(),"");
        assertNotEquals(response.getMetaData().getInterval(),"");
        assertNotEquals(response.getMetaData().getLastRefreshed(),"");
        assertNotEquals(response.getMetaData().getSymbol(),"");
        assertNotEquals(response.getMetaData().getSeriesType(),"");
        assertNotEquals(response.getMetaData().getTimeZone(),"");
    }

    @Test
    public void testHTPHASORResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        HTPHASORResponse response = HTPHASORResponse.of(adapter.fromJson(getJson("htphasor")));
        assertEquals(response.getIndicatorUnits().size(), 2);
        assertNotEquals(response.getMetaData().getIndicator(),"");
        assertNotEquals(response.getMetaData().getInterval(),"");
        assertNotEquals(response.getMetaData().getLastRefreshed(),"");
        assertNotEquals(response.getMetaData().getSymbol(),"");
        assertNotEquals(response.getMetaData().getSeriesType(),"");
        assertNotEquals(response.getMetaData().getTimeZone(),"");
    }


}
