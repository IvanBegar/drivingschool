package com.begar.demo.dto.response;

public class AuthenticationResponseDTO {

    private String jwt;

    public AuthenticationResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "AuthenticationResponseDTO{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
