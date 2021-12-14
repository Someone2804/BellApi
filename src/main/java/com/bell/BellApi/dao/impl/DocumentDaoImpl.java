package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.DocumentDao;
import com.bell.BellApi.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class DocumentDaoImpl implements DocumentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DocumentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Document document) {
        entityManager.persist(document);
    }

    @Override
    public void update(Document document) {

    }

    @Override
    public Document getById(Long id) {
        return null;
    }

    @Override
    public List<Document> getAll() {
        return null;
    }
}
