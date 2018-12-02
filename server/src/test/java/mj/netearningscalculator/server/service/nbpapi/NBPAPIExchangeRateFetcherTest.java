package mj.netearningscalculator.server.service.nbpapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NBPAPIExchangeRateFetcherTest {
	
	@Inject
	NBPAPIExchangeRateFetcher nbpAPIExchangeRateFetcher;
	
	BigDecimal successfulFetchExchangeRateValue, unsuccessfulFetchExchangeRateValue;
	
	@Before
	public void initializeVariables() {
		successfulFetchExchangeRateValue = nbpAPIExchangeRateFetcher.fetchExchangeRate("EUR");
		unsuccessfulFetchExchangeRateValue = nbpAPIExchangeRateFetcher.fetchExchangeRate("BY");
	}
	
	@Test
	public void nonNullTest() {
		assertNotNull(successfulFetchExchangeRateValue);
		assertNotNull(unsuccessfulFetchExchangeRateValue);
	}
	
	
	@Test
	public void successfulFetchExchangeRateTest() {
		assertTrue(successfulFetchExchangeRateValue.compareTo(BigDecimal.ZERO) > 0);
	}
	
	@Test
	public void unsuccessfulFetchExchangeRateTest() {
		assertEquals(new BigDecimal("-1"), unsuccessfulFetchExchangeRateValue);
	}
	
}
