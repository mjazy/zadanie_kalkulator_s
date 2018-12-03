package mj.netearningscalculator.server.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mj.netearningscalculator.server.domain.CountryFinances;
import mj.netearningscalculator.server.domain.SupportedCountries;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorTest {

	@Inject
	Calculator calculator;

	@Inject
	SupportedCountries supportedCountries;

	CountryFinances germanyFinances, polandFinances, unitedKingdomFinances;
	BigDecimal grossDailyEarnings, eurPLNExchangeRate, gbpPLNExchangeRate, germanRelevantMonthlyNetEarnings,
			polishRelevantMonthlyNetEarnings, britishRelevantMonthlyNetEarnings;

	@Before
	public void initializeVariables() {
		germanyFinances = supportedCountries.getCountryByCode("DE").getFinances();
		polandFinances = supportedCountries.getCountryByCode("PL").getFinances();
		unitedKingdomFinances = supportedCountries.getCountryByCode("UK").getFinances();

		grossDailyEarnings = new BigDecimal("100");
		eurPLNExchangeRate = new BigDecimal("4");
		gbpPLNExchangeRate = new BigDecimal("4.5");

		germanRelevantMonthlyNetEarnings = setNetMonthlyEarningsInPLN(grossDailyEarnings, germanyFinances)
				.multiply(eurPLNExchangeRate);
		polishRelevantMonthlyNetEarnings = setNetMonthlyEarningsInPLN(grossDailyEarnings, polandFinances);
		britishRelevantMonthlyNetEarnings = setNetMonthlyEarningsInPLN(grossDailyEarnings, unitedKingdomFinances)
				.multiply(gbpPLNExchangeRate);

	}

	@Test
	public void calculateMonthlyNetEarningsInPLNTest() {
		assertEquals(germanRelevantMonthlyNetEarnings,
				calculator.calculateNetMonthlyEarningsInPLN(grossDailyEarnings, eurPLNExchangeRate, germanyFinances));
		assertEquals(polishRelevantMonthlyNetEarnings,
				calculator.calculateNetMonthlyEarningsInPLN(grossDailyEarnings, new BigDecimal("1"), polandFinances));
		assertEquals(britishRelevantMonthlyNetEarnings, calculator.calculateNetMonthlyEarningsInPLN(grossDailyEarnings,
				gbpPLNExchangeRate, unitedKingdomFinances));
	}

	private BigDecimal setNetMonthlyEarningsInPLN(BigDecimal grossDailyEarnings, CountryFinances countryFinances) {
		BigDecimal monthLength = new BigDecimal("22");
		BigDecimal grossMonthlyEarningsWithoutFixedCosts = grossDailyEarnings.multiply(monthLength)
				.subtract(countryFinances.getFixedCosts());
		return grossMonthlyEarningsWithoutFixedCosts
				.subtract(grossMonthlyEarningsWithoutFixedCosts.multiply(countryFinances.getIncomeTax()));
	}

}
