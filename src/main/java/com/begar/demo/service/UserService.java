package com.begar.demo.service;

import com.begar.demo.dto.UserInfoDTO;
import com.begar.demo.entity.Role;
import com.begar.demo.entity.User;
import com.begar.demo.exception.DataException;
import com.begar.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public void addNewUser(UserInfoDTO user) {
        User newUser = new User();
        if (user.getUsername().equals(getUserByName(user.getUsername()).getUsername())) {
            throw new DataException("User with this username already exist!");
        }
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setPhone(user.getPhone());
        System.out.println(user.getPhone());
        if (user.getRole().equals("Admin")) {
            Role role = new Role();
            role.setRole_id(1);
            role.setRole("ROLE_ADMIN");
            newUser.setRole(role);
        } else {
            Role role = new Role();
            role.setRole_id(2);
            role.setRole("ROLE_USER");
            newUser.setRole(role);
        }
        userRepository.addNewUser(newUser);
    }

    public User getUserByName(String username) {
        return userRepository.getUserByName(username);
    }
}
