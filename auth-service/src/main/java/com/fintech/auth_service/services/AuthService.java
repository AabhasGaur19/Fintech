package com.fintech.auth_service.services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String authenticate(String username) {
        return "Authenticated: " + username;
    }
}
