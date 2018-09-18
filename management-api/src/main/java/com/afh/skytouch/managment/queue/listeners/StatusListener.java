package com.afh.skytouch.managment.queue.listeners;

import com.afh.skytouch.commons.configuration.ProductTransferConfiguration;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.managment.inboxes.QueueInbox;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class StatusListener {

    @Autowired
    @Qualifier("statusResponses")
    private QueueInbox<String, ProductStatus> inbox;

    @RabbitListener(queues = ProductTransferConfiguration.status)
    public void onMessage(ProductStatus status){
        inbox.writeMessage(status.getProductId(),status);
    }

}
