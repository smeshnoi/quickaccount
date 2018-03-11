package com.quickaccount.service;

import com.quickaccount.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserbyLogin(String login);
}
