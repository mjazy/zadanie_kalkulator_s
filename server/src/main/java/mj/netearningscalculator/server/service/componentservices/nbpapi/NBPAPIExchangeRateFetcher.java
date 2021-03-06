package mj.netearningscalculator.server.service.componentservices.nbpapi;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import mj.netearningscalculator.server.domain.nbpapi.NBPAPIFetchExchangeRateResponse;
import mj.netearningscalculator.server.service.componentservices.ExchangeRateFetcherInterface;

/**
 * Service fetching exchange rate from NBP API.
 * 
 * @author MJazy
 *
 */
@Named("nbpAPIExchangeRateFetcher")
public class NBPAPIExchangeRateFetcher implements ExchangeRateFetcherInterface {

	@Inject
	private RestTemplate restTemplate;

	@Inject
	private Logger logger;

	private HttpEntity<?> httpEntity;
	private String fetchExchangeRateUrl;

	public NBPAPIExchangeRateFetcher() {
		httpEntity = prepareHttpEntity();
		fetchExchangeRateUrl = "http://api.nbp.pl/api/exchangerates/rates/C/";
	}

	// If JSON format is not specified in header API returns XML.
	private HttpEntity<?> prepareHttpEntity() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(httpHeaders);
	}

	public BigDecimal fetchExchangeRate(String currency) {

		ResponseEntity<NBPAPIFetchExchangeRateResponse> responseEntity;
		try {
			responseEntity = restTemplate.exchange((String.format("%s%s%s", fetchExchangeRateUrl, currency, "/")),
					HttpMethod.GET, httpEntity, NBPAPIFetchExchangeRateResponse.class);
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			return new BigDecimal(-1);
		}

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			BigDecimal exchangeRate = responseEntity.getBody().getRates()[0].getBid();
			// There may have been external service outage.
			if (exchangeRate != null) {
				return exchangeRate;
			}
		}

		// In case responseEntity's status code was not 'OK' or exchangeRate was null.
		logger.error(String.format("Error with responseEntity, HTTP status: '%s', body: '%s'",
				responseEntity.getStatusCode(), responseEntity.getBody()));
		return new BigDecimal("-1");
	}

}
