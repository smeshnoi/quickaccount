package com.quickaccount.service;

import com.quickaccount.entity.Role;
import com.quickaccount.entity.User;
import com.quickaccount.repository.RoleRepository;
import com.quickaccount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<GrantedAuthority> collection = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), collection);
    }

    @Override
    public User getUserbyLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public User save(User user) {
        HashSet<Role> roles = new HashSet<Role>();
        for (Role role : roleRepository.findAll()) {
            if ("USER".equals(role.getName())) {
                roles.add(role);
            }
        }
        user.setRoles(roles);
        User save = userRepository.save(user);
        return save;
    }
}
