package economicindicator;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.economicindicator.request.*;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.Maturity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EconomicIndicatorRequestTest {
    @Test
    public void testRealGdpRequest() {
        String expected = "https://www.alphavantage.co/query?interval=quarterly&function=REAL_GDP&datatype=json&apikey=demo";
        RealGdpRequest request = new RealGdpRequest.Builder()
                .dataType(DataType.JSON)
                .interval(Interval.QUARTERLY)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test(expected = AlphaVantageException.class)
    public void testRealGdpRequestWithIncorrectInterval() {
        String expected = "https://www.alphavantage.co/query?interval=quarterly&function=REAL_GDP&datatype=json&apikey=demo";
        RealGdpRequest request = new RealGdpRequest.Builder()
                .interval(Interval.MONTHLY)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testRealGdpPerCapitaRequest() {
        String expected = "https://www.alphavantage.co/query?function=REAL_GDP_PER_CAPITA&datatype=json&apikey=demo";
        RealGdpPerCapitaRequest request = new RealGdpPerCapitaRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testConsumerSentimentRequest() {
        String expected = "https://www.alphavantage.co/query?function=CONSUMER_SENTIMENT&datatype=json&apikey=demo";
        ConsumerSentimentRequest request = new ConsumerSentimentRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testCpiRequest() {
        String expected = "https://www.alphavantage.co/query?interval=monthly&function=CPI&datatype=json&apikey=demo";
        CpiRequest request = new CpiRequest.Builder()
                .interval(Interval.MONTHLY)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test(expected = AlphaVantageException.class)
    public void testCpiRequestWithIncorrectInterval() {
        String expected = "https://www.alphavantage.co/query?interval=monthly&function=CPI&datatype=json&apikey=demo";
        CpiRequest request = new CpiRequest.Builder()
                .interval(Interval.ANNUAL)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testDurableGoodsRequest() {
        String expected = "https://www.alphavantage.co/query?function=DURABLES&datatype=json&apikey=demo";
        DurableGoodsOrdersRequest request = new DurableGoodsOrdersRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testFederalFundsRateRequest() {
        String expected = "https://www.alphavantage.co/query?interval=daily&function=FEDERAL_FUNDS_RATE&datatype=json&apikey=demo";
        FederalFundsRateRequest request = new FederalFundsRateRequest.Builder()
                .interval(Interval.DAILY)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test(expected = AlphaVantageException.class)
    public void testFederalFundsRateRequestWithIncorrectInterval() {
        String expected = "https://www.alphavantage.co/query?function=FEDERAL_FUNDS_RATE&datatype=json&apikey=demo";
        FederalFundsRateRequest request = new FederalFundsRateRequest.Builder()
                .interval(Interval.FIFTEEN_MIN)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testInflationRequest() {
        String expected = "https://www.alphavantage.co/query?function=INFLATION&datatype=json&apikey=demo";
        InflationRequest request = new InflationRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testInflationExpectationRequest() {
        String expected = "https://www.alphavantage.co/query?function=INFLATION_EXPECTATION&datatype=json&apikey=demo";
        InflationExpectationRequest request = new InflationExpectationRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testNonFarmPayrollRequest() {
        String expected = "https://www.alphavantage.co/query?function=NONFARM_PAYROLL&datatype=json&apikey=demo";
        NonFarmPayrollRequest request = new NonFarmPayrollRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testRetailSalesRequest() {
        String expected = "https://www.alphavantage.co/query?function=RETAIL_SALES&datatype=json&apikey=demo";
        RetailSalesRequest request = new RetailSalesRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testUnemploymentRateRequest() {
        String expected = "https://www.alphavantage.co/query?function=UNEMPLOYMENT&datatype=json&apikey=demo";
        UnemploymentRateRequest request = new UnemploymentRateRequest.Builder()
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testTreasuryYieldRequest() {
        String expected = "https://www.alphavantage.co/query?interval=daily&maturity=5year&function=TREASURY_YIELD&datatype=json&apikey=demo";
        TreasuryYieldRequest request = new TreasuryYieldRequest.Builder()
                .interval(Interval.DAILY)
                .maturity(Maturity.FIVE_YEAR)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test(expected = AlphaVantageException.class)
    public void testTreasuryYieldRequestWithInvalid() {
        String expected = "https://www.alphavantage.co/query?interval=daily&maturity=5year&function=TREASURY_YIELD&datatype=json&apikey=demo";
        TreasuryYieldRequest request = new TreasuryYieldRequest.Builder()
                .interval(Interval.FIFTEEN_MIN)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

}
