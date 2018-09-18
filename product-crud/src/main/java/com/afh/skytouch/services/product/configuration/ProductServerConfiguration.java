package com.afh.skytouch.services.product.configuration;

import com.afh.skytouch.services.product.queue.listeners.ProductCreationListener;
import com.afh.skytouch.services.product.queue.producers.StatusSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.afh.skytouch")
@EntityScan(basePackages = {"com.afh.skytouch.commons"})
@EnableJpaRepositories(basePackages = "com.afh.skytouch.management.repositories")
@PropertySource({"classpath:common.properties"})
public class ProductServerConfiguration {
    public static void main(String[] args){
        SpringApplication.run(ProductServerConfiguration.class);
    }
}
