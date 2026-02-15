package com.fintech.user_service.services;


import com.fintech.user_service.dto.CreateProfileRequest;
import com.fintech.user_service.dto.UpdateProfileRequest;
import com.fintech.user_service.dto.MessageResponse;
import com.fintech.user_service.dto.UserProfileResponse;
import com.fintech.user_service.entity.UserProfile;
import com.fintech.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userProfileRepository;

    // Create profile (called by Auth Service)
    public MessageResponse createProfile(CreateProfileRequest request) {
        // Check if profile already exists
        if (userProfileRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Profile already exists for userId: " + request.getUserId());
        }

        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Profile already exists for email: " + request.getEmail());
        }

        // Create new profile with only userId and email
        UserProfile profile = new UserProfile();
        profile.setUserId(request.getUserId());
        profile.setEmail(request.getEmail());
        // All other fields use default values from entity

        userProfileRepository.save(profile);

        return new MessageResponse("Profile created successfully");
    }

    // Get profile by userId (from X-USER-ID header)
    public UserProfileResponse getProfile(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for userId: " + userId));

        return mapToResponse(profile);
    }

    // Update profile
    public com.fintech.user_service.dto.MessageResponse updateProfile(Long userId, UpdateProfileRequest request) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for userId: " + userId));

        // Update only non-null fields
        if (request.getFirstName() != null) {
            profile.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            profile.setLastName(request.getLastName());
        }
        if (request.getProfilePictureUrl() != null) {
            profile.setProfilePictureUrl(request.getProfilePictureUrl());
        }
        if (request.getThemePreference() != null) {
            profile.setThemePreference(request.getThemePreference());
        }
        if (request.getTwoFactorEnabled() != null) {
            profile.setTwoFactorEnabled(request.getTwoFactorEnabled());
        }
        if (request.getPreferredLanguage() != null) {
            profile.setPreferredLanguage(request.getPreferredLanguage());
        }
        if (request.getTimezone() != null) {
            profile.setTimezone(request.getTimezone());
        }
        if (request.getNotificationEnabled() != null) {
            profile.setNotificationEnabled(request.getNotificationEnabled());
        }

        if (profile.getFirstName() != null && !profile.getFirstName().isEmpty() &&
            profile.getLastName() != null && !profile.getLastName().isEmpty()) {
            profile.setIsProfileComplete(true);
        }

        userProfileRepository.save(profile);

        return new MessageResponse("Profile updated successfully");
    }

    // Delete profile picture
    public MessageResponse deleteProfilePicture(Long userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for userId: " + userId));

        profile.setProfilePictureUrl(null);
        userProfileRepository.save(profile);

        return new MessageResponse("Profile picture removed successfully");
    }

    // Helper method to map entity to response
    private UserProfileResponse mapToResponse(UserProfile profile) {
        return new UserProfileResponse(
                profile.getUserId(),
                profile.getEmail(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getProfilePictureUrl(),
                profile.getThemePreference(),
                profile.getTwoFactorEnabled(),
                profile.getPreferredLanguage(),
                profile.getTimezone(),
                profile.getNotificationEnabled(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
