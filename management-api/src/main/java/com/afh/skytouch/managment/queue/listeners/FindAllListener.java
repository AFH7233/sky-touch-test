package com.afh.skytouch.managment.queue.listeners;

import com.afh.skytouch.commons.configuration.QueueProperties;
import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FindAllListener {

    private QueueInbox<String, FindAllMessage> inbox;

    @Autowired
    @Qualifier("productList")
    public void setInbox(QueueInbox<String, FindAllMessage> inbox){
        this.inbox = inbox;
    }

    @RabbitListener(queues = QueueProperties.FIND_ALL_RESPONSE)
    public void onMessage(FindAllMessage message){
        inbox.writeMessage(message.getId(),message);
        System.out.println("Find All response: " + message.getProducts());
    }
}
