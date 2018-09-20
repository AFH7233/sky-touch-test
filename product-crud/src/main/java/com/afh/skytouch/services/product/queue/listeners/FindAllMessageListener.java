package com.afh.skytouch.services.product.queue.listeners;

import com.afh.skytouch.commons.configuration.QueueProperties;
import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.services.product.queue.producers.FindAllMessageSender;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllMessageListener {

    private ProductRepositoryCustom repository;

    private FindAllMessageSender sender;

    @Autowired
    public void setRepository(ProductRepositoryCustom repository){
        this.repository = repository;
    }

    @Autowired
    public void setSender(FindAllMessageSender sender){
        this.sender = sender;
    }

    @RabbitListener(queues = QueueProperties.FIND_ALL_REQUEST)
    public void onMessage(FindAllMessage message){
        System.out.println("Message id: " + message.getId());
        List<GenericProduct> products = repository.findAll();
        message.addAll(products);
        sender.sendResponse(message);
    }
}
