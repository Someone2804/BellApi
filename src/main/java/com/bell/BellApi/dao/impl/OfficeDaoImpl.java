package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.model.Office;
import com.bell.BellApi.model.Organization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Component
public class OfficeDaoImpl implements OfficeDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public OfficeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getAll(OfficeFilter filter) {
        CriteriaQuery<Office> cq = buildCriteria(filter);
        TypedQuery<Office> tq = entityManager.createQuery(cq);

        return tq.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Long id) {
        TypedQuery<Office> officeQuery = entityManager.createNamedQuery("Office.getById", Office.class);
        officeQuery.setParameter("id", id);

        return officeQuery.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        entityManager.persist(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        Office fromDb = getById(office.getId());

        if(fromDb == null){
            throw new EntityNotFoundException("Office with id " + office.getId() + " dont exist");
        }

        BeanUtils.copyProperties(office, fromDb,
                office.getPhone() == null ? "phone" : null,
                "organization");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getReference(Long officeId) {
        return entityManager.getReference(Office.class, officeId);
    }

    private CriteriaQuery<Office> buildCriteria(OfficeFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> root = cq.from(Office.class);
        return cq.select(root).where(addPredicates(filter).toPredicate(root, cq, cb));
    }

    private Specification<Office> addPredicates(OfficeFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, criteriaBuilder) -> {
            Join<Office, Organization> join = root.join("organization", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(join, filter.getOrgId()));
            if(filter.getName() != null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }
            if(filter.getPhone() != null){
                predicates.add(criteriaBuilder.equal(root.get("phone"), filter.getPhone()));
            }
            if(filter.getActive() != null){
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getActive()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
