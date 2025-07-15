package com.example.study.ordersystem.product;


/**
 * ✅ 미션: 다양한 상품 유형에 대한 공통 인터페이스를 정의하시오.
 * - 이름, 가격 등의 정보를 제공하는 메서드를 선언
 * - OCP, LSP 적용을 고려한 구조 설계
 */
public interface Product {
    String getName();
    int getPrice();
}
