package com.project.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "water-supplier-service" , url = "${WATER_SUPPLIER_SERVICE_URI:http://localhost}:8083")
public interface WaterSupplierFeign {
    @PostMapping("/api/supplier/create")
    Map<String,String> createAccount(@RequestParam("userId") UUID userId);
    @GetMapping("/api/supplier/{userId}")
    Map<String,Object> getSupplierAccount(@PathVariable("userId") UUID userId);
}
