package mj.netearningscalculator.server.domain.nbpapi;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class NBPAPIRateTest {

	NBPAPIRate nbpAPIRate;
	String relevantToStringValue;

	@Before
	public void initializeVariables() {
		String testNo = "233/C/NBP/2018";
		Date testDate = new Date(10000000);
		BigDecimal testBid = new BigDecimal("4.25");
		BigDecimal testAsk = new BigDecimal("4.27");

		nbpAPIRate = new NBPAPIRate(testNo, testDate, testBid, testAsk);
		relevantToStringValue = String.format("no: '%s', effectiveDate: '%s', bid: '%s', ask: '%s'", testNo, testDate,
				testBid, testAsk);
	}

	@Test
	public void toStringTest() {
		assertEquals(nbpAPIRate.toString(), relevantToStringValue);
	}

}
