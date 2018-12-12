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
		BigDecimal taxFreeAllowance = new BigDecimal("3000");
		polandFinances = new CountryFinances(currency, incomeTax, fixedCosts, taxFreeAllowance);

		relevantToStringValue = String.format(
				"currency: '%s', incomeTax: '%s', fixedCosts: '%s', taxFreeAllowance: '%s'", currency, incomeTax,
				fixedCosts, taxFreeAllowance);

	}

	@Test
	public void toStringTest() {
		assertEquals(relevantToStringValue, polandFinances.toString());
	}
}
