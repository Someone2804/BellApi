package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.CountryDao;
import com.bell.BellApi.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public CountryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Country getByCode(String code) {
        TypedQuery<Country> query = entityManager.createQuery("select c from Country c where c.citizenshipCode=:code", Country.class);
        query.setParameter("code", code);
        return query.getSingleResult();
    }
}
