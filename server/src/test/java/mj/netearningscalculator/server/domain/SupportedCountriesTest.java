package mj.netearningscalculator.server.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SupportedCountriesTest {

	@Inject
	SupportedCountries supportedCountries;

	@Test
	public void containsCountryTest() {
		assertTrue(supportedCountries.containsCountry("DE"));
		assertTrue(supportedCountries.containsCountry("PL"));
		assertTrue(supportedCountries.containsCountry("UK"));

		assertTrue(supportedCountries.containsCountry("de"));
		assertTrue(supportedCountries.containsCountry("Pl"));
		assertTrue(supportedCountries.containsCountry("uK"));	

		assertFalse(supportedCountries.containsCountry("FR"));
		assertFalse(supportedCountries.containsCountry(null));
	}

	@Test
	public void toStringTest() {
		String relevantToStringValue = String.format("supportedCountries: '%s'",
				supportedCountries.getSupportedCountriesList().toString());
		assertEquals(supportedCountries.toString(), relevantToStringValue);
	}

}
