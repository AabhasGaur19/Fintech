package com.fintech.user_service.services;


import com.fintech.user_service.dto.request.CreateProfileRequest;
import com.fintech.user_service.dto.request.UpdateProfileRequest;
import com.fintech.user_service.dto.response.MessageResponse;
import com.fintech.user_service.dto.response.UserProfileResponse;

public interface UserService {
    
    MessageResponse createProfile(CreateProfileRequest request);
    
    UserProfileResponse getProfile(Long userId);
    
    MessageResponse updateProfile(Long userId, UpdateProfileRequest request);
    
    MessageResponse deleteProfilePicture(Long userId);
}
