package com.project.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@FeignClient(name = "location-service" , url = "${LOCATION_SERVICE_URI:http://localhost}:8091")
public interface LocationFeign {
    @PostMapping("/api/location/{locationId}/{userId}")
    Map<String,Object> setLocation(@PathVariable("locationId") String locationId,@PathVariable("userId") UUID userId);

    @GetMapping("/api/location/{userId}")
    List<Object> getUserLocation(@PathVariable("userId") UUID userId);
}
