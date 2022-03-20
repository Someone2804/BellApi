package com.bell.BellApi.service;


import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.bell.BellApi.dto.user.response.UserDtoAll;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.model.user.User;

import java.util.List;

/**
 * Service for {@link User}
 */
public interface UserService {

    /**
     * Add user
     * @param user
     */
    void add(UserRequest user);

    /**
     * Update user
     * @param user
     */
    void update(UserRequest user);

    /**
     * Get all users by filter
     * @param filter
     * @return {@link UserDtoAll}
     */
    List<UserDtoAll> getAll(UserFilter filter);

    /**
     * Get user by id
     * @param id
     * @return {@link UserDtoId}
     */
    UserDtoId getById(Long id);
}
