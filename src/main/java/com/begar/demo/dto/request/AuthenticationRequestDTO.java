package com.begar.demo.dto.request;

public class AuthenticationRequestDTO {

    private String email;
    private String password;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + "[PROTECTED]" + '\'' +
                '}';
    }
}
