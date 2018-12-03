package mj.netearningscalculator.server.rest;

import java.math.BigDecimal;

import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mj.netearningscalculator.server.service.NetEarningsService;

/**
 * Controller for application.
 * 
 * @author MJazy
 *
 */
@RestController
public class ApplicationController {

	@Inject
	NetEarningsService netEarningsService;

	/**
	 * GET endpoint for calculating monthly net earnings in PLN.
	 * @param countryCode compliant with ISO 3166-1 and supported countries list.
	 * @param grossDailyEarnings relevant for country of request's currency.
	 * @return monthly net earnings in PLN or relevant error communicate.
	 */
	@GetMapping("/earnings/{countryCode}/{grossDailyEarnings}")
	ResponseEntity<String> getMonthlyNetEarnings(@PathVariable String countryCode,
			@PathVariable BigDecimal grossDailyEarnings) {
		return netEarningsService.runService(countryCode, grossDailyEarnings);
	}
}
