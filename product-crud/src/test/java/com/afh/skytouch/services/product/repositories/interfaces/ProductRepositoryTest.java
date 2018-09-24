package com.afh.skytouch.services.product.repositories.interfaces;

import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.services.product.repositories.ProductRepositoryCustomImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestConfiguration.class})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepositoryCustomImpl repository;

    @Test
    public void testSaveProduct(){
        GenericProduct product = new GenericProduct();
        product.setName("test2");
        product.setDescription("it's a test");
        repository.saveProduct(product);
        List<GenericProduct> products = repository.findAll();
        assertTrue(products.contains(product));
    }

    @Test
    public void testGetAllProducts(){
        List<GenericProduct> products = new ArrayList<>();
        for(int i=0;i<10;i++){
            GenericProduct product = new GenericProduct();
            product.setName("test" + product.getId());
            product.setDescription("none");
            products.add(product);
            repository.saveProduct(product);
        }
        List<GenericProduct> dataBaseProducts = repository.findAll();
        assertFalse(dataBaseProducts.isEmpty());
    }

}
