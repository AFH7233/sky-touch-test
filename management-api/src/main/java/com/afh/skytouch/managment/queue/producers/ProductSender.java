package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.constans.StatusCode;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.configuration.ProductTransferConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductSender {

    @Autowired
    @Qualifier("createTemplate")
    public RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("createTemplate")
    public RabbitTemplate statusTemplate;

    public void sendProduct(GenericProduct product){
        Object result = rabbitTemplate.convertSendAndReceive(product,new CorrelationData(UUID.randomUUID().toString()));
        System.out.println("Resultó:" + result);
    }
}
