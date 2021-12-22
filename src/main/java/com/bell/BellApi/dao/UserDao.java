package com.bell.BellApi.dao;

import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.model.User;

import java.util.List;


/**
 * DAO for {@link User}
 */
public interface UserDao {

    /**
     * Get all users by {@link UserFilter}
     * @param filter
     * @return
     */
    List<User> getAll(UserFilter filter);

    /**
     * Get user by id
     * @param id
     * @return
     */
    UserDtoId getById(Long id);

    /**
     * Save user
     * @param user
     */
    void save(User user);

    /**
     * Update user
     * @param user
     * @return
     */
    User update(User user);

    /**
     * Get user reference
     * @param id
     * @return
     */
    User getReference(Long id);
}
