package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.PositionDao;
import com.bell.BellApi.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class PositionDaoImpl implements PositionDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public PositionDaoImpl(@Qualifier("entityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Position getByName(String name) {
        TypedQuery<Position> query = entityManager.createQuery("select p from Position p where p.positionName=:name", Position.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
