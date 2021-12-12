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

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/list")
    public List<UserDtoAll> getAll(@RequestBody UserFilter filter){
        List<UserDtoAll> list = userService.getAll(filter);
        return list;
    }

    @GetMapping("/{id}")
    public UserDtoId getById(@PathVariable Long id){
        UserDtoId user = userService.getById(id);
        return user;
    }

    @PostMapping("/add")
    public void add(@RequestBody UserRequest user){
        userService.add(user);
    }


    @PostMapping("/update")
    public void update(@RequestBody UserRequest user){
        userService.update(user);
    }
}
