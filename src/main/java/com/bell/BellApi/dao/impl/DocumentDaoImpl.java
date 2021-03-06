package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.DocumentDao;
import com.bell.BellApi.model.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@Component
public class DocumentDaoImpl implements DocumentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DocumentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Document document) {
        entityManager.persist(document);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Document document) {
        Document fromDb = entityManager.find(Document.class, document.getUser().getId());
        if(fromDb == null){
            save(document);
        }else {
            BeanUtils.copyProperties(document, fromDb);
        }
    }
}
