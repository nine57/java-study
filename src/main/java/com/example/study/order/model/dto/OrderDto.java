package com.example.study.order.model.dto;

import com.example.study.order.model.constant.OrderStatus;
import com.example.study.order.model.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private String userName;
    private OrderStatus status;
    private Integer totalPrice;
    private List<OrderItemDto> orderItems;
    
    public static OrderDto from(Order order) {
        return new OrderDto(
            order.getId(),
            order.getUser().getId(),
            order.getUser().getName(),
            order.getStatus(),
            order.getTotalPrice(),
            order.getOrderItems().stream()
                .map(OrderItemDto::from)
                .collect(Collectors.toList())
        );
    }
}
