package com.afh.skytouch.managment.configuration;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.afh.skytouch")
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
