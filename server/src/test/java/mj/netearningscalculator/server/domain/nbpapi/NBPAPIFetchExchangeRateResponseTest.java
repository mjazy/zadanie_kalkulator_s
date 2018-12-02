package mj.netearningscalculator.server.domain.nbpapi;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class NBPAPIFetchExchangeRateResponseTest {

	NBPAPIFetchExchangeRateResponse nbpAPIFetchExchangeRateResponse;
	String relevantToStringValue;

	@Before
	public void initializeVariables() {

		NBPAPIRate nbpAPIRate = new NBPAPIRate("233/C/NBP/2018", new Date(10000000), new BigDecimal("4.25"),
				new BigDecimal("4.27"));
		NBPAPIRate[] nbpAPIRates = new NBPAPIRate[]{nbpAPIRate};

		char table = 'a';
		String currency = "Euro";
		String code = "EUR";

		nbpAPIFetchExchangeRateResponse = new NBPAPIFetchExchangeRateResponse(table, currency, code,
				nbpAPIRates);
		relevantToStringValue = String.format("table: '%s', currency: '%s', code: '%s', %s", table, currency,
				code, nbpAPIRates);
	}
	
	@Test
	public void toStringTest() {
		assertEquals(nbpAPIFetchExchangeRateResponse.toString(), relevantToStringValue);
	}

}
