package com.afh.skytouch.management.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@ComponentScan("com.afh.skytouch")
@EntityScan(basePackages = {"com.afh.skytouch.commons"})
@EnableJpaRepositories(basePackages = "com.afh.skytouch.management.repositories")
public class ProductServerConfiguration {
    public static void main(String[] args){
        SpringApplication.run(ProductServerConfiguration.class);
    }
}
