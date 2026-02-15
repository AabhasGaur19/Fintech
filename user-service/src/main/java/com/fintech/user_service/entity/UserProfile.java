package com.fintech.user_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fintech.user_service.enums.ThemePreference;

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId; // From Auth Service

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email; // From Auth Service

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "profile_picture_url", length = 500)
    private String profilePictureUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme_preference", length = 20, nullable = false)
    private ThemePreference themePreference = ThemePreference.LIGHT;

    @Column(name = "two_factor_enabled", nullable = false)
    private Boolean twoFactorEnabled = false;

    @Column(name = "preferred_language", length = 10, nullable = false)
    private String preferredLanguage = "EN";

    @Column(name = "timezone", length = 50, nullable = false)
    private String timezone = "UTC";

    @Column(name = "notification_enabled", nullable = false)
    private Boolean notificationEnabled = true;

    @Column(name = "is_profile_complete", nullable = false)
    private Boolean isProfileComplete = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserProfile() {
    }

    public UserProfile(Long userId, String email, String firstName, String lastName,
                       String profilePictureUrl, ThemePreference themePreference,
                       Boolean twoFactorEnabled, String preferredLanguage,
                       String timezone, Boolean notificationEnabled,Boolean isProfileComplete,
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
        this.isProfileComplete = false;
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

    public ThemePreference getThemePreference() {
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

    public Boolean getIsProfileComplete() {
        return isProfileComplete;
    }

    public void setIsProfileComplete(Boolean isProfileComplete) {
        this.isProfileComplete = isProfileComplete;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
