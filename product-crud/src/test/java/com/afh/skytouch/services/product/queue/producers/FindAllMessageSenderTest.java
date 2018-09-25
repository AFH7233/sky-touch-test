package com.afh.skytouch.services.product.queue.producers;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.assertTrue;

public class FindAllMessageSenderTest {
    @Mock
    RabbitTemplate rabbitTemplate;

    FindAllMessageSender sender;

    FindAllMessage message;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sender = new FindAllMessageSender();
        message = new FindAllMessage();
        sender.setAllTemplate(rabbitTemplate);
    }

    @Test
    public void testSendResponse(){
        sender.sendResponse(message);
        assertTrue(true);
    }

}