package com.afh.skytouch.management.queue.listeners;

import com.afh.skytouch.commons.configuration.ProductTransferConfiguration;
import com.afh.skytouch.commons.dto.GenericProduct;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCreationListener {

    @RabbitListener(queues = ProductTransferConfiguration.create)
    public void onMessage(GenericProduct product){
        System.out.println("Resultado: "+product.getId());
    }
}
