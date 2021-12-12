package com.bell.BellApi.service.impl;

import com.bell.BellApi.dao.UserDao;
import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.bell.BellApi.dto.user.response.UserDtoAll;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(transactionManager = "jpa")
    @Override
    public void add(UserRequest user) {

    }

    @Transactional(transactionManager = "jpa")
    @Override
    public void update(UserRequest user) {

    }

    @Transactional(transactionManager = "jpa")
    @Override
    public List<UserDtoAll> getAll(UserFilter filter) {
        filter.validate();
        return UserDtoAll.mapToDtoList(userDao.getAll(filter));
    }

    @Override
    public UserDtoId getById(Long id) {
        return userDao.getById(id);
    }
}
