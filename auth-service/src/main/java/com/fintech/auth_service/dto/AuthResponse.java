package com.fintech.auth_service.dto;

public class AuthResponse {
    private String token;
    private String userId;
    private String username;
    private String role;
    private Boolean isProfileComplete;

    public AuthResponse(String token, String userId, String username, String role,Boolean isProfileComplete) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.isProfileComplete = false;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Boolean getIsProfileComplete() {
        return isProfileComplete;
    }

    public void setIsProfileComplete(Boolean isProfileComplete) {
        this.isProfileComplete = isProfileComplete;
    }
}