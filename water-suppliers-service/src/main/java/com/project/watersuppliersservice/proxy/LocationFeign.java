package com.project.watersuppliersservice.proxy;

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
    @PostMapping("/api/location/add/{userId}")
    Map<String,Object> setLocation(@PathVariable("userId") UUID userId,@RequestParam("lon") double longitude, @RequestParam("lat") double latitude);

    @GetMapping("/api/location/{userId}")
    List<Object> getUserLocation(@PathVariable("userId") UUID userId);
}
