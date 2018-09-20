package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.dto.GenericProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("createTemplate")
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public String sendProduct(GenericProduct product){
        rabbitTemplate.convertAndSend(product);
        return product.getId();
    }
}
