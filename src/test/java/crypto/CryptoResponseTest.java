package crypto;

import java.io.IOException;

import com.crazzyghost.alphavantage.cryptocurrency.response.CryptoResponse;
import com.crazzyghost.alphavantage.cryptocurrency.response.RatingResponse;

import org.junit.Before;
import org.junit.Test;

import util.TestUtils;
import static util.TestUtils.json;
import static util.TestUtils.stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static util.TestUtils.error;
import static util.TestUtils.errorMessage;
import static util.TestUtils.exchangeRateUrl;

public class CryptoResponseTest {

    @Before
    public void setUp(){
        TestUtils.forDirectory("crypto");
    }

    @Test
    public void testDigitalCurrencyResponse() throws IOException {
        CryptoResponse response = CryptoResponse.of(json("daily"), "CNY");
        assertNotNull(response.getMetaData());
        assertEquals(response.getCryptoUnits().size(), 2);
        assertTrue(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }
    
    @Test
    public void testDigitalCurrencyResponseError() throws IOException {
        CryptoResponse response = CryptoResponse.of(error(), "CNY");
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testHealthIndexResponse() throws IOException {
        RatingResponse response = RatingResponse.of(json("rating"));
        assertEquals(response.getSymbol(), "BTC");
        assertEquals(response.getName(), "Bitcoin");
        assertEquals(response.getFcasRating(), "Attractive");
        assertEquals(response.getFcasScore(), "887");
        assertEquals(response.getDeveloperScore(), "844");
        assertEquals(response.getMarketMaturityScore(), "838");
        assertEquals(response.getUtilityScore(), "951");
        assertEquals(response.getLastRefreshed(), "2020-05-03 00:00:00");
        assertEquals(response.getTimeZone(), "UTC");
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));        

    }
    
    @Test
    public void testHealthIndexResponseError() throws IOException {
        RatingResponse response = RatingResponse.of(error());
        assertNotNull(response.getErrorMessage());
    }

}