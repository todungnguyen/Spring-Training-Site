package com.todungnguyen;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@ConditionalOnClass({ SpringSecurityDialect.class })
public class ThymeleafAutoConfig {

	@Bean
	@ConditionalOnMissingBean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}
}
