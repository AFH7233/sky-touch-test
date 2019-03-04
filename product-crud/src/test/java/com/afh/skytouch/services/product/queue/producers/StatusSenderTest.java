package com.afh.skytouch.services.product.queue.producers;

import com.afh.skytouch.commons.dto.ProductStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.assertTrue;

public class StatusSenderTest {
    @Mock
    RabbitTemplate rabbitTemplate;

    StatusSender sender;

    ProductStatus status;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sender = new StatusSender();
        status = new ProductStatus();
        sender.setStatusTemplate(rabbitTemplate);
    }

    @Test
    public void testSendResponse(){
        sender.sendStatus(status);
        assertTrue(true);
    }
}
