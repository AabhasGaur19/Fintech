package com.fintech.auth_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/test")
    public String test() {
        return "Auth Service is working!";
    }

    @PostMapping("/login")
    public String login(@RequestBody String body) {
        return "Login request received: " + body;
    }
}

