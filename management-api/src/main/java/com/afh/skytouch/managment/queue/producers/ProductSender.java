package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.dto.GenericProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductSender {

    @Autowired
    @Qualifier("createTemplate")
    public RabbitTemplate rabbitTemplate;

    public void sendProduct(GenericProduct product){
        rabbitTemplate.convertAndSend(product);
    }
}
