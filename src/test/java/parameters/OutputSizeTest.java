package parameters;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.parameters.OutputSize;

import org.junit.Test;

public class OutputSizeTest {

    @Test
    public void testCompact(){
        assertEquals(OutputSize.COMPACT.toString(), "compact");
    }

    @Test
    public void testFull(){
        assertEquals(OutputSize.FULL.toString(), "full");
    }
}