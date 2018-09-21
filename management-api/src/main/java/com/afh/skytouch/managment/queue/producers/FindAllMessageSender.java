package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.configuration.QueueProperties;
import com.afh.skytouch.commons.dto.FindAllMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FindAllMessageSender {

    private RabbitTemplate allTemplate;

    @Autowired
    @Qualifier("allTemplate")
    public void setAllTemplate(RabbitTemplate allTemplate){
        Objects.requireNonNull(allTemplate,"The required template must not be null");
        this.allTemplate = allTemplate;
    }

    public String sendRequest(){
        Objects.requireNonNull(allTemplate,"The required template must not be null");
        FindAllMessage message = new FindAllMessage();
        allTemplate.convertAndSend(
                QueueProperties.EXCHANGE,
                QueueProperties.FIND_ALL_REQUEST,
                message);
        return message.getId();
    }
}
