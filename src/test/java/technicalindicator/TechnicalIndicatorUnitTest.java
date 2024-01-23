package technicalindicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.crazzyghost.alphavantage.technicalindicator.response.aroon.AROONIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.bbands.BBANDSIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.htphasor.HTPHASORIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.htsine.HTSINEIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.mama.MAMAIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.stochf.STOCHFIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.stoch.STOCHIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.stochrsi.STOCHRSIIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;

import org.junit.Test;

public class TechnicalIndicatorUnitTest {
 
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
    public void testSimpleTechnicalIndicatorUnit(){
        SimpleTechnicalIndicatorUnit unit = new SimpleTechnicalIndicatorUnit("2012-02-02", 4.5,"SMA");
        assertEquals(unit.getValue(), 4.5, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }
    
    @Test 
    public void testSimpleTechnicalIndicatorUnitWithNoIndicator(){
        SimpleTechnicalIndicatorUnit unit = new SimpleTechnicalIndicatorUnit("2012-02-02", 4.5);
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
        assertEquals(unit.getDate(), "2020-05-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testHTSINEIndicatorUnit(){
        HTSINEIndicatorUnit unit = new HTSINEIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getLeadSineValue(), 4.5, 0.0);
        assertEquals(unit.getSineValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }

    @Test 
    public void testHTPHASORIndicatorUnit(){
        HTPHASORIndicatorUnit unit = new HTPHASORIndicatorUnit("2012-02-02", 4.5, 4.0);
        assertEquals(unit.getPhaseValue(), 4.5, 0.0);
        assertEquals(unit.getQuadratureValue(), 4.0, 0.0);
        assertEquals(unit.getDate(), "2012-02-02");
        assertNotNull(unit.toString());
    }
}