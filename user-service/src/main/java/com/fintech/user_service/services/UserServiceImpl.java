package com.fintech.user_service.services;


import com.fintech.user_service.dto.request.CreateProfileRequest;
import com.fintech.user_service.dto.request.UpdateProfileRequest;
import com.fintech.user_service.dto.response.MessageResponse;
import com.fintech.user_service.dto.response.UserProfileResponse;
import com.fintech.user_service.entity.UserProfile;
import com.fintech.user_service.exception.ResourceNotFoundException;
import com.fintech.user_service.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public MessageResponse createProfile(CreateProfileRequest request) {
        log.info("Creating profile for userId: {}", request.getUserId());
        
        // Check if profile already exists
        if (userProfileRepository.existsByUserId(request.getUserId())) {
            throw new IllegalArgumentException("Profile already exists for userId: " + request.getUserId());
        }

        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Profile already exists for email: " + request.getEmail());
        }

        // Create new profile
        UserProfile profile = new UserProfile();
        profile.setUserId(request.getUserId());
        profile.setEmail(request.getEmail());

        userProfileRepository.save(profile);
        
        log.info("Profile created successfully for userId: {}", request.getUserId());
        return new MessageResponse("Profile created successfully");
    }

    @Override
    public UserProfileResponse getProfile(Long userId) {
        log.info("Fetching profile for userId: {}", userId);
        
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Profile not found for userId: " + userId));

        return mapToResponse(profile);
    }

    @Override
    @Transactional
    public MessageResponse updateProfile(Long userId, UpdateProfileRequest request) {
        log.info("Updating profile for userId: {}", userId);
        
        UserProfile profile = userProfileRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Profile not found for userId: " + userId));

        // Update only non-null fields
        if (request.getFirstName() != null) {
            profile.setFirstName(request.getFirstName().trim());
        }
        if (request.getLastName() != null) {
            profile.setLastName(request.getLastName().trim());
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

        // Mark profile as complete if firstName and lastName are filled
        if (profile.getFirstName() != null && !profile.getFirstName().isEmpty() &&
            profile.getLastName() != null && !profile.getLastName().isEmpty()) {
            profile.setIsProfileComplete(true);
            log.info("Profile marked as complete for userId: {}", userId);
        }

        userProfileRepository.save(profile);
        
        log.info("Profile updated successfully for userId: {}", userId);
        return new MessageResponse("Profile updated successfully");
    }

    @Override
    @Transactional
    public MessageResponse deleteProfilePicture(Long userId) {
        log.info("Deleting profile picture for userId: {}", userId);
        
        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for userId: " + userId));

        profile.setProfilePictureUrl(null);
        userProfileRepository.save(profile);
        
        log.info("Profile picture deleted successfully for userId: {}", userId);
        return new MessageResponse("Profile picture removed successfully");
    }

    private UserProfileResponse mapToResponse(UserProfile profile) {
        return UserProfileResponse.builder()
                .userId(profile.getUserId())
                .email(profile.getEmail())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .profilePictureUrl(profile.getProfilePictureUrl())
                .themePreference(profile.getThemePreference())
                .twoFactorEnabled(profile.getTwoFactorEnabled())
                .preferredLanguage(profile.getPreferredLanguage())
                .timezone(profile.getTimezone())
                .notificationEnabled(profile.getNotificationEnabled())
                .isProfileComplete(profile.getIsProfileComplete())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .build();
    }
}
