package com.afh.skytouch.services.product.repositories;

import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.services.product.configuration.InsertProcedureConfiguration;
import com.afh.skytouch.services.product.configuration.ProceduresNameConfiguration;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }

    @Autowired
    private InsertProcedureConfiguration insertProcedureConfiguration;

    @Autowired
    private ProceduresNameConfiguration proceduresNameConfiguration;

    public void saveProduct(GenericProduct product){
        StoredProcedureQuery insert = entityManager.createNamedStoredProcedureQuery(proceduresNameConfiguration.getSave());
        insert.setParameter(insertProcedureConfiguration.getId(),product.getId());
        insert.setParameter(insertProcedureConfiguration.getProductName(),product.getName());
        insert.setParameter(insertProcedureConfiguration.getCreationDate(),product.getCreationDate());
        insert.setParameter(insertProcedureConfiguration.getDescription(),product.getDescription());
        insert.execute();
    }

    @Override
    public List<GenericProduct> findAll() {
        StoredProcedureQuery findAll = entityManager.createNamedStoredProcedureQuery(proceduresNameConfiguration.getFindAll());
        return findAll.getResultList();
    }


}
