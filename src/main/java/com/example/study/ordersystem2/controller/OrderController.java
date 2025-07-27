package com.example.study.ordersystem2.controller;

import com.example.study.ordersystem2.model.OrderDto;
import com.example.study.ordersystem2.service.OrderProcessorV2;
import com.example.study.ordersystem2.external.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ❗ 이번 과제의 핵심은 "조건 분기를 제거하고, 다형성 기반으로 역할을 나누는 것"입니다.
 * 도메인 객체를 분리하는 구조도 가능하지만, 이번 과제에서는 서비스 계층을 중심으로 리팩토링하는 방향을 권장합니다.
 *
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-07-22
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    /**
     * 	Spring이 OrderProcessor 구현체들을 전부 Bean으로 등록하고 List로 주입해줌
     * 	supports() 메서드로 “내가 처리할 타입인지” 스스로 판단하게 함
     * 	조건 분기(if, switch) 없이 구현체가 알아서 선택됨
     * 	- 가이드 문서
     * 	<p>https://wjcompass.atlassian.net/wiki/spaces/WLDA/pages/503742489/OrderProcessor</p>
     */
    private final List<OrderProcessorV2> processors;
    private final Logger logger;

    @Autowired
    public OrderController(List<OrderProcessorV2> processors, Logger logger) {
        this.processors = processors;
        this.logger = logger;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping
    public String processOrder(@RequestBody OrderDto dto) {
        logger.logOrderRequest(dto.getType(), dto.getAmount(), dto.getUserEmail());
        
        try {
            return processors.stream()
                .filter(p -> p.support(dto.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 주문 타입"))
                .process(dto);
        } catch (IllegalArgumentException e) {
            logger.logError("알 수 없는 상품 유형: " + dto.getType());
            return e.getMessage();
        }
    }
}
