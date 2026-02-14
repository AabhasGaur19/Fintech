package com.fintech.auth_service.dto;

public class ValidateTokenResponse {
    private boolean valid;
    private String userId;
    private String role;
    private String message;

    public ValidateTokenResponse(boolean valid, String userId, String role, String message) {
        this.valid = valid;
        this.userId = userId;
        this.role = role;
        this.message = message;
    }

    // Success constructor
    public ValidateTokenResponse(boolean valid, String userId, String role) {
        this.valid = valid;
        this.userId = userId;
        this.role = role;
    }

    // Error constructor
    public ValidateTokenResponse(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    // Getters and Setters
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}