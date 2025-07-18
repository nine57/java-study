package com.example.study.ordersystem.product;

/**
 * ✅ 미션: AbstractProduct를 상속받아 일반 상품 구현
 * - 추가 로직 없이 기본 기능만 사용
 */
public class NormalProduct extends AbstractProduct {
    public NormalProduct(String name, int price) {
        super(name, price);
    }
}
