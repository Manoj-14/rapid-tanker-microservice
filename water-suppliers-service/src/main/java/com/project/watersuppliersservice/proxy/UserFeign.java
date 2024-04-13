package com.project.watersuppliersservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "user-service" , url = "${USER_SERVICE_URI:http://localhost}:8080")
public interface UserFeign {
    @PutMapping("/{userId}")
    public void setAccountSetUpStatus(@PathVariable("userId") UUID userId, @RequestParam("status") boolean status);
}
