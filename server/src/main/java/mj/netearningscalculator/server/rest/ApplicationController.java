package mj.netearningscalculator.server.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mj.netearningscalculator.server.domain.Country;
import mj.netearningscalculator.server.domain.SupportedCountries;
import mj.netearningscalculator.server.service.NetEarningsService;

/**
 * Controller for application. Values of origins are of Client module (where
 * port 4200 is of application run, and 9876 of Karma's tests).
 * 
 * @author MJazy
 *
 */
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:9876" })
@RestController
public class ApplicationController {

	@Inject
	NetEarningsService netEarningsService;

	@Inject
	SupportedCountries supportedCountries;

	/**
	 * GET endpoint for calculating monthly net earnings in PLN.
	 * 
	 * @param countryCode        compliant with ISO 3166-1 and supported countries
	 *                           list.
	 * @param grossDailyEarnings relevant for country of request's currency.
	 * @return monthly net earnings in PLN or relevant error communicate.
	 */
	@GetMapping("/earnings/{countryCode}/{grossDailyEarnings}")
	ResponseEntity<String> getMonthlyNetEarnings(@PathVariable String countryCode,
			@PathVariable BigDecimal grossDailyEarnings) {
		return netEarningsService.runService(countryCode, grossDailyEarnings);
	}

	@GetMapping("/countries")
	ResponseEntity<List<Country>> getSupportedCountries() {
		return ResponseEntity.status(HttpStatus.OK).body(supportedCountries.getSupportedCountriesList());
	}
}
