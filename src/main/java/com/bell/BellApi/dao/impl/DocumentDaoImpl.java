package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.DocumentDao;
import com.bell.BellApi.model.Document;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class DocumentDaoImpl implements DocumentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public DocumentDaoImpl(@Qualifier("entityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Document document) {
        entityManager.persist(document);
    }

    @Override
    public void update(Document document) {
        Document fromDb = entityManager.find(Document.class, document.getUser().getId());
        BeanUtils.copyProperties(document, fromDb);
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
