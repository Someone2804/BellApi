package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.OrganizationDao;
import com.bell.BellApi.dao.impl.filter.OrgFilter;
import com.bell.BellApi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public OrganizationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Organization> getAll(OrgFilter filter) {
        CriteriaQuery<Organization> org = buildCriteria(filter);
        TypedQuery<Organization> organizations = entityManager.createQuery(org);
        return organizations.getResultList();
    }

    @Override
    public Organization getById(Long id) {
        return entityManager.find(Organization.class, id);
    }

    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }

    @Override
    public void update(Organization organization) {
        entityManager.merge(organization);
    }

    private CriteriaQuery<Organization> buildCriteria(OrgFilter filter){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        List<Predicate> predicates = new ArrayList<>();

        addPredicates(predicates, filter, builder);

        return builder.createQuery(Organization.class)
                .where(predicates.toArray(new Predicate[predicates.size()]));
    }

    private void addPredicates(List<Predicate> predicates, OrgFilter filter, CriteriaBuilder builder){
        CriteriaQuery<Organization> org = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = org.from(Organization.class);

        if(!filter.getInn().isBlank()){
            predicates.add(builder.equal(organizationRoot.get("inn"), filter.getInn()));
        }
        predicates.add(builder.equal(organizationRoot.get("name"), filter.getName()));
        predicates.add(builder.equal(organizationRoot.get("isActive"), filter.isActive()));
    }
}
