package com.example.study.ordersystem.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.study.ordersystem.product.Product;

/**
 * ✅ 미션: 여러 상품을 담을 수 있는 주문(Order) 객체를 구현하시오.
 * - 상품 추가 기능 (addProduct)
 * - 총 금액 계산 기능 (getTotalAmount)
 * - 캡슐화 적용 (필드 보호, getter 최소화)
 */
public class Order {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Product product : products) {
            totalAmount += product.getPrice();
        }
        return totalAmount;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
