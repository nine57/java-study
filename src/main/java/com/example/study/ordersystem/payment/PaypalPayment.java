package com.example.study.ordersystem.payment;

/**
 * ✅ 미션: PaymentMethod를 구현하여 PayPal 결제 방식을 추가하시오.
 * - 기존 OrderProcessor를 수정하지 않고 적용할 수 있어야 함
 */
public class PaypalPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("[페이팔 결제] " + amount + "원이 결제되었습니다");
    }
}
