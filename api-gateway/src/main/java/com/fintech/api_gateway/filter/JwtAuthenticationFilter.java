// package com.fintech.api_gateway.filter;

// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpServletRequestWrapper;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;

// @Component
// public class JwtAuthenticationFilter implements Filter {

//     private final RestTemplate restTemplate = new RestTemplate();
//     private static final String AUTH_SERVICE_URL = "http://localhost:8082/auth/validate";

//     private static final List<String> PUBLIC_ROUTES = Arrays.asList(
//             "/auth/login",
//             "/auth/register",
//             "/auth/health"
//     );

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {

//         HttpServletRequest httpRequest = (HttpServletRequest) request;
//         HttpServletResponse httpResponse = (HttpServletResponse) response;

//         String path = httpRequest.getRequestURI();

//         // Allow public routes without JWT
//         if (isPublicRoute(path)) {
//             chain.doFilter(request, response);
//             return;
//         }

//         // Extract JWT token from Authorization header
//         String authHeader = httpRequest.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             httpResponse.setContentType("application/json");
//             httpResponse.getWriter().write("{\"error\": \"Missing or invalid Authorization header\"}");
//             return;
//         }

//         try {
//             // Call Auth Service to validate token
//             HttpHeaders headers = new HttpHeaders();
//             headers.set("Authorization", authHeader);
//             HttpEntity<String> entity = new HttpEntity<>(headers);

//             ResponseEntity<Map> validationResponse = restTemplate.exchange(
//                     AUTH_SERVICE_URL,
//                     HttpMethod.GET,
//                     entity,
//                     Map.class
//             );

//             Map<String, Object> validationBody = validationResponse.getBody();

//             if (validationBody == null || !(Boolean) validationBody.get("valid")) {
//                 httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 httpResponse.setContentType("application/json");
//                 httpResponse.getWriter().write("{\"error\": \"Invalid or expired JWT token\"}");
//                 return;
//             }

//             // Extract user info from validation response
//             String userId = (String) validationBody.get("userId");
//             String role = (String) validationBody.get("role");

//             // Add headers to forward to downstream services
//             HttpServletRequest modifiedRequest = new HttpServletRequestWrapper(httpRequest) {
//                 @Override
//                 public String getHeader(String name) {
//                     if ("X-USER-ID".equals(name)) {
//                         return userId;
//                     }
//                     if ("X-USER-ROLE".equals(name)) {
//                         return role;
//                     }
//                     return super.getHeader(name);
//                 }
//             };

//             chain.doFilter(modifiedRequest, response);

//         } catch (Exception e) {
//             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             httpResponse.setContentType("application/json");
//             httpResponse.getWriter().write("{\"error\": \"Token validation failed\"}");
//         }
//     }

//     private boolean isPublicRoute(String path) {
//         return PUBLIC_ROUTES.stream().anyMatch(path::startsWith);
//     }
// }




// package com.fintech.api_gateway.filter;

// import jakarta.servlet.*;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpServletRequestWrapper;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Map;

// @Component
// public class JwtAuthenticationFilter implements Filter {

//     private final RestTemplate restTemplate = new RestTemplate();
//     private static final String AUTH_SERVICE_URL = "http://localhost:8082/auth/validate";

//     private static final List<String> PUBLIC_ROUTES = Arrays.asList(
//             "/auth/login",
//             "/auth/register",
//             "/auth/health"
//     );

//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//             throws IOException, ServletException {

//         HttpServletRequest httpRequest = (HttpServletRequest) request;
//         HttpServletResponse httpResponse = (HttpServletResponse) response;

//         String path = httpRequest.getRequestURI();

//         // Allow public routes without JWT
//         if (isPublicRoute(path)) {
//             chain.doFilter(request, response);
//             return;
//         }

//         // Extract JWT token from Authorization header
//         String authHeader = httpRequest.getHeader("Authorization");

//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             httpResponse.setContentType("application/json");
//             httpResponse.getWriter().write("{\"error\": \"Missing or invalid Authorization header\"}");
//             return;
//         }

//         try {
//             // Call Auth Service to validate token
//             HttpHeaders headers = new HttpHeaders();
//             headers.set("Authorization", authHeader);
//             HttpEntity<String> entity = new HttpEntity<>(headers);

//             ResponseEntity<Map> validationResponse = restTemplate.exchange(
//                     AUTH_SERVICE_URL,
//                     HttpMethod.GET,
//                     entity,
//                     Map.class
//             );

//             Map<String, Object> validationBody = validationResponse.getBody();

//             if (validationBody == null || !(Boolean) validationBody.get("valid")) {
//                 httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                 httpResponse.setContentType("application/json");
//                 httpResponse.getWriter().write("{\"error\": \"Invalid or expired JWT token\"}");
//                 return;
//             }

//             // Extract user info from validation response
//             String userId = (String) validationBody.get("userId");
//             String role = (String) validationBody.get("role");

//             // Add headers to forward to downstream services
//             HttpServletRequest modifiedRequest = new HttpServletRequestWrapper(httpRequest) {
//                 @Override
//                 public String getHeader(String name) {
//                     if ("X-USER-ID".equals(name)) {
//                         return userId;
//                     }
//                     if ("X-USER-ROLE".equals(name)) {
//                         return role;
//                     }
//                     return super.getHeader(name);
//                 }
//             };

//             chain.doFilter(modifiedRequest, response);

//         } catch (Exception e) {
//             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             httpResponse.setContentType("application/json");
//             httpResponse.getWriter().write("{\"error\": \"Token validation failed\"}");
//         }
//     }

//     private boolean isPublicRoute(String path) {
//         return PUBLIC_ROUTES.stream().anyMatch(path::startsWith);
//     }
// }



package com.fintech.api_gateway.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.*;

@Component
public class JwtAuthenticationFilter implements Filter {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String AUTH_SERVICE_URL = "http://localhost:8082/auth/validate";

    private static final List<String> PUBLIC_ROUTES = Arrays.asList(
            "/auth/login",
            "/auth/register",
            "/auth/health",
            "/auth/google"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        // Allow public routes without JWT
        if (isPublicRoute(path)) {
            chain.doFilter(request, response);
            return;
        }

        // Extract JWT token from Authorization header
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\"error\": \"Missing or invalid Authorization header\"}");
            return;
        }

        try {
            // Call Auth Service to validate token
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> validationResponse = restTemplate.exchange(
                    AUTH_SERVICE_URL,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            Map<String, Object> validationBody = validationResponse.getBody();

            if (validationBody == null || !(Boolean) validationBody.get("valid")) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType("application/json");
                httpResponse.getWriter().write("{\"error\": \"Invalid or expired JWT token\"}");
                return;
            }

            // Extract user info from validation response
            String userId = String.valueOf(validationBody.get("userId"));
            String role = (String) validationBody.get("role");

            // Create wrapper to add headers
            HttpServletRequestWrapper modifiedRequest = new HttpServletRequestWrapper(httpRequest) {
                private final Map<String, String> customHeaders = new HashMap<>();

                {
                    customHeaders.put("X-USER-ID", userId);
                    customHeaders.put("X-USER-ROLE", role);
                }

                @Override
                public String getHeader(String name) {
                    String headerValue = customHeaders.get(name);
                    if (headerValue != null) {
                        return headerValue;
                    }
                    return super.getHeader(name);
                }

                @Override
                public Enumeration<String> getHeaderNames() {
                    Set<String> set = new HashSet<>(customHeaders.keySet());
                    Enumeration<String> e = super.getHeaderNames();
                    while (e.hasMoreElements()) {
                        set.add(e.nextElement());
                    }
                    return Collections.enumeration(set);
                }

                @Override
                public Enumeration<String> getHeaders(String name) {
                    String headerValue = customHeaders.get(name);
                    if (headerValue != null) {
                        return Collections.enumeration(Collections.singletonList(headerValue));
                    }
                    return super.getHeaders(name);
                }
            };

            chain.doFilter(modifiedRequest, response);

        } catch (Exception e) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write("{\"error\": \"Token validation failed\"}");
        }
    }

    private boolean isPublicRoute(String path) {
        return PUBLIC_ROUTES.stream().anyMatch(path::startsWith);
    }
}