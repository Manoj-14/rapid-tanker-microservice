package com.project.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "order-service" , url = "${ORDER_SERVICE_URI:http://localhost}:8084")
public interface OrderFeign {

    @PostMapping("/api/order")
    Map<String,Object> createOrder(Map<String,Object> request);
    @GetMapping("/api/order/{orderId}")
    Map<String,Object> getOrder(@PathVariable String orderId);
    @GetMapping("/api/order/{userId}")
    Map<String,Object> getUserOrders(@PathVariable  String userId);
}
