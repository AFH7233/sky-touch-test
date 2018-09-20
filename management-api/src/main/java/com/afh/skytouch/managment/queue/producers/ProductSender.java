package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.dto.GenericProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductSender {

    private RabbitTemplate productTemplate;

    @Autowired
    @Qualifier("createTemplate")
    public void setProductTemplate(RabbitTemplate rabbitTemplate){
        this.productTemplate = rabbitTemplate;
    }


    public void sendProduct(GenericProduct product){
        productTemplate.convertAndSend(product);
    }
}
