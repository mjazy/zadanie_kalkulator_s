package mj.netearningscalculator.server.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class CountryTest {

	Country poland;
	String relevantToStringValue;

	@Before
	public void initializeVariables() {
		String name = "Poland";
		String code = "PL";

		String currency = "PLN";
		BigDecimal incomeTax = new BigDecimal("0.19");
		BigDecimal fixedCosts = new BigDecimal("2000");
		CountryFinances polandFinances = new CountryFinances(currency, incomeTax, fixedCosts);

		poland = new Country(name, code, polandFinances);

		relevantToStringValue = String.format("name: '%s', code: '%s', %s", name, code, polandFinances);
	}

	@Test
	public void toStringTest() {
		assertEquals(relevantToStringValue, poland.toString());
	}

}
