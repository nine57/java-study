package com.example.study.order.model.domain;

import com.example.study.product.model.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orderItems")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;
    
    @Column(nullable = false)
    private Integer price;  // 주문 시점의 가격
    
    @Column(nullable = false)
    private Integer quantity;

    @Builder
    public OrderItem(Product product, Integer price, Integer quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
}
