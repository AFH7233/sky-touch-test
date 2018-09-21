package com.afh.skytouch.commons.dto;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FindAllMessageTest {

    private List<GenericProduct> products;

    @Before
    public void setUp(){
        products = new ArrayList<>();
        for(int i=0;i<10;i++){
            products.add(new GenericProduct());
        }
    }

    @Test
    public void testInmutabilityOfProducts() {
            FindAllMessage message = new FindAllMessage();
            message.addAll(products);
            products.add(new GenericProduct());
            assertNotEquals(products,message.getProducts());
    }

    @Test
    public void testAddProducts(){
        FindAllMessage message = new FindAllMessage();
        message.addAll(products);
        assertEquals(products,message.getProducts());
    }

    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {
        FindAllMessage messageA = new FindAllMessage();
        FindAllMessage messageB = new FindAllMessage();
        Field id = messageB.getClass().getDeclaredField("id");
        id.setAccessible(true);
        id.set(messageB,messageA.getId());
        assertEquals(messageB,messageA);
    }
}