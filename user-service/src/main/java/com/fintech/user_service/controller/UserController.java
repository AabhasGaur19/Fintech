package com.fintech.user_service.controller;


import com.fintech.user_service.dto.CreateProfileRequest;
import com.fintech.user_service.dto.UpdateProfileRequest;
import com.fintech.user_service.dto.MessageResponse;
import com.fintech.user_service.dto.UserProfileResponse;
import com.fintech.user_service.entity.UserProfile;
import com.fintech.user_service.repository.UserRepository;
import com.fintech.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Health check
    @GetMapping("/health")
    public String health() {
        return "User Service is running!";
    }

    // Create profile (called by Auth Service after registration)
    @PostMapping("/profile/create")
    public ResponseEntity<?> createProfile(@RequestBody CreateProfileRequest request) {
        try {
            MessageResponse response = userService.createProfile(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Get current user profile (reads X-USER-ID header from Gateway)
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("X-USER-ID") String userIdHeader) {
        try {
            Long userId = Long.parseLong(userIdHeader);
            UserProfileResponse response = userService.getProfile(userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Update profile
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestHeader("X-USER-ID") String userIdHeader,
            @RequestBody UpdateProfileRequest request) {
        try {
            Long userId = Long.parseLong(userIdHeader);
            MessageResponse response = userService.updateProfile(userId, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Delete profile picture
    @DeleteMapping("/profile/picture")
    public ResponseEntity<?> deleteProfilePicture(@RequestHeader("X-USER-ID") String userIdHeader) {
        try {
            Long userId = Long.parseLong(userIdHeader);
            MessageResponse response = userService.deleteProfilePicture(userId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

@GetMapping("/profile/status")
public ResponseEntity<?> getProfileStatus(@RequestHeader("X-USER-ID") String userIdHeader) {
    try {
        Long userId = Long.parseLong(userIdHeader);
        UserProfile profile = userRepository.findByUserId(userId).orElse(null);
        
        Map<String, Object> status = new HashMap<>();
        if (profile == null) {
            status.put("exists", false);
            status.put("isComplete", false);
        } else {
            status.put("exists", true);
            status.put("isComplete", profile.getIsProfileComplete());
        }
        
        return ResponseEntity.ok(status);
    } catch (Exception e) {
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
}