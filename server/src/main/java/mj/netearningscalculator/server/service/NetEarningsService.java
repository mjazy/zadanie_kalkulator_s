package mj.netearningscalculator.server.service;

import java.math.BigDecimal;

import javax.inject.Named;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import mj.netearningscalculator.server.domain.Country;
import mj.netearningscalculator.server.domain.SupportedCountries;
import mj.netearningscalculator.server.service.componentservices.Calculator;
import mj.netearningscalculator.server.service.componentservices.UserInputValidator;
import mj.netearningscalculator.server.service.componentservices.nbpapi.NBPAPIExchangeRateFetcher;

/**
 * Service aggregating business logic related to calculating net monthly
 * earnings in 'PLN' based on user's input.
 * 
 * @author MJazy
 *
 */
@Named("netEarningsService")
public class NetEarningsService {

	private UserInputValidator userInputValidator;
	private NBPAPIExchangeRateFetcher nbpAPIExchangeRateFetcher;
	private Calculator calculator;
	private SupportedCountries supportedCountries;

	/**
	 * Constructor for NetEarningsService class.
	 * 
	 * @param userInputValidator        {@link UserInputValidator}
	 * @param nbpAPIExchangeRateFetcher {@link NBPAPIExchangeRateFetcher}
	 * @param calculator                {@link Calculator)
	 * @param supportedCountries        {@link SupportedCountries}
	 */
	public NetEarningsService(UserInputValidator userInputValidator,
			NBPAPIExchangeRateFetcher nbpAPIExchangeRateFetcher, Calculator calculator,
			SupportedCountries supportedCountries) {
		this.userInputValidator = userInputValidator;
		this.nbpAPIExchangeRateFetcher = nbpAPIExchangeRateFetcher;
		this.calculator = calculator;
		this.supportedCountries = supportedCountries;
	}

	public ResponseEntity<String> runService(String countryCode, BigDecimal grossDailyEarnings) {

		if (!userInputValidator.validate(countryCode, grossDailyEarnings)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input parameters.");
		}

		Country country = supportedCountries.getCountryByCode(countryCode);
		BigDecimal exchangeRate = setExchangeRate(country);

		// if exchange rate < 0
		if (exchangeRate.compareTo(BigDecimal.ZERO) < 0) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("External service error.");
		} else {
			BigDecimal netMonthlyEarningsInPLN = calculator.calculateNetMonthlyEarningsInPLN(grossDailyEarnings,
					exchangeRate, country.getFinances());
			return ResponseEntity.status(HttpStatus.OK).body(netMonthlyEarningsInPLN.toString());
		}

	}

	private BigDecimal setExchangeRate(Country country) {
		if (country.getCode().equals("PL")) {
			return new BigDecimal("1");
		} else {
			return nbpAPIExchangeRateFetcher.fetchExchangeRate(country.getFinances().getCurrency());
		}
	}

}
