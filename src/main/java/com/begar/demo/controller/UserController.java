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
        User user = getUserByName(authentication.getName());
        return user;
    }

    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username) {
        return userService.getUserByName(username);
    }

    @PostMapping
    public void addNewUser(@RequestBody User user) {
        System.out.println(user.toString());
        userService.addNewUser(user);
    }
}
