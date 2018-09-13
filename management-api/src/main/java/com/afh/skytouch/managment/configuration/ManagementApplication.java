package com.afh.skytouch.managment.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan("com.afh.skytouch")
public class ManagementApplication {

    public static void main(String[] args){
        SpringApplication.run(ManagementApplication.class);
    }
}
