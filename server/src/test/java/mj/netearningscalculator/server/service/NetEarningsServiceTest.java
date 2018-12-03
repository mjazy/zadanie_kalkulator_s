package mj.netearningscalculator.server.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import mj.netearningscalculator.server.domain.CountryFinances;
import mj.netearningscalculator.server.domain.SupportedCountries;
import mj.netearningscalculator.server.service.nbpapi.NBPAPIExchangeRateFetcher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetEarningsServiceTest {

	@Inject
	NetEarningsService netEarningsService;

	@Inject
	Calculator calculator;

	@Inject
	SupportedCountries supportedCountries;

	@Inject
	UserInputValidator userInputValidator;

	String invalidCountryCode, validCountryCode, validCountryCode1;
	BigDecimal invalidGrossDailyEarnings, validGrossDailyEarnings;
	ResponseEntity<String> badRequestResponseEntity, successfulRequestResponseEntity,
			externalServiceOutageResponseEntity;
	NBPAPIExchangeRateFetcher mockedNBPAPIExchangeRateFetcher;
	NetEarningsService netEarningsServiceWithMockedFetcher;

	@Before
	public void setUpEnvironment() {
		initializeUserInputVariables();
		initializeResponseEntities();
		mockNBPAPIExchangeRateFetcher();
		initializeNetMonthlyEarningsInPLNCalculatorServiceWithMockedFetcher();

	}

	@Test
	public void badRequestTest() {
		assertEquals(netEarningsService.runService(invalidCountryCode, validGrossDailyEarnings),
				badRequestResponseEntity);
		assertEquals(netEarningsService.runService(validCountryCode, invalidGrossDailyEarnings),
				badRequestResponseEntity);
	}

	@Test
	public void successfulRequestTest() {
		assertEquals(netEarningsService.runService(validCountryCode, validGrossDailyEarnings),
				successfulRequestResponseEntity);
	}

	@Test
	public void externalServiceOutageRequestTest() {
		assertEquals(netEarningsServiceWithMockedFetcher.runService(validCountryCode1, validGrossDailyEarnings),
				externalServiceOutageResponseEntity);
	}

	private void initializeUserInputVariables() {
		invalidCountryCode = "ZZZ";
		validCountryCode = "PL";
		validCountryCode1 = "UK";
		invalidGrossDailyEarnings = null;
		validGrossDailyEarnings = new BigDecimal("100");
	}

	private void initializeResponseEntities() {
		badRequestResponseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input parameters.");

		CountryFinances validCountryFinances = supportedCountries.getCountryByCode(validCountryCode).getFinances();
		BigDecimal validNetMonthlyEarningsInPLN = calculator.calculateNetMonthlyEarningsInPLN(validGrossDailyEarnings,
				new BigDecimal("1"), validCountryFinances);
		successfulRequestResponseEntity = ResponseEntity.status(HttpStatus.OK)
				.body(validNetMonthlyEarningsInPLN.toString());

		externalServiceOutageResponseEntity = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("External service error.");
	}

	private void mockNBPAPIExchangeRateFetcher() {
		mockedNBPAPIExchangeRateFetcher = mock(NBPAPIExchangeRateFetcher.class);
		when(mockedNBPAPIExchangeRateFetcher.fetchExchangeRate(supportedCountries.getCountryCurrency("UK")))
				.thenReturn(new BigDecimal("-1"));
	}

	private void initializeNetMonthlyEarningsInPLNCalculatorServiceWithMockedFetcher() {
		netEarningsServiceWithMockedFetcher = new NetEarningsService(userInputValidator,
				mockedNBPAPIExchangeRateFetcher, calculator, supportedCountries);
	}

}
