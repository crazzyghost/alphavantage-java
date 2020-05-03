package indicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

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
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Test;

import okio.BufferedSource;
import okio.Okio;

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
    public void testPeriodicSeriesResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicSeriesResponse response = PeriodicSeriesResponse.of(adapter.fromJson(getJson("sma")), "SMA");
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPeriodicSeriesResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicSeriesResponse response = PeriodicSeriesResponse.of(adapter.fromJson(errorMessage), "SMA");
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
        PeriodicResponse response = PeriodicResponse.of(adapter.fromJson(getJson("adx")), "ADX");
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPeriodicResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicResponse response = PeriodicResponse.of(adapter.fromJson(errorMessage), "ADX");
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
        PriceOscillatorResponse response = PriceOscillatorResponse.of(adapter.fromJson(getJson("ppo")), "PPO");
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAPO() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PriceOscillatorResponse response = PriceOscillatorResponse.of(adapter.fromJson(getJson("apo")),"APO");
        assertEquals(response.getIndicatorUnits().size(), 2);
    
    }

    @Test
    public void testPOError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PriceOscillatorResponse response = PriceOscillatorResponse.of(adapter.fromJson(errorMessage), "PPO");
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
        SimpleIndicatorResponse response = SimpleIndicatorResponse.of(adapter.fromJson(getJson("vwap")), "VWAP");
        assertEquals(response.getIndicatorUnits().size(), 2);    
    }

    @Test
    public void testSimpleIndicatorResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SimpleIndicatorResponse response = SimpleIndicatorResponse.of(adapter.fromJson(errorMessage), "VWAP");
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
        SeriesResponse response = SeriesResponse.of(adapter.fromJson(getJson("httrendline")), "HT_TRENDLINE");
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSeriesResponseError() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SeriesResponse response = SeriesResponse.of(adapter.fromJson(errorMessage), "HT_TRENDLINE");
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