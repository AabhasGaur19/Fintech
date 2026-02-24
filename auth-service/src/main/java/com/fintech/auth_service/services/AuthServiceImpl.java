package com.fintech.auth_service.services;

import com.fintech.auth_service.dto.AuthResponse;
import com.fintech.auth_service.dto.LoginRequest;
import com.fintech.auth_service.dto.RegisterRequest;
import com.fintech.auth_service.entity.User;
import com.fintech.auth_service.repository.AuthRepository;
import com.fintech.auth_service.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:3600000}")
    private Long expiration;

    public String test() {
        return "Working!!";
    }

    public AuthResponse register(RegisterRequest request) {
        // Check if username exists
        if (authRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Check if email exists
        if (authRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setAuthProvider("LOCAL");

        user = authRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user.getId().toString(), user.getRole());

        return new AuthResponse(token, user.getId().toString(), user.getUsername(), user.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        // Find user by username
        User user = authRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate token
        String token = jwtUtil.generateToken(user.getId().toString(), user.getRole());

        return new AuthResponse(token, user.getId().toString(), user.getUsername(), user.getRole());
    }
}