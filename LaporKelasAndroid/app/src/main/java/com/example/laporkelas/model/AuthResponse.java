package com.example.laporkelas.model;

import java.util.List;

public class AuthResponse {
    private String email;
    private String accessToken;
    private List<String> roles;

    public AuthResponse(String email, String accessToken, List<String> roles) {
        this.email = email;
        this.accessToken = accessToken;
        this.roles = roles;
    }

    // Add getters and setters for the fields

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
