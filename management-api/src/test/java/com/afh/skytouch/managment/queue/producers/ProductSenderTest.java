package com.afh.skytouch.managment.queue.producers;

import com.afh.skytouch.commons.dto.GenericProduct;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.*;

public class ProductSenderTest {
    @Mock
    RabbitTemplate rabbitTemplate;

    ProductSender sender;

    GenericProduct product;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sender = new ProductSender();
        product = new GenericProduct();
    }

    @Test
    public void testSendRequest(){
        sender.setRabbitTemplate(rabbitTemplate);
        String id = sender.sendProduct(product);
        assertEquals(id,product.getId());
    }


    @Test(expected = NullPointerException.class)
    public void testSetNullTemplate(){
        sender.setRabbitTemplate(null);
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void testSendWithNullTemplate(){
        sender.sendProduct(product);
        fail();
    }

}