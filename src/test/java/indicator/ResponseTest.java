package indicator;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

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
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.junit.Test;

import okio.BufferedSource;
import okio.Okio;

public class ResponseTest {

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
    public void testMAMAResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MAMAResponse response = MAMAResponse.of(adapter.fromJson(getJson("mama")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MACDResponse response = MACDResponse.of(adapter.fromJson(getJson("macd")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testMACDEXTResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        MACDEXTResponse response = MACDEXTResponse.of(adapter.fromJson(getJson("macdext")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHResponse response = STOCHResponse.of(adapter.fromJson(getJson("stoch")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHFResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHFResponse response = STOCHFResponse.of(adapter.fromJson(getJson("stochf")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSTOCHRSIResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        STOCHRSIResponse response = STOCHRSIResponse.of(adapter.fromJson(getJson("stochrsi")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testPeriodicResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        PeriodicResponse response = PeriodicResponse.of(adapter.fromJson(getJson("adx")), "ADX");
        assertEquals(response.getIndicatorUnits().size(), 2);
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
        PriceOscillatorResponse response = PriceOscillatorResponse.of(adapter.fromJson(getJson("apo")), "APO");
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testAROON() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        AROONResponse response = AROONResponse.of(adapter.fromJson(getJson("aroon")));
        assertEquals(response.getIndicatorUnits().size(), 2);
    }

    @Test
    public void testSimpleIndicatorResponse() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        SimpleIndicatorResponse response = SimpleIndicatorResponse.of(adapter.fromJson(getJson("vwap")), "VWAP");
        assertEquals(response.getIndicatorUnits().size(), 2);    
    }

    @Test
    public void testULTOSC() throws IOException{
        final JsonAdapter<Map<String,Object>> adapter = getJsonAdapter();
        ULTOSCResponse response = ULTOSCResponse.of(adapter.fromJson(getJson("ultosc")), "ULTOSC");
        assertEquals(response.getIndicatorUnits().size(), 2);
    }


}