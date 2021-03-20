package com.zhitar.in28minutesudemyrestful.controller;

import com.zhitar.in28minutesudemyrestful.dao.UserDao;
import com.zhitar.in28minutesudemyrestful.domain.User;
import com.zhitar.in28minutesudemyrestful.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/resource")
public class UserResource {

    private final UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/users")
    public List<User> retrieveAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Long id) {
        User user = userDao.findOne(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(link.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User save = userDao.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userDao.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }
}
