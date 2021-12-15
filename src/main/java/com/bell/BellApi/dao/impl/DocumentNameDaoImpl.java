package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.DocumentNameDao;
import com.bell.BellApi.model.DocName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class DocumentNameDaoImpl implements DocumentNameDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DocumentNameDaoImpl(@Qualifier("entityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public DocName getByNameAndCode(String docName, String docCode) {
        TypedQuery<DocName> query = entityManager.createQuery("select dm from DocName dm where dm.docCode=:code and dm.docName=:name", DocName.class);
        query.setParameter("name", docName);
        query.setParameter("code", docCode);
        return query.getSingleResult();
    }
}
