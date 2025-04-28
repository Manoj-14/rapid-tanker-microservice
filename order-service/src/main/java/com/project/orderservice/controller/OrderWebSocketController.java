package com.project.orderservice.controller;

import jakarta.websocket.OnOpen;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

@RestController
public class OrderWebSocketController {

    private String webSocketSessionId;

    @OnOpen
    public void onOpen(WebSocketSession session) {
        this.webSocketSessionId = session.getId();
    }

    @MessageMapping("/websocket")
    @SendTo("/message")
    public String message(String message) {
        System.out.println("Message received: " + message);
        return "Message  "+ message +" received at " + webSocketSessionId;
    }
}
