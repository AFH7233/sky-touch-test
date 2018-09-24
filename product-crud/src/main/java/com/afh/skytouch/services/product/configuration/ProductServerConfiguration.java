package com.afh.skytouch.services.product.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.afh.skytouch")
@EntityScan(basePackages = {"com.afh.skytouch.commons"})
@EnableJpaRepositories(basePackages = "com.afh.skytouch.management.repositories")
@PropertySource("classpath:common.properties")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class ProductServerConfiguration {
    public static void main(String[] args){
        SpringApplication.run(ProductServerConfiguration.class);
    }
}
