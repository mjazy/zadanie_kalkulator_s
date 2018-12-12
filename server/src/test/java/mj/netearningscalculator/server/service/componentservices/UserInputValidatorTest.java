package mj.netearningscalculator.server.service.componentservices;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import mj.netearningscalculator.server.service.componentservices.UserInputValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInputValidatorTest {

	@Inject
	UserInputValidator userInputValidator;
	
	@Test
	public void validateTest() {
		assertFalse(userInputValidator.validate(null, new BigDecimal("20000")));
		assertFalse(userInputValidator.validate("", new BigDecimal("20000")));
		
		assertFalse(userInputValidator.validate("PL", null));
		assertFalse(userInputValidator.validate("PL", new BigDecimal("-20000")));
		assertFalse(userInputValidator.validate("PL", new BigDecimal("0")));
		
		assertTrue(userInputValidator.validate("PL", new BigDecimal("100")));

	}
	
}
