package com.example.study.ordersystem.discount;

public class FixedDiscountPolicy implements discountPolicy{
    private final int discountAmount;

    public FixedDiscountPolicy(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public int applyDiscount(int price) {
        return Math.max(0, price - discountAmount);
    }
}
