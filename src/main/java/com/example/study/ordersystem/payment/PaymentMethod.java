package com.example.study.ordersystem.payment;

/**
 * ✅ 미션: 결제 방식에 대한 공통 인터페이스를 정의하시오.
 * - pay(int amount): 결제 실행 메서드
 *
 * 🎯 목표:
 * - DIP: OrderProcessor가 구체 결제 방식에 의존하지 않도록 하기
 * - OCP: 새로운 결제 방식 추가 시 OrderProcessor 수정 없이 적용되도록 구성
 */
public interface PaymentMethod {
    void pay(int amount);
}
