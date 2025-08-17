package com.example.study.order.model.domain;

import com.example.study.order.model.constant.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-08-07
 */

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public Order(User user) {
        this.user = user;
        this.status = OrderStatus.ORDERED;
    }
    
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    
    public void cancel() {
        this.status = OrderStatus.CANCELLED;
        this.orderItems.forEach(orderItem -> 
            orderItem.getProduct().increaseStock(orderItem.getQuantity())
        );
    }
    
    public int getTotalPrice() {
        return this.orderItems.stream()
                .mapToInt(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();
    }
}
