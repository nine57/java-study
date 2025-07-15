package com.example.study.ordersystem.order;

import com.example.study.ordersystem.payment.PaymentMethod;

/**
 * ✅ 미션: 주문(Order)과 결제(PaymentMethod)를 연결하여 결제를 처리하시오.
 * - SRP: 주문 처리 외의 책임 분리
 * - DIP: 인터페이스를 통한 결제 방식 처리
 * - OCP: 새로운 결제 방식이나 주문 확장 시 기존 코드 변경이 없도록 구성
 */
public class OrderProcessor {
    public void process(Order order, PaymentMethod paymentMethod) {
    }
}
