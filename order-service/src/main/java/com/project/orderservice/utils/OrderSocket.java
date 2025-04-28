package com.project.orderservice.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class OrderSocket implements WebSocketHandler {

    private static WebSocketSession session = null;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // This method is called after the connection is established with the client.
        OrderSocket.session = session;
        System.out.println("Connected to websocket session id: " + session.getId());
        session.sendMessage(new TextMessage("Connect to order-service"));
        session.sendMessage(new TextMessage("Your session id is " + session.getId()));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // This method is called when a new WebSocket message is received.
        System.out.println("Message received: " + message.getPayload()+" from websocket session id: "+session.getId());
        session.sendMessage(new TextMessage("Thank you your data has been received"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // This method is called if there's an error in the WebSocket transport layer.
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // This method is called after the WebSocket connection is closed.
        OrderSocket.session = null;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
        // Indicates whether this handler can handle partial messages. If this flag is set to true, then a partial message will not be sent until it is complete.
    }

    public static WebSocketSession getSession() {
        return session;
    }
}
