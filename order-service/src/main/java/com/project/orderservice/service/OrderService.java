package com.project.orderservice.service;

import com.project.orderservice.exceptions.OrderNotFoundException;
import com.project.orderservice.modal.WaterOrder;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional
    public WaterOrder createOrder(WaterOrder waterOrder);
    @Transactional
    public WaterOrder updateOrder(WaterOrder waterOrder);
    public List<WaterOrder> findOrderByUserId(String userId);
    public WaterOrder findOrderByOrderId(String orderId) throws OrderNotFoundException;
    public List<WaterOrder> findOrderByUserIdAndDate(String userId, String date);
}
