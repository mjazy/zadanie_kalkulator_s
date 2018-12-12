package mj.netearningscalculator.server.service.componentservices.nbpapi;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import mj.netearningscalculator.server.service.componentservices.nbpapi.NBPAPIExchangeRateFetcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NBPAPIExchangeRateFetcherTest {

	@Inject
	NBPAPIExchangeRateFetcher nbpAPIExchangeRateFetcher;

	BigDecimal successfulFetchExchangeRateValue, unsuccessfulFetchExchangeRateValue;
	OutputCapture outputCapture;

	@Before
	public void initializeVariables() {
		successfulFetchExchangeRateValue = nbpAPIExchangeRateFetcher.fetchExchangeRate("EUR");
		unsuccessfulFetchExchangeRateValue = nbpAPIExchangeRateFetcher.fetchExchangeRate("BY");
		outputCapture = new OutputCapture();
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

	@Test
	public void loggingTest() {
		// Log should be added with unsuccesfulFetchRateValue run.
		outputCapture.expect(containsString("404 NotFound"));
	}

}
