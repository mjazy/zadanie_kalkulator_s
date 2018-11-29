package mj.netearningscalculator.server.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Class maintaining currently supported countries. Currently supported
 * countries are Germany, Poland, United Kingdom.
 * 
 * @author MJazy
 *
 */
@Component("supportedCountries")
public class SupportedCountries {

	private List<Country> supportedCountriesList;

	/**
	 * Constructor for SupportedCountries class.
	 */
	public SupportedCountries() {
		setSupportedCountriesList(new ArrayList<Country>());

		CountryFinances germanyFinances = new CountryFinances("EUR", new BigDecimal("0.20"), new BigDecimal("800"));
		Country germany = new Country("Germany", "DE", germanyFinances);

		CountryFinances polandFinances = new CountryFinances("PLN", new BigDecimal("0.19"), new BigDecimal("1200"));
		Country poland = new Country("Poland", "PL", polandFinances);

		CountryFinances unitedKingdomFinances = new CountryFinances("GBP", new BigDecimal("0.25"),
				new BigDecimal("600"));
		Country unitedKingdom = new Country("United Kingdom", "UK", unitedKingdomFinances);

		getSupportedCountriesList().add(germany);
		getSupportedCountriesList().add(poland);
		getSupportedCountriesList().add(unitedKingdom);

	}

	public List<Country> getSupportedCountriesList() {
		return supportedCountriesList;
	}

	public void setSupportedCountriesList(List<Country> supportedCountriesList) {
		this.supportedCountriesList = supportedCountriesList;
	}

	/**
	 * Confirms if country is supported.
	 * 
	 * @param countryCode compliant with ISO 3166-1 e.g. 'PL'.
	 * @return true if country is supported, false if else.
	 */
	public boolean containsCountry(String countryCode) {
		for (Country country : supportedCountriesList) {
			if (country.getCode().equalsIgnoreCase(countryCode)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("supportedCountries: '%s'", supportedCountriesList.toString());
	}

}
