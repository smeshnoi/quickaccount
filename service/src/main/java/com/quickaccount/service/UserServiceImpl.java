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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    //private UsersRolesRepository usersRolesRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        //this.usersRolesRepository = usersRolesRepository;
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
        List<Role> addList = new ArrayList<>();
        List<Role> all = roleRepository.findAll();
        HashSet<Role> roles = new HashSet<>(roleRepository.findAll());
        user.setRoles(roles);
        for (Role role : all) {
            if (role.getName().equals("USER")) {
                addList.add(role);

                //usersRolesRepository.save(new UsersRoles(save, role));
            }
        }
        User save = userRepository.save(user);
        System.out.println(save);
        //User save = userRepository.save(user);

        return null;
    }
}
