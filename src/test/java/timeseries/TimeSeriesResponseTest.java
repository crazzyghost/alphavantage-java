package timeseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static util.TestUtils.*;

import java.io.IOException;

import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.RealtimeBulkQuote;
import com.crazzyghost.alphavantage.timeseries.response.RealtimeBulkQuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import org.junit.Before;
import org.junit.Test;

import util.TestUtils;

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
    public void testEmptyTimeSeriesResponseError() throws IOException {
        TimeSeriesResponse response = TimeSeriesResponse.of(empty(), false);
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

    @Test
    public void testEmptyGlobalQuoteResponse() throws IOException {
        QuoteResponse response = QuoteResponse.of(empty());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testRealtimeBulkQuoteResponse() throws IOException {
        RealtimeBulkQuoteResponse response = RealtimeBulkQuoteResponse.of(json("realtimebulkquote"));
        RealtimeBulkQuote quote = response.getData().get(0);
        assertEquals(quote.getSymbol(), "MSFT");
        assertEquals(quote.getTimestamp(), "2024-10-18 19:59:55.291");
        assertEquals(quote.getOpen(), 417.61, 0.0);
        assertEquals(quote.getHigh(), 419.649, 0.0);
        assertEquals(quote.getLow(), 416.2601, 0.0);
        assertEquals(quote.getClose(), 418.16, 0.0);
        assertEquals(quote.getVolume(), 17145307, 0.0);
        assertEquals(quote.getPreviousClose(), 416.72, 0.0);
        assertEquals(quote.getChange(), 1.44, 0.0);
        assertEquals(quote.getChangePercent(), 0.3456, 0.0);
        assertEquals(quote.getExtendedHoursQuote(), 418.1, 0.0);
        assertEquals(quote.getExtendedHoursChange(), -0.06, 0.0);
        assertEquals(quote.getExtendedHoursChangePercent(), -0.01435, 0.0);
    }

    @Test
    public void testRealtimeBulkQuoteResponseError() throws IOException {
        RealtimeBulkQuoteResponse response = RealtimeBulkQuoteResponse.of(error());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testEmptyRealtimeBulkQuoteResponse() throws IOException {
        RealtimeBulkQuoteResponse response = RealtimeBulkQuoteResponse.of(empty());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }
}
