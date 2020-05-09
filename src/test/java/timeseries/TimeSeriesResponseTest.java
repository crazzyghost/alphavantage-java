package timeseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import org.junit.Before;
import org.junit.Test;

import util.TestUtils;
import static util.TestUtils.json;
import static util.TestUtils.error;

public class TimeSeriesResponseTest {

    @Before 
    public void setUp(){
        TestUtils.forDirectory("timeseries");
    }

    @Test
    public void testTimeSeriesResponse() throws IOException {
        TimeSeriesResponse response = TimeSeriesResponse.of(json("intraday"), false);
        assertNotNull(response.getMetaData());
        assertEquals(response.getStockUnits().size(), 2);
        assertNotEquals(response.getMetaData().getSymbol(), "");
        assertNotEquals(response.getMetaData().getInformation(), "");
        assertNotEquals(response.getMetaData().getLastRefreshed(), "");
        assertNotEquals(response.getMetaData().getOutputSize(), "");
        assertNotEquals(response.getMetaData().getTimeZone(), "");
        assertNotEquals(response.getMetaData().getInterval(), "");

    }

    @Test
    public void testTimeSeriesDailyAdjustedResponse() throws IOException {
        TimeSeriesResponse response = TimeSeriesResponse.of(json("dailyadjusted"), true);
        assertNotNull(response.getMetaData());
        assertEquals(response.getStockUnits().size(), 2);
        assertNotEquals(response.getMetaData().getSymbol(), "");
        assertNotEquals(response.getMetaData().getInformation(), "");
        assertNotEquals(response.getMetaData().getLastRefreshed(), "");
        assertNotEquals(response.getMetaData().getOutputSize(), "");
        assertNotEquals(response.getMetaData().getTimeZone(), "");
        assertNotEquals(response.getMetaData().getInterval(), "");

    }

    @Test
    public void testTimeSeriesMonthlyAdjustedResponse() throws IOException {
        TimeSeriesResponse response = TimeSeriesResponse.of(json("monthlyadjusted"), true);
        assertNotNull(response.getMetaData());
        assertEquals(response.getStockUnits().size(), 2);
        assertNotEquals(response.getMetaData().getSymbol(), "");
        assertNotEquals(response.getMetaData().getInformation(), "");
        assertNotEquals(response.getMetaData().getLastRefreshed(), "");
        assertNotEquals(response.getMetaData().getOutputSize(), "");
        assertNotEquals(response.getMetaData().getTimeZone(), "");
        assertNotEquals(response.getMetaData().getInterval(), "");

    }

    @Test
    public void testTimeSeriesResponseError() throws IOException {
        TimeSeriesResponse response = TimeSeriesResponse.of(error(), false);
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }
 
    @Test
    public void testGlobalQuoteResponse() throws IOException {
        QuoteResponse response = QuoteResponse.of(json("globalquote"));
        assertEquals(response.getSymbol(), "IBM");
        assertEquals(response.getOpen(), 122.67, 0.0);
        assertEquals(response.getHigh(), 122.80, 0.0);
        assertEquals(response.getLow(), 121.06, 0.0);
        assertEquals(response.getPrice(), 121.74, 0.0);
        assertEquals(response.getVolume(), 2528814, 0.0);
        assertEquals(response.getLatestTradingDay(), "2020-05-08");
        assertEquals(response.getPreviousClose(), 121.23, 0.0);
        assertEquals(response.getChange(), 0.5100, 0.0);
        assertEquals(response.getChangePercent(), 0.4207, 0.0);
    }

    @Test
    public void testGlobalQuoteResponseError() throws IOException {
        QuoteResponse response = QuoteResponse.of(error());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }
}