package com.quickaccount.repository;

import com.quickaccount.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User getUserByLogin(String login);
}
