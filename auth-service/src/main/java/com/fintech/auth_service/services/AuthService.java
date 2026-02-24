package com.fintech.auth_service.services;

import com.fintech.auth_service.dto.AuthResponse;
import com.fintech.auth_service.dto.LoginRequest;
import com.fintech.auth_service.dto.RegisterRequest;


public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}
