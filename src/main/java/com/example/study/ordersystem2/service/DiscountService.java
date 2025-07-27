package com.example.study.ordersystem2.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    
    public int calculateEventDiscount(boolean isVip) {
        return isVip ? 2000 : 1000;
    }
    
    public int applyDiscount(int originalAmount, int discountAmount) {
        return originalAmount - discountAmount;
    }
} 