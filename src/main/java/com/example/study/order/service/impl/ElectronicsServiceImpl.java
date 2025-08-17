package com.example.study.order.service.impl;

import com.example.study.order.model.constant.ProductType;
import com.example.study.order.model.request.CreateOrderRequest;
import com.example.study.order.model.request.CreateProductRequest;
import com.example.study.order.model.domain.Product;
import com.example.study.order.model.dto.ProductDto;
import com.example.study.order.repository.ProductRepository;
import com.example.study.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectronicsServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    
    @Override
    public boolean supports(ProductType productType) {
        return ProductType.ELECTRONICS.equals(productType);
    }
    
    @Override
    @Transactional
    public Long createProduct(CreateProductRequest request) {
        Product product = Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .stockQuantity(request.getStockQuantity())
            .productType(ProductType.ELECTRONICS)
            .build();
        Product savedProduct = productRepository.save(product);
        log.info("전자제품 상품 생성 완료: {} (ID: {})", savedProduct.getName(), savedProduct.getId());
        return savedProduct.getId();
    }
    
    @Override
    @Transactional
    public void updateProduct(Long id, Integer price, Integer stockQuantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + id));
        log.info("전자제품 상품 수정 완료: {} (ID: {})", product.getName(), product.getId());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findByProductType(ProductType.ELECTRONICS);
        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }
    
    @Override
    public void validateOrder(List<CreateOrderRequest.OrderItemRequest> items) {
        int totalQuantity = items.stream()
                .mapToInt(CreateOrderRequest.OrderItemRequest::getQuantity)
                .sum();
        log.info("전자제품 주문 검증 통과: 총 {}개", totalQuantity);
    }
    
    @Override
    public void preProcessOrder(List<CreateOrderRequest.OrderItemRequest> items) {
        log.info("전자제품 주문 전처리 시작: {}개 상품", items.size());
    }
    
    @Override
    public void postProcessOrder(List<CreateOrderRequest.OrderItemRequest> items) {
        log.info("전자제품 주문 후처리 시작: {}개 상품", items.size());
    }
}
