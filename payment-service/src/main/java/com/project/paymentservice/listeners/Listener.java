package com.project.paymentservice.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Listener {
    @KafkaListener(topics = "order",groupId = "payment-service")
    public void receiveMessage(Map<String,Object> order) {
        System.out.println("Received message: " + order.toString());
    }
}
