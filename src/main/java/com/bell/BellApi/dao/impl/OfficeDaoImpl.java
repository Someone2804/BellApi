package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.model.Office;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final String GET_ALL = "select o from Office o";

    @PersistenceContext
    private final EntityManager entityManager;

    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Office> getAll() {
        TypedQuery<Office> offices = entityManager.createQuery(GET_ALL, Office.class);
        return offices.getResultList();
    }

    @Override
    public Optional<Office> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Office.class, id));
    }

    @Override
    public void save(Office office) {
        entityManager.persist(office);
    }

    @Override
    public void update(Office office) {
        entityManager.merge(office);
    }
}
