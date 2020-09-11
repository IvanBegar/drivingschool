package com.begar.demo.dto.request;

public class UserInfoDTO {

    private String username;
    private String role;
    private String email;
    private String password;

    public UserInfoDTO(String username, String role, String phone, String password) {
        this.username = username;
        this.role = role;
        this.email = phone;
        this.password = password;
    }

    public UserInfoDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String phone) {
        this.email = phone;
    }
}
