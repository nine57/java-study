package com.example.study.ordersystem2.external;

import org.springframework.stereotype.Service;

@Service
public class Logger {
    public void logOrderRequest(String type, int amount, String userEmail) {
        System.out.println("[LOG] 주문 요청 - 타입: " + type + ", 금액: " + amount + ", 사용자: " + userEmail);
    }

    public void logDebug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    public void logWarning(String message) {
        System.out.println("[WARN] " + message);
    }

    public void logError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void logDiscount(int discount) {
        System.out.println("[LOG] 할인 적용: " + discount + "원");
    }
}