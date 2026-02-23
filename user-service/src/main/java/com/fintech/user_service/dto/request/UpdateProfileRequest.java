package com.fintech.user_service.dto.request;

import com.fintech.user_service.enums.ThemePreference;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {
    
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;
    
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;
    
    private String profilePictureUrl;
    
    private ThemePreference themePreference;
    
    private Boolean twoFactorEnabled;
    
    private String preferredLanguage;
    
    private String timezone;
    
    private Boolean notificationEnabled;
}