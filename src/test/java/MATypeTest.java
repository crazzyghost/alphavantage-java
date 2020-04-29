import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.parameters.MAType;

import org.junit.Test;

public class MATypeTest {


    @Test
    public void testSMA(){
        assertEquals(MAType.SMA.toString(), "0");
    }

    @Test
    public void testEMA(){
        assertEquals(MAType.EMA.toString(), "1");
    }
    
    @Test
    public void testWMA(){
        assertEquals(MAType.WMA.toString(), "2");
    }

    @Test
    public void testDEMA(){
        assertEquals(MAType.DEMA.toString(), "3");
    }

    @Test
    public void testTEMA(){
        assertEquals(MAType.TEMA.toString(), "4");
    }

    @Test
    public void testTRIMA(){
        assertEquals(MAType.TRIMA.toString(), "5");
    }

    @Test
    public void testT3(){
        assertEquals(MAType.T3.toString(), "6");
    }

    @Test
    public void testKAMA(){
        assertEquals(MAType.KAMA.toString(), "7");
    }
    
    @Test
    public void testMAMA(){
        assertEquals(MAType.MAMA.toString(), "8");
    }
}