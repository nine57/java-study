package com.example.study.ordersystem2.service;

import com.example.study.ordersystem2.model.OrderDto;
import com.example.study.ordersystem2.external.Logger;
import com.example.study.ordersystem2.external.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class GiftOrderProcessor implements OrderProcessorV2 {
    private final Logger logger;
    private final EmailSender emailSender;

    public GiftOrderProcessor(Logger logger, EmailSender emailSender) {
        this.logger = logger;
        this.emailSender = emailSender;
    }

    @Override
    public boolean support(String type) {
        return "gift".equals(type);
    }

    @Override
    public String process(OrderDto dto) {
        logger.logDebug("사은품 처리 시작");

        if (!dto.isVip()) {
            logger.logWarning("일반 회원은 사은품 제공 대상이 아닙니다.");
            return "[GIFT] 대상 아님";
        }

        emailSender.sendVipGiftNotification(dto.getUserEmail());
        return "[GIFT] 사은품 발송됨";
    }
} 