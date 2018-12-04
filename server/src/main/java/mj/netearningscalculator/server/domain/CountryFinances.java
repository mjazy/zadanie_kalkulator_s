package mj.netearningscalculator.server.domain;

import java.math.BigDecimal;

/**
 * Data model for country's finances.
 * 
 * @author MJazy
 *
 */
public class CountryFinances {

	private String currency;
	private BigDecimal incomeTax;
	private BigDecimal fixedCosts;

	/**
	 * CountryFinances class constructor.
	 */
	public CountryFinances() {
	}
	
	/**
	 * CountryFinances class constructor.
	 * 
	 * @param currency   compliant with ISO 4127 e.g. 'PLN'.
	 * @param incomeTax  with value range between 0 and 1 where 0 stands for 0% and
	 *                   1 for 100% e.g. '0.19'.
	 * @param fixedCosts to be deducted from gross earnings e.g. '2000'.
	 */
	public CountryFinances(String currency, BigDecimal incomeTax, BigDecimal fixedCosts) {
		this.currency = currency;
		this.incomeTax = incomeTax;
		this.fixedCosts = fixedCosts;
	}

	/**
	 * Get country's currency.
	 * 
	 * @return currency compliant with ISO 4127 e.g. 'PLN'.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Set country's currency.
	 * 
	 * @param currency compliant with ISO 4127 e.g. 'PLN'.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Get country's income tax.
	 * 
	 * @return incomeTax with value range between 0 and 1 where 0 stands for 0% and
	 *         1 for 100% e.g. '0.19'.
	 */
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	/**
	 * Set country's income tax.
	 * 
	 * @param incomeTax with value range between 0 and 1 where 0 stands for 0% and 1
	 *                  for 100% e.g. '0,19'
	 */
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	public BigDecimal getFixedCosts() {
		return fixedCosts;
	}

	public void setFixedCosts(BigDecimal fixedCosts) {
		this.fixedCosts = fixedCosts;
	}

	@Override
	public String toString() {
		return String.format("currency: '%s', incomeTax: '%s', fixedCosts: '%s'", this.currency, this.incomeTax,
				this.fixedCosts);
	}

}
