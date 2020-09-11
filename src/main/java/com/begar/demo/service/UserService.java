package com.begar.demo.service;

import com.begar.demo.dto.request.UserInfoDTO;
import com.begar.demo.entity.User;
import com.begar.demo.entity.enums.ROLE;
import com.begar.demo.exception.NoDataException;
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
        if (getUserByName(user.getUsername()) != null) {
            throw new NoDataException("User with this username already exist!");
        }
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        if (user.getRole().equals("Admin")) {
            newUser.setRole(ROLE.ROLE_ADMIN);
        } else {
            newUser.setRole(ROLE.ROLE_USER);
        }
        userRepository.save(newUser);
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}
