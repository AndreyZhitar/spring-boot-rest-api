package com.zhitar.in28minutesudemyrestful.controller;

import com.zhitar.in28minutesudemyrestful.dao.UserDao;
import com.zhitar.in28minutesudemyrestful.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserDao userDao;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<User> findAll() {
        return userDao.findAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userDao.save(user);
    }

    @GetMapping(value = "{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User findOne(@PathVariable Long userId) {
        return userDao.findOne(userId);
    }
}
