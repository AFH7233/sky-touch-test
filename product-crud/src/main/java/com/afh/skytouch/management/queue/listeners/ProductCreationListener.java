package com.afh.skytouch.management.queue.listeners;

import com.afh.skytouch.commons.configuration.ProductTransferConfiguration;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.management.repositories.interfaces.ProductRepository;
import com.afh.skytouch.management.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCreationListener {

    @Autowired
    ProductRepositoryCustom repository;

    @Autowired
    ProductRepository repo;

    @RabbitListener(queues = ProductTransferConfiguration.create)
    public void onMessage(GenericProduct product){
        repository.saveProduct(product);
        System.out.println("Resultado: "+product.getId());
    }
}
