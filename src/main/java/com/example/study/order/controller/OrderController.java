package com.example.study.order.controller;

import com.example.study.common.model.GlobalResponse;
import com.example.study.order.model.request.CreateOrderRequest;
import com.example.study.order.model.dto.OrderDto;
import com.example.study.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    
    @PostMapping
    public GlobalResponse<Long> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Long orderId = orderService.createOrder(request);
        return GlobalResponse.success(orderId);
    }
    
    @PostMapping("/{orderId}/cancel")
    public GlobalResponse<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return GlobalResponse.success();
    }
    
    @GetMapping("/user/{userId}")
    public GlobalResponse<List<OrderDto>> getOrderHistory(@PathVariable Long userId) {
        List<OrderDto> orders = orderService.getOrderHistory(userId);
        return GlobalResponse.success(orders);
    }
}
