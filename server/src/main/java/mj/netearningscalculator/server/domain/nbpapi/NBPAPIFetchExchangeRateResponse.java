package mj.netearningscalculator.server.domain.nbpapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for response from NBP API to request related to fetching exchange
 * rate.
 */
public class NBPAPIFetchExchangeRateResponse {

	@JsonProperty
	private char table;

	@JsonProperty
	private String currency;

	@JsonProperty
	private String code;

	@JsonProperty
	private NBPAPIRate[] rates;

	/**
	 * Constructor for NBPAPIFetchExchangeRateResponse class.
	 */
	public NBPAPIFetchExchangeRateResponse() {
	}

	/**
	 * Constructor for NBPAPIFetchExchangeRateResponse class.
	 * 
	 * @param table    e.g. 'A'.
	 * @param currency e.g. 'euro'.
	 * @param code     compliant with ISO 4127 e.g. 'EUR'.
	 * @param rates    {@link NBPAPIRate#NBPAPIRates(String, java.util.Date, java.math.BigDecimal, java.math.BigDecimal)}
	 */
	public NBPAPIFetchExchangeRateResponse(char table, String currency, String code, NBPAPIRate[] rates) {
		this.table = table;
		this.currency = currency;
		this.code = code;
		this.rates = rates;
	}

	public char getTable() {
		return table;
	}

	public String getCurrency() {
		return currency;
	}

	/**
	 * Gets currency code.
	 * 
	 * @return code compliant with ISO 4127 e.g. 'EUR'.
	 */
	public String getCode() {
		return code;
	}

	public NBPAPIRate[] getRates() {
		return rates;
	}

	@Override
	public String toString() {
		return String.format("table: '%s', currency: '%s', code: '%s', %s", this.table, this.currency, this.code,
				this.rates);
	}

}
