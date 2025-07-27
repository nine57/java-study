package com.example.study.ordersystem2.service;

import com.example.study.ordersystem2.model.OrderDto;
import com.example.study.ordersystem2.external.Logger;
import com.example.study.ordersystem2.external.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class NormalOrderProcessor implements OrderProcessorV2 {
    private final Logger logger;
    private final EmailSender emailSender;

    public NormalOrderProcessor(Logger logger, EmailSender emailSender) {
        this.logger = logger;
        this.emailSender = emailSender;
    }

    @Override
    public boolean support(String type) {
        return "normal".equals(type);
    }

    @Override
    public String process(OrderDto dto) {
        logger.logDebug("일반 상품 처리 시작");
        emailSender.sendOrderConfirmation(dto.getUserEmail());
        return "[NORMAL] 결제 금액: " + dto.getAmount();
    }
}