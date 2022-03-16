package com.bell.BellApi.dao.impl;

import com.bell.BellApi.dao.UserDao;
import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.model.Position;
import com.bell.BellApi.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * {@inheritDoc}
 */
@Component
public class UserDaoImpl implements UserDao {

    private final String GET_BY_ID = "select u.id, u.first_name, u.second_name, u.middle_name, u.phone, u.is_identified, p.position_name, dm.doc_name, d.doc_number, d.doc_date, c.citizenship_name, c.citizenship_code from Usr u " +
            "left join Usr_Position up on u.id = up.usr_id left join Position p on up.position_id = p.id " +
            "left join Document d on u.id = d.id " +
            "left join Document_name dm on d.doc_name_id = dm.id " +
            "left join Citizenship c on u.citizenship_id = c.id " +
            "where u.id = ?";



    private final DataSource dataSource;
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(DataSource dataSource, EntityManager entityManager) {
        this.dataSource = dataSource;
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll(UserFilter filter) {
        CriteriaQuery<User> cq = buildCriteria(filter);
        TypedQuery<User> tq = entityManager.createQuery(cq);
        List<User> resultList = tq.getResultList();
        return resultList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDtoId getById(Long id) {
        UserDtoId user = new UserDtoId();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID)){
            connection.setAutoCommit(false);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setSecondName(resultSet.getString("second_name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setPhone(resultSet.getString("phone"));
                user.addPosition(resultSet.getString("position_name"));
                user.setDocName(resultSet.getString("doc_name"));
                user.setDocNumber(resultSet.getString("doc_number"));
                user.setDocDate(resultSet.getDate("doc_date"));
                user.setCitizenshipName(resultSet.getString("citizenship_name"));
                user.setCitizenshipCode(resultSet.getString("citizenship_code"));
                user.setIdentified(resultSet.getBoolean("is_identified"));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Sql exception", e);
        }
        if(user.getId() == null){
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(User user) {
        User fromDb = Optional.ofNullable(entityManager.find(User.class, user.getId()))
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + user.getId() + " not found"));

        BeanUtils.copyProperties(user, fromDb,
                user.getSecondName() == null ? "secondName" : null,
                user.getMiddleName() == null ? "middleName" : null,
                user.getPhone() == null ? "phone" : null,
                "document",
                user.getOffice() == null ? "office" : null);

        return fromDb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getReference(Long id) {
        return entityManager.getReference(User.class, id);
    }

    @Override
    public User getByUsername(String username){
        TypedQuery<User> userq = entityManager.createQuery("select u from User u where u.securityUser.username=:username", User.class);
        userq.setParameter("username", username);
        User user = userq.getSingleResult();
        if(user == null){
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return user;
    }

    private CriteriaQuery<User> buildCriteria(UserFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        root.fetch("position", JoinType.LEFT);
        root.fetch("citizenship", JoinType.LEFT);
        root.fetch("document", JoinType.LEFT).fetch("documentName", JoinType.LEFT);
        return cq.select(root).distinct(true).where(addPredicates(filter).toPredicate(root, cq, cb));
    }

    private Specification<User> addPredicates(UserFilter filter) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, criteriaBuilder) -> {
            predicates.add(criteriaBuilder.equal(root.get("office"), filter.getOfficeId()));
            if(filter.getFirstName() != null){
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + filter.getFirstName() + "%"));
            }
            if(filter.getSecondName() != null){
                predicates.add(criteriaBuilder.like(root.get("secondName"), "%" + filter.getSecondName() + "%"));
            }
            if(filter.getMiddleName() != null){
                predicates.add(criteriaBuilder.like(root.get("middleName"), "%" + filter.getMiddleName() + "%"));
            }
            if(filter.getDocCode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("document").get("documentName").get("docCode"), filter.getDocCode()));
            }
            if(filter.getPosition() != null){
                SetJoin<User, Position> userPositionJoin = root.joinSet("position");
                predicates.add(criteriaBuilder.equal(userPositionJoin.get("positionName"), filter.getPosition()));
            }
            if(filter.getCitizenshipCode() != null) {
                predicates.add(criteriaBuilder.equal(root.get("citizenship").get("citizenshipCode"), filter.getCitizenshipCode()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
