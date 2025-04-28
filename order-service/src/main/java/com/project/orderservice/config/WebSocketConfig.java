package com.project.orderservice.config;

import com.project.orderservice.utils.MyWebSocketHandler;
import com.project.orderservice.utils.OrderSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/websocket");
        registry.addHandler(orderSocketHandler(),"/order");
    }

    @Bean
    public MyWebSocketHandler webSocketHandler() {
        return new MyWebSocketHandler();
    }

    @Bean
    public OrderSocket orderSocketHandler() {
        return new OrderSocket();
    }
}