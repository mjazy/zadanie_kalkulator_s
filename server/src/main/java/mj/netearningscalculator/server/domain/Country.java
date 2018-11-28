package mj.netearningscalculator.server.domain;

import java.math.BigDecimal;

/**
 * Data model for country.
 * 
 * @author MJazy
 *
 */
public class Country {

	private String name;
	private String code;
	private CountryFinances finances;

	/**
	 * Country class constructor.
	 * 
	 * @param name     e.g. 'Poland'.
	 * @param code     compliant with ISO 3166-1 e.g. 'PL'.
	 * @param finances meeting requirements specified in
	 *                 {@link CountryFinances#CountryFinances(String, BigDecimal, BigDecimal)}
	 */
	public Country(String name, String code, CountryFinances countryFinances) {
		this.name = name;
		this.code = code;
		this.finances = countryFinances;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets country code.
	 * 
	 * @return ISO 3166-1 compliant country code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets country code.
	 * 
	 * @param code compliant with ISO 3166-1. e.g. 'PL'.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public CountryFinances getFinances() {
		return finances;
	}

	public void setFinances(CountryFinances finances) {
		this.finances = finances;
	}

	@Override
	public String toString() {
		return String.format("name: '%s', code: '%s', %s", this.name, this.code, this.finances);
	}
}