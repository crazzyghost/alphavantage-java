package indicator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

import com.crazzyghost.alphavantage.indicator.response.adosc.ADOSCResponse;
import com.crazzyghost.alphavantage.indicator.response.adx.ADXResponse;
import com.crazzyghost.alphavantage.indicator.response.apo.APOResponse;
import com.crazzyghost.alphavantage.indicator.response.aroon.AROONResponse;
import com.crazzyghost.alphavantage.indicator.response.bbands.BBANDSResponse;
import com.crazzyghost.alphavantage.indicator.response.htphasor.HTPHASORResponse;
import com.crazzyghost.alphavantage.indicator.response.htsine.HTSINEResponse;
import com.crazzyghost.alphavantage.indicator.response.httrendline.HTTRENDLINEResponse;
import com.crazzyghost.alphavantage.indicator.response.macd.MACDEXTResponse;
import com.crazzyghost.alphavantage.indicator.response.macd.MACDResponse;
import com.crazzyghost.alphavantage.indicator.response.mama.MAMAResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.indicator.response.ppo.PPOResponse;
import com.crazzyghost.alphavantage.indicator.response.sar.SARResponse;
import com.crazzyghost.alphavantage.indicator.response.stochf.STOCHFResponse;
import com.crazzyghost.alphavantage.indicator.response.stochrsi.STOCHRSIResponse;
import com.crazzyghost.alphavantage.indicator.response.stoch.STOCHResponse;
import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.ultosc.ULTOSCResponse;
import com.crazzyghost.alphavantage.indicator.response.sma.SMAResponse;
import com.crazzyghost.alphavantage.indicator.response.vwap.VWAPResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Test;

import okio.BufferedSource;
import okio.Okio;

import static org.junit.Assert.*;
import static util.TestUtils.emptyMessage;

public class IndicatorResponseTest {

    String errorMessage = "{" +
        "\"Information\":" + "\"The **demo** API key is for demo purposes only. Please claim your free API key at (https://www.alphavantage.co/support/#api-key) to explore our full API offerings. It takes fewer than 20 seconds, and we are committed to making it free forever.\"" +
    "}";

    private BufferedSource getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src","test","java","indicator","data", filename + ".json").toFile());
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

    @Test
    public void testPeriodicSeriesResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicSeriesResponse response = SMAResponse.of(adapter.fromJson(errorMessage));
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
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
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
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
    public void testSimpleIndicatorResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SimpleIndicatorResponse response = VWAPResponse.of(adapter.fromJson(getJson("vwap")));
        assertEquals(response.getIndicatorUnits().size(), 2);    
    }

    @Test
    public void testSimpleIndicatorResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SimpleIndicatorResponse response = VWAPResponse.of(adapter.fromJson(errorMessage));
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
        assertEquals(response.getIndicatorUnits().size(), 0);
        assertNotNull(response.getErrorMessage());
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
