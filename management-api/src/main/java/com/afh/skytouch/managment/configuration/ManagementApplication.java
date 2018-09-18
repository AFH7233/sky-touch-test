package com.afh.skytouch.managment.configuration;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@ComponentScan("com.afh.skytouch")
public class ManagementApplication {

    public static void main(String[] args){
        SpringApplication.run(ManagementApplication.class);
    }

    @Bean(name="statusResponses")
    public QueueInbox<String, ProductStatus> getStatusResponses(){
        return new QueueInbox<>();

    }

    @Bean(name="productList")
    public QueueInbox<String, FindAllMessage> getProductList(){
        return new QueueInbox<>();

    }
}
