package com.example.study.order.service;

import com.example.study.order.model.request.CreateOrderRequest;
import com.example.study.order.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Long createOrder(CreateOrderRequest request);
    void cancelOrder(Long orderId);
    List<OrderDto> getOrderHistory(Long userId);
}
