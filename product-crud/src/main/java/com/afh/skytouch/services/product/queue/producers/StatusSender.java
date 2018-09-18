package com.afh.skytouch.services.product.queue.producers;

import com.afh.skytouch.commons.dto.ProductStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
public class StatusSender{

    @Autowired
    @Qualifier("statusTemplate")
    RabbitTemplate statusTemplate;

    public void sendStatus(ProductStatus status){
        statusTemplate.convertAndSend(status);
    }
}
