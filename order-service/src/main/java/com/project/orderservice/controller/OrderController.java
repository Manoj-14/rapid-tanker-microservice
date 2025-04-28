package com.project.orderservice.controller;

import com.project.orderservice.modal.WaterOrder;
import com.project.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<WaterOrder> CreateOrder(WaterOrder waterOrder) {
        return new ResponseEntity<WaterOrder>(orderService.createOrder(waterOrder), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public  ResponseEntity<WaterOrder> getOrderById(@PathVariable String orderId) {
        WaterOrder waterOrder = orderService.findOrderByOrderId(orderId);
        return ResponseEntity.ok(waterOrder);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<?> getAllOrderFromUserId(@PathVariable String userId){
        List<WaterOrder> waterOrders = orderService.findOrderByUserId(userId);
        return ResponseEntity.ok(waterOrders);
    }
}
