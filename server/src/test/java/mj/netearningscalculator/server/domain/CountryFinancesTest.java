package mj.netearningscalculator.server.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CountryFinancesTest {

	CountryFinances polandFinances;
	String relevantToStringValue;

	@Before
	public void initializeVariables() {
		String currency = "PLN";
		BigDecimal incomeTax = new BigDecimal("0.19");
		BigDecimal fixedCosts = new BigDecimal("2000");
		polandFinances = new CountryFinances(currency, incomeTax, fixedCosts);

		relevantToStringValue = String.format("currency: '%s', incomeTax: '%s', fixedCosts: '%s'", currency, incomeTax,
				fixedCosts);

	}

	@Test
	public void toStringTest() {
		assertEquals(relevantToStringValue, polandFinances.toString());
	}
}
