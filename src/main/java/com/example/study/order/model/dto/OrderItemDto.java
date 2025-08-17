package com.example.study.order.model.dto;

import com.example.study.order.model.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private Integer price;
    private Integer quantity;
    
    public static OrderItemDto from(OrderItem orderItem) {
        return new OrderItemDto(
            orderItem.getId(),
            orderItem.getProduct().getId(),
            orderItem.getProduct().getName(),
            orderItem.getPrice(),
            orderItem.getQuantity()
        );
    }
}
