package mj.netearningscalculator.server.rest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import mj.netearningscalculator.server.domain.Country;
import mj.netearningscalculator.server.domain.SupportedCountries;
import mj.netearningscalculator.server.service.NetEarningsService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {

	@Inject
	NetEarningsService netEarningsService;

	@Inject
	TestRestTemplate testRestTemplate;

	@Inject
	SupportedCountries supportedCountries;

	@LocalServerPort
	int port;

	String getMonthlyNetEarningsInvalidInputUrl, getMonthlyNetEarningsValidInputUrl, getSupportedCountriesUrl;
	BigDecimal validGrossDailyEarnings;
	ResponseEntity<String> netEarningsServiceOkResponseEntity, netEarningsServiceBadRequestResponseEntity;
	ResponseEntity<ArrayList<Country>> getSupportedCountriesResponseEntity;

	@Before
	public void initializeVariables() {

		String validCountryCode = "PL";
		String invalidCountryCode = "PLLL";
		validGrossDailyEarnings = new BigDecimal("100");

		netEarningsServiceOkResponseEntity = netEarningsService.runService(validCountryCode, validGrossDailyEarnings);
		netEarningsServiceBadRequestResponseEntity = netEarningsService.runService(invalidCountryCode,
				validGrossDailyEarnings);

		getMonthlyNetEarningsInvalidInputUrl = String.format("http://localhost:%s/earnings/%s/%s", port,
				invalidCountryCode, validGrossDailyEarnings);
		getMonthlyNetEarningsValidInputUrl = String.format("http://localhost:%s/earnings/%s/%s", port, validCountryCode,
				validGrossDailyEarnings);
		getSupportedCountriesUrl = String.format("http://localhost:%s/countries", port);
		getSupportedCountriesResponseEntity = testRestTemplate.exchange(getSupportedCountriesUrl, HttpMethod.GET,
				null, new ParameterizedTypeReference<ArrayList<Country>>() {
				});

	}

	@Test
	public void getMonthlyNetEarningsInvalidInputTest() {
		assertEquals(testRestTemplate.getForObject(getMonthlyNetEarningsInvalidInputUrl, String.class),
				netEarningsServiceBadRequestResponseEntity.getBody());
	}

	@Test
	public void getMonthlyNetEarningsValidInputTest() {
		assertEquals(testRestTemplate.getForObject(getMonthlyNetEarningsValidInputUrl, String.class),
				netEarningsServiceOkResponseEntity.getBody());
	}

	@Test
	public void getSupportedCountriesTest() {
		assertEquals(getSupportedCountriesResponseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(getSupportedCountriesResponseEntity.getBody().toString(),
				supportedCountries.getSupportedCountriesList().toString());
	}

}
