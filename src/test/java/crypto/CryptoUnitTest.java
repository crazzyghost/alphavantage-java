package crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;

import com.crazzyghost.alphavantage.cryptocurrency.response.CryptoUnit;

import org.junit.Test;

public class CryptoUnitTest {

    @Test
    public void testCryptoUnit(){

        CryptoUnit unit = new CryptoUnit.Builder()
            .close(40.0)
            .closeUSD(45.0)
            .high(56.76)
            .highUSD(99.22)
            .open(13.5)
            .openUSD(66.4)
            .low(3.5)
            .lowUSD(14.2)
            .marketCap(56.5)
            .volume(100.0)
            .build();

        assertEquals(unit.getClose(), 40.0, 0);
        assertEquals(unit.getCloseUSD(), 45.0, 0);
        assertEquals(unit.getHigh(), 56.76, 0);
        assertEquals(unit.getHighUSD(), 99.22, 0);
        assertEquals(unit.getOpen(), 13.5, 0);
        assertEquals(unit.getOpenUSD(), 66.4, 0);
        assertEquals(unit.getLow(), 3.5, 0);
        assertEquals(unit.getLowUSD(), 14.2, 0);
        assertEquals(unit.getMarketCap(), 56.5, 0);
        assertEquals(unit.getVolume(), 100.0, 0);
        assertNotNull(unit.toString());
    }

}