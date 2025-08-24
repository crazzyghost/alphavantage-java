package crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.crazzyghost.alphavantage.cryptocurrency.response.CryptoUnit;

import org.junit.Test;

public class CryptoUnitTest {

    @Test
    public void testCryptoUnit(){

        CryptoUnit unit = new CryptoUnit.Builder()
            .close(40.0)
            .high(56.76)
            .open(13.5)
            .low(3.5)
            .volume(100.0)
            .build();

        assertEquals(unit.getClose(), 40.0, 0);
        assertEquals(unit.getHigh(), 56.76, 0);
        assertEquals(unit.getOpen(), 13.5, 0);
        assertEquals(unit.getLow(), 3.5, 0);
        assertEquals(unit.getVolume(), 100.0, 0);
        assertNotNull(unit.toString());
    }

}