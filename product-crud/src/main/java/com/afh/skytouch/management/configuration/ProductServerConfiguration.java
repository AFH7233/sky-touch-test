package com.afh.skytouch.management.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.afh.skytouch")
public class ProductServerConfiguration {
    public static void main(String[] args){
        SpringApplication.run(ProductServerConfiguration.class);
    }
}
