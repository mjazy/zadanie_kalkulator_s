package mj.netearningscalculator.server.rest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import mj.netearningscalculator.server.service.NetEarningsService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {

	@Inject
	NetEarningsService netEarningsService;

	@Inject
	TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	int port;

	String getMonthlyNetEarningsInvalidInputUrl, getMonthlyNetEarningsValidInputUrl;
	BigDecimal validGrossDailyEarnings;
	ResponseEntity<String> netEarningsServiceOkResponseEntity, netEarningsServiceBadRequestResponseEntity;

	@Before
	public void initializeVariables() {
		
		String validCountryCode = "PL";
		String invalidCountryCode = "PLLL";
		validGrossDailyEarnings = new BigDecimal("100");
		
		netEarningsServiceOkResponseEntity = netEarningsService.runService(validCountryCode,
				validGrossDailyEarnings);
		netEarningsServiceBadRequestResponseEntity = netEarningsService.runService(invalidCountryCode, validGrossDailyEarnings);
		
		getMonthlyNetEarningsInvalidInputUrl = String.format("http://localhost:%s/earnings/%s/%s", port, invalidCountryCode, validGrossDailyEarnings);
		getMonthlyNetEarningsValidInputUrl = String.format("http://localhost:%s/earnings/%s/%s", port, validCountryCode, validGrossDailyEarnings);

	}

	@Test
	public void getMonthlyNetEarningsInvalidInputTest() {
			assertEquals(testRestTemplate.getForObject(getMonthlyNetEarningsInvalidInputUrl, String.class), netEarningsServiceBadRequestResponseEntity.getBody());
	}
	
	@Test
	public void getMonthlyNetEarningsValidInputTest() {
			assertEquals(testRestTemplate.getForObject(getMonthlyNetEarningsValidInputUrl, String.class), netEarningsServiceOkResponseEntity.getBody());
	}


}
