package com.project.locationservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "geo-apify",url = "https://api.geoapify.com" )
public interface Geoapify {
    @GetMapping("/v1/geocode/reverse")
    Map<String, Object> getLocations(@RequestParam("apiKey") String api_key,@RequestParam("lat") double latitude, @RequestParam("lon") double longitude );}
