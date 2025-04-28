package com.project.orderservice.utils;

import com.project.orderservice.modal.WaterOrder;
import org.springframework.web.socket.WebSocketMessage;

public class OrderMessage implements WebSocketMessage<WaterOrder> {

    private WaterOrder waterOrder;

    public OrderMessage(WaterOrder waterOrder) {
        this.waterOrder = waterOrder;
    }

    @Override
    public WaterOrder getPayload() {
        return waterOrder;
    }

    @Override
    public int getPayloadLength() {
        return 0;
    }

    @Override
    public boolean isLast() {
        return false;
    }
}
