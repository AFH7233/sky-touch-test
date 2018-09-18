package com.afh.skytouch.services.product.queue.listeners;

import com.afh.skytouch.commons.configuration.ProductTransferConfiguration;
import com.afh.skytouch.commons.constans.StatusCode;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.services.product.queue.producers.StatusSender;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Observable;

@Component
public class ProductCreationListener{

    @Autowired
    ProductRepositoryCustom repository;

    @Autowired
    StatusSender sender;

    @RabbitListener(queues = ProductTransferConfiguration.create)
    public void onMessage(GenericProduct product){
        ProductStatus status = new ProductStatus();
        status.setProductId(product.getId());
        try
        {
            repository.saveProduct(product);
            status.setCode(StatusCode.SUCCESS);
            status.setMessage("The product was added successfully");
        }
        catch(Exception e)
        {
            status.setCode(StatusCode.CREATION_ERROR);
            status.setMessage(e.getMessage());
        }
        sender.sendStatus(status);
        System.out.println("Resultado: "+product.getId());
    }
}
