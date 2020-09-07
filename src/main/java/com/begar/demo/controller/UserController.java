package com.begar.demo.controller;

import com.begar.demo.dto.request.UserInfoDTO;
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

    @GetMapping
    public UserInfoDTO getUser(Authentication authentication) {
        User user = getUserByName(authentication.getName());
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setPhone(user.getPhone());
        userInfoDTO.setRole(user.getRole().getRole());
        return userInfoDTO;
    }

    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username) {
        return userService.getUserByName(username);
    }

    @PostMapping
    public void addNewUser(@RequestBody UserInfoDTO user) {
        userService.addNewUser(user);
    }
}
