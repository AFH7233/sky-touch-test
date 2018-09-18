package com.afh.skytouch.services.product.queue.producers;

import com.afh.skytouch.commons.configuration.ProductTransferConfiguration;
import com.afh.skytouch.commons.dto.FindAllMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FindAllMessageSender {

    @Autowired
    @Qualifier("allTemplate")
    RabbitTemplate allTemplate;

    public void sendResponse(FindAllMessage message){
        allTemplate.convertAndSend(
                ProductTransferConfiguration.exchange,
                ProductTransferConfiguration.findAllResponse,
                message
        );
    }
}
