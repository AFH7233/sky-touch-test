package com.afh.skytouch.management.repositories;

import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.management.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Component
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public void saveProduct(GenericProduct product){
        StoredProcedureQuery insert = em.createNamedStoredProcedureQuery("save");
        insert.setParameter("id_param",product.getId());
        insert.setParameter("product_name_param",product.getName());
        insert.setParameter("creation_date_param",product.getCreationDate());
        insert.setParameter("description_param",product.getDescription());
        insert.execute();
    }


}
