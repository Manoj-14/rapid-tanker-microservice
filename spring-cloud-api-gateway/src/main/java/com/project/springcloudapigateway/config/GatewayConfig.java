package com.project.springcloudapigateway.config;

import com.project.springcloudapigateway.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter filter;

    @Autowired
    public GatewayConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator gateWayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p-> p.path("/api/supplier/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://water-supplier-service"))
                .route(p-> p.path("/api/tanker-supplier/**")
                        .filters(f->f.filter(filter))
                        .uri("lb://tanker-service"))
                .route(p->p.path("/api/user/**")
                        .filters(f->f.filter(filter))
                        .uri("lb://user-service"))
                .route(p->p.path("/api/location/**")
                        .filters(f->f.filter(filter))
                        .uri("lb://location-service"))
                .route(p->p.path("/api/order/**")
                        .filters(f->f.filter(filter))
                        .uri("lb://order-service")
                .build();
    }

}
