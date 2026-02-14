package com.fintech.api_gateway.config;


import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addRequestHeader;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.addResponseHeader;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> authServiceRoute() {
        return route("auth-service")
                .route(RequestPredicates.path("/auth/**"), HandlerFunctions.http("http://localhost:8082"))
                .filter(addRequestHeader("X-Gateway-Request", "API-Gateway"))
                .filter(addResponseHeader("X-Gateway-Response", "API-Gateway"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return route("user-service")
                .route(RequestPredicates.path("/user/**"), HandlerFunctions.http("http://localhost:8083"))
                .filter(addRequestHeader("X-Gateway-Request", "API-Gateway"))
                .filter(addResponseHeader("X-Gateway-Response", "API-Gateway"))
                .build();
    }
}
