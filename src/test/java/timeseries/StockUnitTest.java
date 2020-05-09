package timeseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.crazzyghost.alphavantage.timeseries.response.StockUnit;

import org.junit.Test;

public class StockUnitTest {

    @Test
    public void testStockUnit(){

        StockUnit unit = new StockUnit.Builder()
            .open(1.0)
            .close(2.0)
            .high(3.0)
            .low(4.0)
            .volume(5l)
            .adjustedClose(6.0)
            .dividendAmount(7.0)
            .splitCoefficient(8.0)
            .time("20-05-2099")
            .build();

        assertEquals(unit.getOpen(), 1.0, 0.0);
        assertEquals(unit.getClose(), 2.0, 0.0);
        assertEquals(unit.getHigh(), 3.0, 0.0);
        assertEquals(unit.getLow(), 4.0, 0.0);
        assertEquals(unit.getVolume(), 5l);
        assertEquals(unit.getAdjustedClose(), 6.0, 0.0);
        assertEquals(unit.getDividendAmount(), 7.0, 0.0);
        assertEquals(unit.getSplitCoefficient(), 8.0, 0.0);
        assertEquals(unit.getDate(), "20-05-2099");
        assertNotNull(unit.toString());
    }


}