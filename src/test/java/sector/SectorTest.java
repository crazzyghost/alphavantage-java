package sector;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.sector.SectorResponse;
import com.crazzyghost.alphavantage.sector.SectorUnit;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;
import static util.TestUtils.stream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.errorMessage;
import static util.TestUtils.sectorUrl;

public class SectorTest {
    
    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("sector");
        
        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(4, TimeUnit.SECONDS)
            .callTimeout(4, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mockInterceptor)
            .build();

        config = Config.builder()
            .key("demo")
            .httpClient(client)
            .build();
        
        AlphaVantage.api().init(config);

    }

    @Test
    public void testSector() throws Exception{
        
        mockInterceptor.addRule().get(sectorUrl()).respond(stream("data"));
        
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SectorResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .sector()
            .onSuccess(e -> {
                ref.set(e);
                lock.countDown();
            })
            .fetch();
        lock.await();

        SectorResponse response = ref.get();
        assertNotNull(response);

        assertEquals("US Sector Performance (realtime & historical)", response.getMetaData().getInformation());
        assertNotNull(response.getMetaData().getLastRefreshed());
        assertNotNull(response.getMetaData().toString());
        assertNotNull(response.toString());
        
        SectorUnit realtime = response.getRealTimePerformance();
        assertEquals(realtime.getEnergy(), "7.46%");
        assertEquals(realtime.getFinancials(), "3.87%");
        assertEquals(realtime.getIndustrials(), "3.71%");
        assertEquals(realtime.getRealEstate(), "3.60%");
        assertEquals(realtime.getInformationTechnology(), "2.69%");
        assertEquals(realtime.getMaterials(), "2.50%");
        assertEquals(realtime.getConsumerDiscretionary(), "2.13%");
        assertEquals(realtime.getCommunicationServices(), "1.68%");
        assertEquals(realtime.getHealthCare(), "1.66%");
        assertEquals(realtime.getConsumerStaples(), "1.45%");
        assertEquals(realtime.getUtilities(), "1.36%");
        assertNotNull(realtime.toString());
        
        SectorUnit oneDay = response.getOneDayPerformance();
        assertEquals(oneDay.getEnergy(), "7.46%");
        assertEquals(oneDay.getFinancials(), "3.87%");
        assertEquals(oneDay.getIndustrials(), "3.71%");
        assertEquals(oneDay.getRealEstate(), "3.60%");
        assertEquals(oneDay.getInformationTechnology(), "2.69%");
        assertEquals(oneDay.getMaterials(), "2.50%");
        assertEquals(oneDay.getConsumerDiscretionary(), "2.13%");
        assertEquals(oneDay.getCommunicationServices(), "1.68%");
        assertEquals(oneDay.getHealthCare(), "1.66%");
        assertEquals(oneDay.getConsumerStaples(), "1.45%");
        assertEquals(oneDay.getUtilities(), "1.36%");

        SectorUnit fiveDay = response.getFiveDayPerformance();
        assertEquals(fiveDay.getEnergy(), "15.41%");
        assertEquals(fiveDay.getFinancials(), "12.16%");
        assertEquals(fiveDay.getIndustrials(), "10.52%");
        assertEquals(fiveDay.getRealEstate(), "7.07%");
        assertEquals(fiveDay.getInformationTechnology(), "3.65%");
        assertEquals(fiveDay.getMaterials(), "7.65%");
        assertEquals(fiveDay.getConsumerDiscretionary(), "4.75%");
        assertEquals(fiveDay.getCommunicationServices(), "2.38%");
        assertEquals(fiveDay.getHealthCare(), "0.23%");
        assertEquals(fiveDay.getConsumerStaples(), "1.89%");
        assertEquals(fiveDay.getUtilities(), "2.36%");

        SectorUnit oneMonth = response.getOneMonthPerformance();
        assertEquals(oneMonth.getEnergy(), "19.17%");
        assertEquals(oneMonth.getFinancials(), "19.92%");
        assertEquals(oneMonth.getIndustrials(), "21.37%");
        assertEquals(oneMonth.getRealEstate(), "12.59%");
        assertEquals(oneMonth.getInformationTechnology(), "12.46%");
        assertEquals(oneMonth.getMaterials(), "16.92%");
        assertEquals(oneMonth.getConsumerDiscretionary(), "14.37%");
        assertEquals(oneMonth.getCommunicationServices(), "9.89%");
        assertEquals(oneMonth.getHealthCare(), "5.46%");
        assertEquals(oneMonth.getConsumerStaples(), "4.52%");
        assertEquals(oneMonth.getUtilities(), "8.20%");

        SectorUnit threeMonth = response.getThreeMonthPerformance();
        assertEquals(threeMonth.getEnergy(), "-3.92%");
        assertEquals(threeMonth.getFinancials(), "-5.42%");
        assertEquals(threeMonth.getIndustrials(), "-2.45%");
        assertEquals(threeMonth.getRealEstate(), "-7.68%");
        assertEquals(threeMonth.getInformationTechnology(), "8.41%");
        assertEquals(threeMonth.getMaterials(), "5.49%");
        assertEquals(threeMonth.getConsumerDiscretionary(), "9.67%");
        assertEquals(threeMonth.getCommunicationServices(), "4.26%");
        assertEquals(threeMonth.getHealthCare(), "3.28%");
        assertEquals(threeMonth.getConsumerStaples(), "-5.06%");
        assertEquals(threeMonth.getUtilities(), "-10.92%");

        SectorUnit yearToDate = response.getYearToDatePerformance();
        assertEquals(yearToDate.getEnergy(), "-26.28%");
        assertEquals(yearToDate.getFinancials(), "-15.01%");
        assertEquals(yearToDate.getIndustrials(), "-8.36%");
        assertEquals(yearToDate.getRealEstate(), "-4.54%");
        assertEquals(yearToDate.getInformationTechnology(), "10.55%");
        assertEquals(yearToDate.getMaterials(), "-2.81%");
        assertEquals(yearToDate.getConsumerDiscretionary(), "6.45%");
        assertEquals(yearToDate.getCommunicationServices(), "1.96%");
        assertEquals(yearToDate.getHealthCare(), "1.07%");
        assertEquals(yearToDate.getConsumerStaples(), "-4.60%");
        assertEquals(yearToDate.getUtilities(), "-5.84%");

    }

 
    @Test
    public void testSectorFailure() throws InterruptedException{
        mockInterceptor.addRule().get(sectorUrl()).respond(errorMessage).code(400);
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .sector()
            .onFailure(e -> {
                ref.set(e);
                lock.countDown();
            })
            .fetch();
        lock.await();
        assertNotNull(ref.get());
    }

    @Test
    public void testSectorError() throws InterruptedException{
        mockInterceptor.addRule().get(sectorUrl()).respond(errorMessage);
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<SectorResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .sector()
            .onSuccess(e ->lock.countDown())
            .fetch();
        lock.await();
        assertNull(ref.get());
    }
}