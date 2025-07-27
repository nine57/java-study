package com.example.study.ordersystem2.service;

import com.example.study.ordersystem2.model.OrderDto;

/**
 * 	구현체에 supports(type) 메서드 있는 이유 → 분기 대신 이걸로 판단하기 위함
 * 	@Component 또는 @Service로 구현체 Bean 등록되어야 List<OrderProcessor> 주입됨
 * 	Spring이 알아서 모든 구현체를 모아서 넣어줌 (Magic X → DI의 기본 동작)
 */
public interface OrderProcessorV2 {
    boolean support(String type);
    String process(OrderDto dto);
}