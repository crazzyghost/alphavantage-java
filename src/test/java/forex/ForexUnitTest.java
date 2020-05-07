package forex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.crazzyghost.alphavantage.forex.response.ForexUnit;

import org.junit.Test;

public class ForexUnitTest {

    @Test
    public void testForexUnit(){

        ForexUnit unit = new ForexUnit.Builder()
            .close(34.5)
            .high(50.5)
            .low(40.5)
            .open(35.5)
            .date("20-05-2099")
            .build();
        
        assertEquals(unit.getClose(), 34.5, 0);
        assertEquals(unit.getHigh(), 50.5, 0);
        assertEquals(unit.getLow(), 40.5, 0);
        assertEquals(unit.getOpen(), 35.5, 0);
        assertEquals(unit.getDate(), "20-05-2099");
        assertNotNull(unit.toString());
    
    }
}