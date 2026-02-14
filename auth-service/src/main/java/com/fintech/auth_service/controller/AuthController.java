package com.fintech.auth_service.controller;

import com.fintech.auth_service.dto.AuthResponse;
import com.fintech.auth_service.dto.LoginRequest;
import com.fintech.auth_service.dto.RegisterRequest;
import com.fintech.auth_service.dto.ValidateTokenResponse;
import com.fintech.auth_service.services.AuthService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/health")
    public String test() {
        return authService.test();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateTokenResponse> validateToken(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract token from "Bearer <token>"
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest()
                        .body(new ValidateTokenResponse(false, "Missing or invalid Authorization header"));
            }

            String token = authHeader.substring(7);
            
            // Validate token
            Claims claims = authService.validateToken(token);
            String userId = claims.getSubject();
            String role = claims.get("role", String.class);

            return ResponseEntity.ok(new ValidateTokenResponse(true, userId, role));
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(401)
                    .body(new ValidateTokenResponse(false, e.getMessage()));
        }
    }
}