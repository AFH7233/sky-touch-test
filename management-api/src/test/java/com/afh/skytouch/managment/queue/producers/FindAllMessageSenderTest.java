package com.afh.skytouch.managment.queue.producers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class FindAllMessageSenderTest {

    @Mock
    RabbitTemplate rabbitTemplate;

    FindAllMessageSender sender;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        sender = new FindAllMessageSender();
    }

    @Test
    public void testSendRequest(){
        sender.setAllTemplate(rabbitTemplate);
        String id = sender.sendRequest();
        assertFalse(id.isEmpty());
    }

    @Test
    public void testSendRequestRandomId(){
        sender.setAllTemplate(rabbitTemplate);
        String idA = sender.sendRequest();
        String idB = sender.sendRequest();
        assertNotEquals(idA,idB);
    }


    @Test(expected = NullPointerException.class)
    public void testSetNullTemplate(){
        sender.setAllTemplate(null);
        fail();
    }

    @Test(expected = NullPointerException.class)
    public void testSendWithNullTemplate(){
        sender.sendRequest();
        fail();
    }
}