package forex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static util.TestUtils.json;
import static util.TestUtils.error;

import java.io.IOException;

import com.crazzyghost.alphavantage.forex.response.ForexResponse;

import org.junit.Before;
import org.junit.Test;

import util.TestUtils;

public class ForexResponseTest {
    
    @Before 
    public void setUp(){
        TestUtils.forDirectory("forex");
    }

    @Test
    public void testForexResponse() throws IOException {
        ForexResponse response = ForexResponse.of(json("daily"));
        assertNotNull(response.getMetaData());
        assertEquals(response.getForexUnits().size(), 2);
        assertTrue(response.toString().matches("(.*), errorMessage='null'(.*)"));
        assertNotEquals(response.getMetaData().getFromSymbol(), "");
        assertNotEquals(response.getMetaData().getInformation(), "");
        assertNotEquals(response.getMetaData().getInterval(), "");
        assertNotEquals(response.getMetaData().getLastRefreshed(), "");
        assertNotEquals(response.getMetaData().getOutputSize(), "");
        assertNotEquals(response.getMetaData().getTimeZone(), "");
        assertNotEquals(response.getMetaData().getToSymbol(), "");

    }
    
    @Test
    public void testForexResponseError() throws IOException {
        ForexResponse response = ForexResponse.of(error());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }
}