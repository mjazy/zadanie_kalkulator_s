package mj.netearningscalculator.server.service.componentservices;

import java.math.BigDecimal;

import javax.inject.Named;

import mj.netearningscalculator.server.domain.CountryFinances;

/**
 * Service calculating net monthly earnings in 'PLN'.
 * 
 * @author MJazy
 *
 */
@Named("calculator")
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
		// If grossMonthlyEarnings < taxFreeAllowance.
		if (grossMonthlyEarnings.compareTo(countryFinances.getTaxFreeAllowance()) < 0) {
			return grossMonthlyEarnings.multiply(exchangeRate);
		} else {
			BigDecimal netMonthlyEarnings = calculateNetMonthlyEarnings(grossMonthlyEarnings, countryFinances);
			return netMonthlyEarnings.multiply(exchangeRate);

		}
	}

	private BigDecimal calculateNetMonthlyEarnings(BigDecimal grossMonthlyEarnings, CountryFinances countryFinances) {
		BigDecimal monthlyGrossEarningsWithoutFixedCosts = grossMonthlyEarnings
				.subtract(countryFinances.getFixedCosts());
		BigDecimal taxation = monthlyGrossEarningsWithoutFixedCosts.multiply(countryFinances.getIncomeTax());
		return monthlyGrossEarningsWithoutFixedCosts.subtract(taxation);
	}

}
