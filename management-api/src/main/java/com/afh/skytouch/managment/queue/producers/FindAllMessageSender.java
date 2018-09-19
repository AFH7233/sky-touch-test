package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.configuration.QueueProperties;
import com.afh.skytouch.commons.dto.FindAllMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FindAllMessageSender {

    @Autowired
    @Qualifier("allTemplate")
    public RabbitTemplate allTemplate;

    public String sendRequest(){
        FindAllMessage message = new FindAllMessage();
        allTemplate.convertAndSend(
                QueueProperties.EXCHANGE,
                QueueProperties.FIND_ALL_REQUEST,
                message);
        return message.getId();
    }
}
