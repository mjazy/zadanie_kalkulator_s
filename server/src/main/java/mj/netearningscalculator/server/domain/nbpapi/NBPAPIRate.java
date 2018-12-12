package mj.netearningscalculator.server.domain.nbpapi;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data model for rate returned as part of response from NBP API.
 * 
 * @author MJazy
 *
 */
public class NBPAPIRate {

	@JsonProperty
	private String no;

	@JsonProperty
	private Date effectiveDate;

	@JsonProperty
	private BigDecimal bid;

	@JsonProperty
	private BigDecimal ask;

	/**
	 * Constructor for NBPRate class.
	 * 
	 * @param no            e.g. '233/C/NBP/2018'.
	 * @param effectiveDate e.g. '2018-11-30'.
	 * @param bid           e.g. '3.7298'.
	 * @param ask           e.g. '3.8052'.
	 */
	public NBPAPIRate(String no, Date effectiveDate, BigDecimal bid, BigDecimal ask) {
		this.no = no;
		this.effectiveDate = effectiveDate;
		this.bid = bid;
		this.ask = ask;
	}

	/**
	 * Constructor for NBPAPIRate class.
	 */
	public NBPAPIRate() {
	}

	public String getNo() {
		return no;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public BigDecimal getBid() {
		return bid;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public String toString() {
		return String.format("no: '%s', effectiveDate: '%s', bid: '%s', ask: '%s'", this.no, this.effectiveDate,
				this.bid, this.ask);
	}

}
