package com.afh.skytouch.commons.dto;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProductStatusTest {

    @Test
    public void testRandomIdGeneration(){
        ProductStatus statusA = new ProductStatus();
        ProductStatus statusB = new ProductStatus();
        assertNotEquals(statusA.getId(),statusB.getId());
    }

    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {
        ProductStatus statusA = new ProductStatus();
        ProductStatus statusB = new ProductStatus();
        Field id = statusB.getClass().getDeclaredField("id");
        id.setAccessible(true);
        id.set(statusB,statusA.getId());
        assertEquals(statusB,statusA);
    }

}