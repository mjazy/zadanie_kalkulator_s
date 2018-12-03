package mj.netearningscalculator.server.rest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestRestConfiguration {

	@Bean
	TestRestTemplate testRestTemplate() {
		return new TestRestTemplate();
	}
}
