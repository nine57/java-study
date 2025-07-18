package com.example.study.ordersystem.product;

import com.example.study.ordersystem.discount.discountPolicy;

/**
 * ✅ 미션: AbstractProduct를 상속받아 할인 로직 적용
 * - 가격 계산 시 할인 정책 적용
 * - getPrice() 오버라이드하여 할인된 가격 반환
 *
 * 🎯 목적:
 * - 상속을 통한 공통 필드 재사용
 * - 다형성으로 주문 처리 로직에 통합 가능해야 함
 */
public class DiscountedProduct extends AbstractProduct {
    private final discountPolicy discountPolicy;

    public DiscountedProduct(String name, int price, discountPolicy discountPolicy) {
        super(name, price);
        this.discountPolicy = discountPolicy;
    }

    @Override
    public int getPrice() {
        return discountPolicy.applyDiscount(super.getPrice());
    }

}
