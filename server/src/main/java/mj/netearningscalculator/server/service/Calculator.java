package mj.netearningscalculator.server.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import mj.netearningscalculator.server.domain.CountryFinances;

/**
 * Service calculating net monthly earnings in 'PLN'.
 * 
 * @author MJazy
 *
 */
@Service("calculator")
public class Calculator {

	private static final int MONTH_LENGTH = 22;

	/**
	 * Method calculating net monthly earnings.
	 * 
	 * @param grossDailyEarnings
	 * @param exchangeRate       between currency in which dailyGrossEarnings are
	 *                           given and 'PLN'. Should be '1' in case country code
	 *                           supplied by client was 'PL'.
	 * @param countryFinances    of a country relevant to code supplied by client.
	 * @return monthly net earnings.
	 */
	public BigDecimal calculateNetMonthlyEarningsInPLN(BigDecimal grossDailyEarnings, BigDecimal exchangeRate,
			CountryFinances countryFinances) {
		BigDecimal grossMonthlyEarnings = grossDailyEarnings.multiply(new BigDecimal(MONTH_LENGTH));
		BigDecimal netMonthlyEarnings = calculateNetMonthlyEarnings(grossMonthlyEarnings, countryFinances);
		return netMonthlyEarnings.multiply(exchangeRate);
	}

	private BigDecimal calculateNetMonthlyEarnings(BigDecimal grossMonthlyEarnings, CountryFinances countryFinances) {
		BigDecimal monthlyGrossEarningsWithoutFixedCosts = grossMonthlyEarnings
				.subtract(countryFinances.getFixedCosts());
		BigDecimal taxation = monthlyGrossEarningsWithoutFixedCosts.multiply(countryFinances.getIncomeTax());
		return monthlyGrossEarningsWithoutFixedCosts.subtract(taxation);
	}

}