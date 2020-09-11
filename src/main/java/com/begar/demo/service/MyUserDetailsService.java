package com.begar.demo.service;

import com.begar.demo.entity.User;
import com.begar.demo.exception.NoDataException;
import com.begar.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws NoDataException {
        User user;
        if (userRepository.findByEmail(usernameOrEmail) != null) {
            user = userRepository.findByEmail(usernameOrEmail);
        } else {
            user = userRepository.findByUsername(usernameOrEmail);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
