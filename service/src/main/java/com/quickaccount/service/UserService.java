package com.quickaccount.service;

import com.quickaccount.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserbyLogin(String login);
    User save(User user);
    List<User> getAllUsers();
}
