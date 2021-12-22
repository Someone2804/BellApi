package com.bell.BellApi.controller;

import com.bell.BellApi.dto.filter.UserFilter;
import com.bell.BellApi.dto.user.request.UserRequest;
import com.bell.BellApi.dto.user.response.UserDtoAll;
import com.bell.BellApi.dto.user.response.UserDtoId;
import com.bell.BellApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User controller
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Mapping for /list
     * @param filter
     * @return user dto's list
     */
    @PostMapping("/list")
    public List<UserDtoAll> getAll(@RequestBody UserFilter filter){
        List<UserDtoAll> list = userService.getAll(filter);
        return list;
    }

    /**
     * Mapping for /{id}
     * @param id
     * @return user dto
     */
    @GetMapping("/{id}")
    public UserDtoId getById(@PathVariable Long id){
        UserDtoId user = userService.getById(id);
        return user;
    }

    /**
     * Mapping for /add
     * @param user
     */
    @PostMapping("/add")
    public void add(@RequestBody UserRequest user){
        userService.add(user);
    }

    /**
     * Mapping for /update
     * @param user
     */
    @PostMapping("/update")
    public void update(@RequestBody UserRequest user){
        userService.update(user);
    }
}
