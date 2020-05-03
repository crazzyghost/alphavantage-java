package indicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.crazzyghost.alphavantage.indicator.response.AROONIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.BBANDSIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.MACDIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.MAMAIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.STOCHFIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.STOCHIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.STOCHRSIIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;

import org.junit.Test;

public class IndicatorUnitTest {
 
    @Test 
    public void testAROONIndicatorUnit(){
        AROONIndicatorUnit unit = new AROONIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getAroonUpValue(), 4.5, 0.0);
        assertEquals(unit.getAroonDownValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }
    
    @Test 
    public void testMACDIndicatorUnit(){
        MACDIndicatorUnit unit = new MACDIndicatorUnit("2012-02-02", 4.5, 4.0, 3.5);
        assertEquals(unit.getMacdHistValue(), 4.5, 0.0);
        assertEquals(unit.getMacdSignalValue(), 4.0, 0.0);
        assertEquals(unit.getMacdValue(), 4.0, 3.5);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testMAMAIndicatorUnit(){
        MAMAIndicatorUnit unit = new MAMAIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getFamaValue(), 4.5, 0.0);
        assertEquals(unit.getMamaValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testSimpleIndicatorUnit(){
        SimpleIndicatorUnit unit = new SimpleIndicatorUnit("2012-02-02", 4.5,"SMA");
        assertEquals(unit.getValue(), 4.5, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }
    
    @Test 
    public void testSimpleIndicatorUnitWithNoIndicator(){
        SimpleIndicatorUnit unit = new SimpleIndicatorUnit("2012-02-02", 4.5);
        assertEquals(unit.getValue(), 4.5, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testSTOCHIndicatorUnit(){
        STOCHIndicatorUnit unit = new STOCHIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getSlowKValue(), 4.5, 0.0);
        assertEquals(unit.getSlowDValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testSTOCHFIndicatorUnit(){
        STOCHFIndicatorUnit unit = new STOCHFIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getFastKValue(), 4.5, 0.0);
        assertEquals(unit.getFastDValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testSTOCHRSIIndicatorUnit(){
        STOCHRSIIndicatorUnit unit = new STOCHRSIIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getFastKValue(), 4.5, 0.0);
        assertEquals(unit.getFastDValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test
    public void testBBANDSIndicatorUnit(){
        BBANDSIndicatorUnit unit = new BBANDSIndicatorUnit("2020-05-02", 220.5, 220.5, 220.5);
        assertEquals(unit.getRealLowerBandValue(), 220.5, 0.0);
        assertEquals(unit.getRealMiddleBandValue(), 220.5, 0.0);
        assertEquals(unit.getRealUpperBandValue(), 220.5, 0.0);
        assertEquals(unit.getRealLowerBandValue(), 220.5, 0.0);
        assertNotNull(unit.toString());
    }
}