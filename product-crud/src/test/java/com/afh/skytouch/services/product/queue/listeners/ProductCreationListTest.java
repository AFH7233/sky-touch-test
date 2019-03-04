package com.afh.skytouch.services.product.queue.listeners;

import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.services.product.queue.producers.StatusSender;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;

public class ProductCreationListTest {

    @Mock
    private ProductRepositoryCustom repository;

    @Mock
    private StatusSender sender;

    ProductCreationListener listener;

    @Before
    public void setUp(){
        listener = new ProductCreationListener();
        MockitoAnnotations.initMocks(this);
        listener.setSender(sender);
        listener.setRepository(repository);
    }

    @Test
    public void testOnMessage(){
        GenericProduct product = new GenericProduct();
        listener.onMessage(product);
        assertTrue(true);
    }
}
