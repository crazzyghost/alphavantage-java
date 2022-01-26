package parser.util;

import com.crazzyghost.alphavantage.parser.util.ParserUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParserUtilTest {

    @Test
    public void testGetLongFromString() {
        long actualNum = ParserUtil.getNumberFromString("1", Long::parseLong);
        assertEquals(1L, actualNum);
    }

    @Test
    public void testGetLongFromNotNumberString() {
        Long actualNum = ParserUtil.getNumberFromString("-", Long::parseLong);
        assertNull(actualNum);
    }

    @Test
    public void testGetDoubleFromString() {
        double actualNum = ParserUtil.getNumberFromString("-1.2312342342", Double::parseDouble);
        assertEquals(-1.2312342342, actualNum, 0.0);
    }

    @Test
    public void testGetDoubleFromNotNumberString() {
        Double actualNum = ParserUtil.getNumberFromString("-", Double::parseDouble);
        assertNull(actualNum);
    }
}