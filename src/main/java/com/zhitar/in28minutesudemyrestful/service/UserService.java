package com.zhitar.in28minutesudemyrestful.service;

import com.zhitar.in28minutesudemyrestful.dao.PostRepository;
import com.zhitar.in28minutesudemyrestful.dao.UserRepository;
import com.zhitar.in28minutesudemyrestful.domain.Post;
import com.zhitar.in28minutesudemyrestful.domain.User;
import com.zhitar.in28minutesudemyrestful.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.getOne(id);
        userRepository.deleteById(id);
    }

    public List<Post> userPosts(Long id) {
        return postRepository.findAllByUserId(id);
    }

    public User addPost(Long id, Post post) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(String.format("User with id - %d not found", id))
                );
        post.setUser(userFromDb);
        postRepository.save(post);
        return userFromDb;
    }
}
