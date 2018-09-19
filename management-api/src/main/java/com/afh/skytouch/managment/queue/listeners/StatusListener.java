package com.afh.skytouch.managment.queue.listeners;

import com.afh.skytouch.commons.configuration.QueueProperties;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StatusListener {


    private QueueInbox<String, ProductStatus> inbox;

    @Autowired
    @Qualifier("statusResponses")
    public void setInbox(QueueInbox<String, ProductStatus> inbox){
        this.inbox = inbox;
    }

    @RabbitListener(queues = QueueProperties.STATUS)
    public void onMessage(ProductStatus status){
        inbox.writeMessage(status.getProductId(),status);
    }

}
