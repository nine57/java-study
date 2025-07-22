package com.example.study.ordersystem2.service;

import com.example.study.ordersystem2.model.OrderDto;
import org.springframework.stereotype.Service;

/**
 * 🧠 생각해보기:
 * - 상품 종류가 더 늘어난다면 이 구조는 얼마나 유지보수에 유리할까요?
 * - 로그 출력, 할인 처리, 메시지 리턴 등 다양한 역할이 한 클래스에 몰려있지는 않나요?
 * - 추상화 방식이나 구현체 라우팅 방식에 제한은 없습니다.
 */
@Service
public class LegacyOrderProcessor implements OrderProcessor {
    @Override
    public String process(OrderDto dto) {
        String type = dto.getType();
        int amount = dto.getAmount();
        String userEmail = dto.getUserEmail(); // 고객 정보도 들어옴
        boolean vip = dto.isVip(); // VIP 여부

        // 로그
        System.out.println("[LOG] 주문 요청 - 타입: " + type + ", 금액: " + amount + ", 사용자: " + userEmail);

        if ("normal".equals(type)) {
            System.out.println("[DEBUG] 일반 상품 처리 시작");
            sendEmail(userEmail, "주문이 접수되었습니다.");
            return "[NORMAL] 결제 금액: " + amount;
        } else if ("event".equals(type)) {
            System.out.println("[DEBUG] 이벤트 상품 처리 시작");
            int discount = vip ? 2000 : 1000;
            int discounted = amount - discount;
            System.out.println("[LOG] 할인 적용: " + discount + "원");
            sendEmail(userEmail, "이벤트 상품 할인 적용 완료");
            return "[EVENT] 할인 후 금액: " + discounted;
        } else if ("gift".equals(type)) {
            System.out.println("[DEBUG] 사은품 처리 시작");
            if (!vip) {
                System.out.println("[WARN] 일반 회원은 사은품 제공 대상이 아닙니다.");
                return "[GIFT] 대상 아님";
            }
            sendEmail(userEmail, "VIP 사은품 발송 예정");
            return "[GIFT] 사은품 발송됨";
        } else {
            System.out.println("[ERROR] 알 수 없는 상품 유형: " + type);
            return "알 수 없는 상품 유형";
        }
    }

    private void sendEmail(String to, String message) {
        System.out.println("[EMAIL] To: " + to + ", Message: " + message);
        // 실제 이메일 전송 로직은 생략
    }
}