package com.quickaccount.repository;

import com.quickaccount.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByLogin(String login);
}
