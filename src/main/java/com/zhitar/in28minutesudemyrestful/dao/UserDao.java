package com.zhitar.in28minutesudemyrestful.dao;

import com.zhitar.in28minutesudemyrestful.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserDao {

    private final Map<Long, User> map = new ConcurrentHashMap<>();
    {
        map.put(1L, new User(1L, "Andrey", LocalDateTime.of(1990, 2, 9, 12, 0)));
        map.put(2L, new User(2L, "Tolik", LocalDateTime.of(1990, 2, 9, 12, 0)));
        map.put(3L, new User(3L, "Marina", LocalDateTime.of(1990, 4, 20, 7, 0)));
    }

    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

    public User save(User user) {
        synchronized (map) {
            Long max = map.keySet().stream().max(Long::compareTo).orElse(1L);
            user.setId(++max);
            map.put(max, user);
        }
        return user;
    }

    public User findOne(Long id) {
        Objects.requireNonNull(id);
        return map.get(id);
    }

    public User deleteById(Long id) {
        return map.remove(id);
    }
}
