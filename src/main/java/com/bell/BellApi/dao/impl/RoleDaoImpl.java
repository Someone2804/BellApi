package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.RoleDao;
import com.bell.BellApi.model.role.ERole;
import com.bell.BellApi.model.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role getByName(ERole name) {
        TypedQuery<Role> roleQuery = entityManager.createQuery("select r from Role r where r.name=:name", Role.class);
        roleQuery.setParameter("name", name);
        return roleQuery.getSingleResult();
    }
}
