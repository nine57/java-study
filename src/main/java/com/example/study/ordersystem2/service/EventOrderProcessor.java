package com.example.study.ordersystem2.service;

import com.example.study.ordersystem2.model.OrderDto;
import com.example.study.ordersystem2.external.Logger;
import com.example.study.ordersystem2.external.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EventOrderProcessor implements OrderProcessorV2 {
    private final Logger logger;
    private final EmailSender emailSender;
    private final DiscountService discountService;

    public EventOrderProcessor(Logger logger, EmailSender emailSender, DiscountService discountService) {
        this.logger = logger;
        this.emailSender = emailSender;
        this.discountService = discountService;
    }

    @Override
    public boolean support(String type) {
        return "event".equals(type);
    }

    @Override
    public String process(OrderDto dto) {
        logger.logDebug("이벤트 상품 처리 시작");

        int discount = discountService.calculateEventDiscount(dto.isVip());
        int discountedAmount = discountService.applyDiscount(dto.getAmount(), discount);

        logger.logDiscount(discount);
        emailSender.sendEventDiscountNotification(dto.getUserEmail());

        return "[EVENT] 할인 후 금액: " + discountedAmount;
    }
}