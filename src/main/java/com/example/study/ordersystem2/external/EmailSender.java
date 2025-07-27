package com.example.study.ordersystem2.external;

import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    public void sendOrderConfirmation(String userEmail) {
        System.out.println("[EMAIL] To: " + userEmail + ", Message: 주문이 접수되었습니다.");
    }

    public void sendEventDiscountNotification(String userEmail) {
        System.out.println("[EMAIL] To: " + userEmail + ", Message: 이벤트 상품 할인 적용 완료");
    }

    public void sendVipGiftNotification(String userEmail) {
        System.out.println("[EMAIL] To: " + userEmail + ", Message: VIP 사은품 발송 예정");
    }
}