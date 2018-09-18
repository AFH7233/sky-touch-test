package com.afh.skytouch.managment.queue.listeners;

import com.afh.skytouch.commons.configuration.ProductTransferConfiguration;
import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FindAllListener {

    @Autowired
    @Qualifier("productList")
    private QueueInbox<String, FindAllMessage> inbox;

    @RabbitListener(queues = ProductTransferConfiguration.findAllResponse)
    public void onMessage(FindAllMessage message){
        inbox.writeMessage(message.getId(),message);
        System.out.println("Find All response: " + message.getProducts());
    }
}
