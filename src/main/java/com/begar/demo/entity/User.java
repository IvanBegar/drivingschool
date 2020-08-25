package com.begar.demo.entity;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String username;
    private String password;
    private String role;
    private String phone;

    public User() {
        this.username = "";
        this.password = "";
        this.role = "";
        this.phone = "";
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
