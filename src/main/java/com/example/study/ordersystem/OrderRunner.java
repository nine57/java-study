package com.example.study.ordersystem;

import com.example.study.ordersystem.discount.DiscountPolicy;
import com.example.study.ordersystem.discount.FixedDiscountPolicy;
import com.example.study.ordersystem.discount.RateDiscountPolicy;
import com.example.study.ordersystem.order.Order;
import com.example.study.ordersystem.order.OrderProcessor;
import com.example.study.ordersystem.payment.CreditCardPayment;
import com.example.study.ordersystem.payment.PaymentMethod;
import com.example.study.ordersystem.payment.PaypalPayment;
import com.example.study.ordersystem.product.DiscountedProduct;
import com.example.study.ordersystem.product.NormalProduct;
import com.example.study.ordersystem.product.Product;
import com.example.study.ordersystem.product.SubscriptionProduct;

/**
 * ✅ 미션: 다양한 상품과 결제 조합으로 주문을 생성하고 처리하시오.
 * - 조건문 없이 다형성을 통해 유연하게 동작하도록 구성
 * - 실행 예제는 팀원이 참고할 수 있도록 구성
 */
public class OrderRunner {
    public static void main(String[] args) {
        // 할인 정책
        DiscountPolicy rateDiscountPolicy10 = new RateDiscountPolicy(10);
        DiscountPolicy fixedDiscountPolicy100 = new FixedDiscountPolicy(100);

        // 상품
        Product normalProduct = new NormalProduct("기본 상품", 1000);
        System.out.println(normalProduct.getName() + ": " + normalProduct.getPrice() + "원");
        Product discountedProduct = new DiscountedProduct("할인 상품", 1000, new DiscountPolicy[]{rateDiscountPolicy10});
        System.out.println(discountedProduct.getName() + ": " + discountedProduct.getPrice() + "원");
        Product discountedProduct2 = new DiscountedProduct("할인 상품 2", 1000, new DiscountPolicy[]{rateDiscountPolicy10, fixedDiscountPolicy100});
        System.out.println(discountedProduct2.getName() + ": " + discountedProduct2.getPrice() + "원");
        Product subscriptionProduct = new SubscriptionProduct("구독 상품", 1000, 6);
        System.out.println(subscriptionProduct.getName() + ": " + subscriptionProduct.getPrice() + "원");

        // 주문
        Order order1 = new Order();
        order1.addProduct(normalProduct);

        Order order2 = new Order();
        order2.addProduct(normalProduct);
        order2.addProduct(discountedProduct);

        Order order3 = new Order();
        order3.addProduct(discountedProduct2);

        Order order4 = new Order();
        order4.addProduct(subscriptionProduct);

        // 결제 수단
        PaymentMethod creditCardPayment = new CreditCardPayment();
        PaymentMethod paypalPayment = new PaypalPayment();

        // 주문 결제 처리
        OrderProcessor orderProcessor = new OrderProcessor();

        orderProcessor.process(order1, creditCardPayment);
        orderProcessor.process(order2, paypalPayment);
        orderProcessor.process(order3, creditCardPayment);
        orderProcessor.process(order4, paypalPayment);
    }
}
