package mj.netearningscalculator.server.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mj.netearningscalculator.server.domain.SupportedCountries;

/**
 * Service validating user input.
 * 
 * @author MJazy
 */
@Service("userInputValidator")
public class UserInputValidator {

	@Inject
	SupportedCountries supportedCountries;

	/**
	 * Method validating user input.
	 * 
	 * @param countryCode should be compliant with ISO 3166-1 and of supported country.
	 * @param grossDailyEarnings should be greater than 0.
	 * @return true if input is valid, false if else.
	 */
	public boolean validate(String countryCode, BigDecimal grossDailyEarnings) {
		if (supportedCountries.containsCountry(countryCode)) {
			return validateGrossDailyEarnings(grossDailyEarnings);
		}
		return false;
	}

	private boolean validateGrossDailyEarnings(BigDecimal grossDailyEarnings) {
		if (grossDailyEarnings != null) {
			if (grossDailyEarnings.compareTo(BigDecimal.ZERO) > 0) {
				return true;
			}

		}
		return false;
	}
}
