package com.project.springcloudapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p-> p.path("/api/supplier/**")
                        .uri("lb://water-supplier-service"))
                .route(p-> p.path("/api/tanker-supplier/**")
                        .uri("lb://tanker-service"))
                .build();
    }
}
