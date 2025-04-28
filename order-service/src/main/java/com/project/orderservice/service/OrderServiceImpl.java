package com.project.orderservice.service;

import com.project.orderservice.emitters.Emitters;
import com.project.orderservice.exceptions.OrderNotFoundException;
import com.project.orderservice.modal.WaterOrder;
import com.project.orderservice.repository.OrderRepository;
import com.project.orderservice.utils.OrderMessage;
import com.project.orderservice.utils.OrderSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Emitters emitters;

    @Autowired
    private OrderSocket orderSocket;


    @Override
    public WaterOrder createOrder(WaterOrder waterOrder) {
        WaterOrder savedWaterOrder = orderRepository.save(waterOrder);
        emitters.sendForPayment(savedWaterOrder);
        try {
            OrderSocket.getSession().sendMessage(new OrderMessage(savedWaterOrder));
        } catch (IOException e) {
            System.out.println("Error in sending message for websocket session id "+orderSocket.getSession().getId());
            throw new RuntimeException(e);
        }
        return savedWaterOrder;
    }

    @Override
    public WaterOrder updateOrder(WaterOrder waterOrder) {
        return null;
    }

    @Override
    public List<WaterOrder> findOrderByUserId(String userId) {
        return List.of();
    }

    @Override
    public WaterOrder findOrderByOrderId(String orderId) {
        WaterOrder waterOrder = orderRepository.findById(orderId).orElse(null);
        if (waterOrder == null) {
            throw new OrderNotFoundException(orderId);
        }
        else {
            return null;
        }
    }

    @Override
    public List<WaterOrder> findOrderByUserIdAndDate(String userId, String date) {
        return List.of();
    }
}
