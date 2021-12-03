package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.OrganizationDao;
import com.bell.BellApi.dao.filter.OrgFilter;
import com.bell.BellApi.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
import java.util.Optional;


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
        CriteriaQuery<Organization> cq = buildCriteria(filter);
        TypedQuery<Organization> organizations = entityManager.createQuery(cq);
        return organizations.getResultList();
    }

    @Override
    public Optional<Organization> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Organization.class, id));
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
        CriteriaQuery<Organization> cq = builder.createQuery(Organization.class);
        Root<Organization> root = cq.from(Organization.class);
        return cq.select(root).where(addPredicates(filter).toPredicate(root, cq, builder));
    }

    private Specification<Organization> addPredicates(OrgFilter filter){
        List<Predicate> predicates = new ArrayList<>();
        String inn = filter.getInn();
        return (root, query, criteriaBuilder) -> {

            if((inn != null) && (!inn.isBlank())) {
                predicates.add(criteriaBuilder.equal(root.get("inn"), inn));
            }
            if(filter.isActive() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.isActive()));
            }
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
