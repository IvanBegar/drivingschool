package com.begar.demo.controller;

import com.begar.demo.entity.User;
import com.begar.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public User getUser(Authentication authentication) {
        User user = new User();
        user.setUsername(authentication.getName());
        user.setRole(authentication.getAuthorities().toString());
        return user;
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
      userService.addNewUser(user);
    }
}
