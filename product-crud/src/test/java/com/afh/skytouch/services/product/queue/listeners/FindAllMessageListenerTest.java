package com.afh.skytouch.services.product.queue.listeners;

import com.afh.skytouch.commons.dto.FindAllMessage;
import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.services.product.queue.producers.FindAllMessageSender;
import com.afh.skytouch.services.product.queue.producers.StatusSender;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;


public class FindAllMessageListenerTest {

    @Mock
    private ProductRepositoryCustom repository;

    @Mock
    private FindAllMessageSender sender;

    FindAllMessageListener listener;

    @Before
    public void setUp(){
        listener = new FindAllMessageListener();
        MockitoAnnotations.initMocks(this);
        listener.setSender(sender);
        listener.setRepository(repository);
    }

    @Test
    public void testOnMessage(){
        FindAllMessage product = new FindAllMessage();
        listener.onMessage(product);
        assertTrue(true);
    }

}