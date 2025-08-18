package com.example.study.product.model.domain;

import com.example.study.product.model.constant.ProductType;
import com.example.study.order.model.domain.OrderItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer price;
    
    @Column(nullable = false)
    private Integer stockQuantity;
    
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Product(String name, Integer price, Integer stockQuantity, ProductType productType) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
    }
    
    public void decreaseStock(Integer quantity) {
        if (this.stockQuantity < quantity) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.stockQuantity -= quantity;
    }
    
    public void increaseStock(Integer quantity) {
        this.stockQuantity += quantity;
    }
}
