package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.DocumentNameDao;
import com.bell.BellApi.model.DocName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Component
public class DocumentNameDaoImpl implements DocumentNameDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DocumentNameDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DocName getByCode(String docCode) {
        TypedQuery<DocName> query = entityManager.createQuery("select dm from DocName dm where dm.docCode=:code", DocName.class);
        query.setParameter("code", docCode);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocName getByName(String docName) {
        TypedQuery<DocName> query = entityManager.createQuery("select dm from DocName dm where dm.docName=:name", DocName.class);
        query.setParameter("name", docName);
        return query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocName> getAll() {
        return entityManager.createQuery("select dm from DocName dm", DocName.class).getResultList();
    }
}
