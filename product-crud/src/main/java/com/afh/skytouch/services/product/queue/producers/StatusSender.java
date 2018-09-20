package com.afh.skytouch.services.product.queue.producers;

import com.afh.skytouch.commons.dto.ProductStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StatusSender{

    private RabbitTemplate statusTemplate;

    @Autowired
    @Qualifier("statusTemplate")
    public void setStatusTemplate(RabbitTemplate statusTemplate){
        this.statusTemplate = statusTemplate;
    }

    public void sendStatus(ProductStatus status){
        statusTemplate.convertAndSend(status);
    }
}
