package util;

import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.UrlParameter;
import com.crazzyghost.alphavantage.parameters.DataType;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlExtractorTest {

    @Test
    public void testAnnotatedField() {
        AnnotatedFixture fixture = new AnnotatedFixture();
        fixture.fromSymbol = "USD";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain from_symbol parameter", result.contains("from_symbol=USD"));
    }

    @Test
    public void testUnannotatedField() {
        UnannotatedFixture fixture = new UnannotatedFixture();
        fixture.symbol = "IBM";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain lowercased field name", result.contains("symbol=IBM"));
    }

    @Test
    public void testUnannotatedFieldWithCamelCase() {
        CamelCaseFixture fixture = new CamelCaseFixture();
        fixture.dataType = DataType.JSON;
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should lowercase camelCase field name", result.contains("datatype=json"));
    }

    @Test
    public void testNullFieldIsSkipped() {
        NullFieldFixture fixture = new NullFieldFixture();
        fixture.symbol = "IBM";
        fixture.absent = null;
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain non-null field", result.contains("symbol=IBM"));
        assertFalse("Should not contain null field", result.contains("absent"));
    }

    @Test
    public void testNullAnnotatedFieldIsSkipped() {
        AnnotatedNullFixture fixture = new AnnotatedNullFixture();
        fixture.fromSymbol = null;
        fixture.symbol = "IBM";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain non-null field", result.contains("symbol=IBM"));
        assertFalse("Should not contain null annotated field", result.contains("from_symbol"));
    }

    @Test
    public void testSuperclassField() {
        SubclassFixture fixture = new SubclassFixture();
        fixture.parentField = "parent";
        fixture.childField = "child";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain parent field", result.contains("parentfield=parent"));
        assertTrue("Should contain child field", result.contains("childfield=child"));
    }

    @Test
    public void testEnumUsesToString() {
        EnumFixture fixture = new EnumFixture();
        fixture.dataType = DataType.JSON;
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should use enum toString() value", result.contains("datatype=json"));
        assertFalse("Should not use enum name()", result.contains("JSON"));
    }

    @Test
    public void testPrimitiveInt() {
        PrimitiveFixture fixture = new PrimitiveFixture();
        fixture.count = 0;
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should serialize primitive int even when 0", result.contains("count=0"));
    }

    @Test
    public void testApikeyAppendedLast() {
        SimpleFixture fixture = new SimpleFixture();
        fixture.symbol = "IBM";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should end with apikey=", result.endsWith("apikey="));
    }

    @Test
    public void testMixedAnnotatedAndUnannotated() {
        MixedFixture fixture = new MixedFixture();
        fixture.fromSymbol = "USD";
        fixture.toSymbol = "EUR";
        fixture.function = "FX_DAILY";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain annotated from_symbol", result.contains("from_symbol=USD"));
        assertTrue("Should contain annotated to_symbol", result.contains("to_symbol=EUR"));
        assertTrue("Should contain unannotated function", result.contains("function=FX_DAILY"));
    }

    @Test
    public void testSyntheticFieldsAreSkipped() {
        // Synthetic fields (compiler-generated) should be skipped
        // This test uses a nested class which generates a synthetic this$0 field
        OuterClass.InnerFixture fixture = new OuterClass().new InnerFixture();
        fixture.symbol = "IBM";
        
        String result = UrlExtractor.extract(fixture);
        
        assertTrue("Should contain regular field", result.contains("symbol=IBM"));
        assertFalse("Should not contain synthetic field", result.contains("this$"));
    }

    // Test fixtures

    static class OuterClass {
        class InnerFixture {
            String symbol;
        }
    }

    static class AnnotatedFixture {
        @UrlParameter("from_symbol")
        String fromSymbol;
    }

    static class UnannotatedFixture {
        String symbol;
    }

    static class CamelCaseFixture {
        DataType dataType;
    }

    static class NullFieldFixture {
        String symbol;
        String absent;
    }

    static class AnnotatedNullFixture {
        @UrlParameter("from_symbol")
        String fromSymbol;
        String symbol;
    }

    static class ParentFixture {
        String parentField;
    }

    static class SubclassFixture extends ParentFixture {
        String childField;
    }

    static class EnumFixture {
        DataType dataType;
    }

    static class PrimitiveFixture {
        int count;
    }

    static class SimpleFixture {
        String symbol;
    }

    static class MixedFixture {
        @UrlParameter("from_symbol")
        String fromSymbol;
        
        @UrlParameter("to_symbol")
        String toSymbol;
        
        String function;
    }
}
