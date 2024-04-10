package com.project.locationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "open-route-service",url = "https://api.openrouteservice.org")
public interface OpenRouteService {
    @GetMapping("/geocode/reverse")
    Map<String, Object> getLocations(@RequestParam("api_key") String api_key,@RequestParam("point.lon") double longitude,@RequestParam("point.lat") double latitude);
}
