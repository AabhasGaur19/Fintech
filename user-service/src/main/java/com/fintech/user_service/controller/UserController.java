package com.fintech.user_service.controller;


import com.fintech.user_service.dto.request.CreateProfileRequest;
import com.fintech.user_service.dto.request.UpdateProfileRequest;
import com.fintech.user_service.dto.response.MessageResponse;
import com.fintech.user_service.dto.response.UserProfileResponse;
import com.fintech.user_service.services.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User Service is running!");
    }

    @PostMapping("/profile/create")
    public ResponseEntity<MessageResponse> createProfile(@Valid @RequestBody CreateProfileRequest request) {
        log.info("POST /user/profile/create - userId: {}", request.getUserId());
        MessageResponse response = userService.createProfile(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@RequestHeader("X-USER-ID") String userIdHeader) {
        log.info("GET /user/profile - X-USER-ID: {}", userIdHeader);
        
        Long userId = Long.parseLong(userIdHeader);
        UserProfileResponse response = userService.getProfile(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<MessageResponse> updateProfile(@RequestHeader("X-USER-ID") String userIdHeader,@Valid @RequestBody UpdateProfileRequest request) {
        log.info("PUT /user/profile - X-USER-ID: {}", userIdHeader);
        
        Long userId = Long.parseLong(userIdHeader);
        MessageResponse response = userService.updateProfile(userId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/profile/picture")
    public ResponseEntity<MessageResponse> deleteProfilePicture(@RequestHeader("X-USER-ID") String userIdHeader) {
        log.info("DELETE /user/profile/picture - X-USER-ID: {}", userIdHeader);
        
        Long userId = Long.parseLong(userIdHeader);
        MessageResponse response = userService.deleteProfilePicture(userId);
        return ResponseEntity.ok(response);
    }
}
