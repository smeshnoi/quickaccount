package com.quickaccount.service;

import com.quickaccount.entity.User;
import com.quickaccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserSeviceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserSeviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
        //Collection<Role>
//                new SimpleGrantedAuthority(user.getRole().name());
        return null;// new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), );
    }
}
