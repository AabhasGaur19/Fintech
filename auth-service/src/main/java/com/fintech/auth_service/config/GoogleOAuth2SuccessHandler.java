package com.fintech.auth_service.config;

import com.fintech.auth_service.entity.User;
import com.fintech.auth_service.repository.AuthRepository;
import com.fintech.auth_service.services.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    @Lazy // Use Lazy to avoid circular dependency issues with SecurityConfig
    private AuthService authService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        
        // Default username to email or part of email if name is missing
        String username = email != null ? email.split("@")[0] : "user_" + UUID.randomUUID().toString().substring(0, 8);

        User user = authRepository.findByEmail(email).orElse(null);

        if (user == null) {
            // Register new user
            user = new User();
            user.setEmail(email);
            user.setUsername(username);
            // Set a dummy password since they use Google Login
            user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            user.setRole("USER");
            user.setAuthProvider("GOOGLE");
            
            user = authRepository.save(user);
        } else {
            if (user.getAuthProvider() == null) {
                user.setAuthProvider("GOOGLE"); // Only if you want to claim them as Google users now
                authRepository.save(user);
            }
        }

        // Generate JWT Token
        String token = authService.generateToken(user.getId().toString(), user.getRole());

        // Redirect to Frontend with details in query params
        // Assuming Frontend runs on port 5173 (Vite default)
        String targetUrl = "http://localhost:5173/login?token=" + token 
                + "&userId=" + user.getId() 
                + "&username=" + user.getUsername() 
                + "&role=" + user.getRole();

        response.sendRedirect(targetUrl);
    }
}