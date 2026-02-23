package com.fintech.user_service.dto.response;

import com.fintech.user_service.enums.ThemePreference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Boolean isProfileComplete;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}