package com.project.locationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "open-street-map",url = "https://nominatim.openstreetmap.org")
public interface OpenStreetMap {

    @GetMapping("/reverse")
    Map<String,Object> getLocation(@RequestParam("lat") double latitude,@RequestParam("lon") double longitude,@RequestParam("format") String format);
}
