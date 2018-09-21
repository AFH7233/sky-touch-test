package com.afh.skytouch.services.product.queue.listeners;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:common.properties")
@EnableJpaRepositories(basePackages =  "com.afh.skytouch.management.repositories")
public class TestConfiguration {

}
