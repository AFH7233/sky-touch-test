package com.afh.skytouch.services.product.queue.listeners;

import com.afh.skytouch.commons.configuration.QueueProperties;
import com.afh.skytouch.commons.constans.StatusCode;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.commons.dto.ProductStatus;
import com.afh.skytouch.services.product.queue.producers.StatusSender;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCreationListener{

    private ProductRepositoryCustom repository;

    private StatusSender sender;

    @Autowired
    public void setRepository(ProductRepositoryCustom repository){
        this.repository = repository;
    }

    @Autowired
    public void setSender(StatusSender sender){
        this.sender = sender;
    }

    @RabbitListener(queues = QueueProperties.CREATE)
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
