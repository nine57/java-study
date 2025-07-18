package com.example.study.ordersystem.discount;

public class RateDiscountPolicy implements discountPolicy{
    private final int discountRate;

    public RateDiscountPolicy(int discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public int applyDiscount(int price) {
        return Math.max(0, price - (price * discountRate / 100));
    }
}
