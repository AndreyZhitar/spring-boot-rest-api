package com.zhitar.in28minutesudemyrestful.controller;

import com.zhitar.in28minutesudemyrestful.domain.Post;
import com.zhitar.in28minutesudemyrestful.domain.User;
import com.zhitar.in28minutesudemyrestful.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("jpa")
@AllArgsConstructor
public class UserJpaController {

    private final UserService userService;

    @GetMapping(value = "/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Long id) {
        User user = userService.findById(id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(link.withRel("all-users"));
        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User save = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("users/{id}/posts")
    public List<Post> userPosts(@PathVariable Long id) {
        return userService.userPosts(id);
    }

    @PostMapping("users/{id}/posts")
    public ResponseEntity<Object> createPost(@Valid @PathVariable Long id, @RequestBody Post post) {
        User user = userService.addPost(id, post);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/posts/{postId}")
                .buildAndExpand(user.getId(), post.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
