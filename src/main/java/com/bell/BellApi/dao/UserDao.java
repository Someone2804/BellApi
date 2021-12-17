package com.bell.BellApi.dao;

import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll(UserFilter filter);
    UserDtoId getById(Long id);
    void save(User user);
    User update(User user);
    User getReference(Long id);
}
