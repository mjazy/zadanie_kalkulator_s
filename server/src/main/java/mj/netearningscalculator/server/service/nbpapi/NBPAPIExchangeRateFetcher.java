package mj.netearningscalculator.server.service.nbpapi;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mj.netearningscalculator.server.domain.nbpapi.NBPAPIFetchExchangeRateResponse;
import mj.netearningscalculator.server.service.ExchangeRateFetcherInterface;

/**
 * Service fetching exchange rate from NBP API.
 * 
 * @author MJazy
 *
 */
@Service("nbpAPIExchangeRateFetcher")
public class NBPAPIExchangeRateFetcher implements ExchangeRateFetcherInterface {

	@Inject
	RestTemplate restTemplate;

	private HttpEntity<?> httpEntity;
	private String fetchExchangeRateUrl;

	NBPAPIExchangeRateFetcher() {
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
			// TODO : add logger functionality.
			exception.printStackTrace();
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
		//TODO: responseEntity's status code and body should be logged.
		return new BigDecimal("-1");
	}

}
