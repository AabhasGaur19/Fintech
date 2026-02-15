package com.fintech.user_service.dto;

import com.fintech.user_service.enums.ThemePreference;
import java.time.LocalDateTime;

public class UserProfileResponse {

    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private ThemePreference themePreference;
    private Boolean twoFactorEnabled;
    private String preferredLanguage;
    private String timezone;
    private Boolean notificationEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public UserProfileResponse() {
    }

    public UserProfileResponse(Long userId, String email,String firstName, String lastName,
                               String profilePictureUrl, ThemePreference themePreference,
                               Boolean twoFactorEnabled, String preferredLanguage,
                               String timezone, Boolean notificationEnabled,
                               LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePictureUrl = profilePictureUrl;
        this.themePreference = themePreference;
        this.twoFactorEnabled = twoFactorEnabled;
        this.preferredLanguage = preferredLanguage;
        this.timezone = timezone;
        this.notificationEnabled = notificationEnabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public com.fintech.user_service.enums.ThemePreference getThemePreference() {
        return themePreference;
    }

    public void setThemePreference(ThemePreference themePreference) {
        this.themePreference = themePreference;
    }

    public Boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Boolean getNotificationEnabled() {
        return notificationEnabled;
    }

    public void setNotificationEnabled(Boolean notificationEnabled) {
        this.notificationEnabled = notificationEnabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
