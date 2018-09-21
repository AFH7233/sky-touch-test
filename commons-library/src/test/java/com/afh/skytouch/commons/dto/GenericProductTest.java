package com.afh.skytouch.commons.dto;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GenericProductTest {

    @Test
    public void testRandomIdGeneration(){
        GenericProduct statusA = new GenericProduct();
        GenericProduct statusB = new GenericProduct();
        assertNotEquals(statusA.getId(),statusB.getId());
    }

    @Test
    public void testEquals() throws NoSuchFieldException, IllegalAccessException {
        GenericProduct statusA = new GenericProduct();
        GenericProduct statusB = new GenericProduct();
        Field id = statusB.getClass().getDeclaredField("id");
        id.setAccessible(true);
        id.set(statusB,statusA.getId());
        assertEquals(statusB,statusA);
    }

}