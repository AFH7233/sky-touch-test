package com.afh.skytouch.services.product.repositories;

import com.afh.skytouch.commons.dto.GenericProduct;
import com.afh.skytouch.services.product.repositories.interfaces.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Component
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Value("${db.stored.procedures.insert.parameters.id}")
    private String idKey;

    @Value("${db.stored.procedures.insert.parameters.productName}")
    private String nameKey;

    @Value("${db.stored.procedures.insert.parameters.creationDate}")
    private String dateKey;

    @Value("${db.stored.procedures.insert.parameters.description}")
    private String descriptionKey;

    @Value("${db.insert.id}")
    private String saveProcedure;

    @Value("${db.findAll.id}")
    private String findAllProcedure;

    public void saveProduct(GenericProduct product){
        StoredProcedureQuery insert = em.createNamedStoredProcedureQuery(saveProcedure);
        insert.setParameter(idKey,product.getId());
        insert.setParameter(nameKey,product.getName());
        insert.setParameter(dateKey,product.getCreationDate());
        insert.setParameter(descriptionKey,product.getDescription());
        insert.execute();
    }

    @Override
    public List<GenericProduct> findAll() {
        StoredProcedureQuery findAll = em.createNamedStoredProcedureQuery(findAllProcedure);
        return findAll.getResultList();
    }


}
