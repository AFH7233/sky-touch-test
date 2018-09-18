package com.afh.skytouch.services.product.repositories.interfaces;

import com.afh.skytouch.commons.dto.GenericProduct;

import java.util.List;

public interface ProductRepositoryCustom {
    void saveProduct(GenericProduct product);
    List<GenericProduct> findAll();
}
