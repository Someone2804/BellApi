package com.bell.BellApi.service;


import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.bell.BellApi.dto.user.response.UserDtoAll;
import com.bell.BellApi.dto.user.response.UserDtoId;

import java.util.List;

public interface UserService {

    void add(UserRequest user);
    void update(UserRequest user);
    List<UserDtoAll> getAll(UserFilter filter);
    UserDtoId getById(Long id);
}
