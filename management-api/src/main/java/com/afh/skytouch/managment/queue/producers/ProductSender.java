package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.dto.GenericProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("createTemplate")
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate){
        Objects.requireNonNull(rabbitTemplate,"The required template must not be null");
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendProduct(GenericProduct product){
        Objects.requireNonNull(rabbitTemplate,"The required template must not be null");
        rabbitTemplate.convertAndSend(product);
        return product.getId();
    }
}
