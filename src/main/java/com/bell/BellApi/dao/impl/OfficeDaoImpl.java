package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.OfficeDao;
import com.bell.BellApi.dto.filter.OfficeFilter;
import com.bell.BellApi.model.Office;
import com.bell.BellApi.model.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class OfficeDaoImpl implements OfficeDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OfficeDaoImpl(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("Hibernate factory not found");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public List<Office> getAll(OfficeFilter filter) {
        List<Office> offices = new ArrayList<>();
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            CriteriaQuery<Office> cq = buildCriteria(filter);
            TypedQuery<Office> tq = getSession().createQuery(cq);
            offices = tq.getResultList();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw (e);
        }
        return offices;
    }

    @Override
    public Office getById(Long id) {
        Office office = null;
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            TypedQuery<Office> officeQuery = session.createNamedQuery("Office.getById", Office.class);
            officeQuery.setParameter("id", id);
            office = officeQuery.getSingleResult();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw (e);
        }
        return office;
    }

    @Override
    public void save(Office office, Long orgId) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            Organization organization = session.getReference(Organization.class, orgId);
            office.setOrganization(organization);
            session.save(office);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw (e);
        }
    }

    @Override
    public void update(Office office) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            Office fromDb = session.find(Office.class, office.getId());

            if(fromDb == null){
                throw new EntityNotFoundException("Office with id " + office.getId() + " dont exist");
            }

            BeanUtils.copyProperties(office, fromDb,
                    office.getPhone() == null ? "phone" : null,
                    office.isActive() == null ? "isActive" : null,
                    "organization");
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            throw (e);
        }

    }

    private CriteriaQuery<Office> buildCriteria(OfficeFilter filter) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
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

    private Session getSession(){
        return sessionFactory.openSession();
    }
}
