package com.zhitar.in28minutesudemyrestful.dao;

import com.zhitar.in28minutesudemyrestful.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
