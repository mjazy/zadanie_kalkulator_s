package mj.netearningscalculator.server.service;

import java.math.BigDecimal;

/**
 * Representation of service fetching exchange rate from an API.
 * @author MJazy
 *
 */
public interface ExchangeRateFetcherInterface {
	
	/**
	 * Method for fetching exchange rate between supplied Currency and PLN (bid).
	 * @param currency compliant with ISO 4217 e.g. 'EUR.
	 * @return exchange rate returned by API or '-1' in case issue appeared.
	 */
	BigDecimal fetchExchangeRate(String currency);

}
