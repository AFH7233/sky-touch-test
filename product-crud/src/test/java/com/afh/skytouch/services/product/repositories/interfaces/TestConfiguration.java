package com.afh.skytouch.services.product.repositories.interfaces;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(value = "com.afh.skytouch")
@EntityScan(basePackages = {"com.afh.skytouch.commons"})
@EnableJpaRepositories(basePackages = "com.afh.skytouch.management.repositories")
@PropertySource("classpath:common.properties")
public class TestConfiguration {
}
