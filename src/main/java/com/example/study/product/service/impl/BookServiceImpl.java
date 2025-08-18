package com.example.study.product.service.impl;

import com.example.study.product.model.constant.ProductType;
import com.example.study.product.model.domain.Product;
import com.example.study.product.model.dto.ProductDto;
import com.example.study.product.model.request.CreateProductRequest;
import com.example.study.product.repository.ProductRepository;
import com.example.study.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    
    @Override
    public boolean supports(ProductType productType) {
        return ProductType.BOOK.equals(productType);
    }
    
    @Override
    @Transactional
    public Long createProduct(CreateProductRequest request) {
        Product product = Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .stockQuantity(request.getStockQuantity())
            .productType(ProductType.BOOK)
            .build();
        Product savedProduct = productRepository.save(product);
        log.info("도서 상품 생성 완료: {} (ID: {})", savedProduct.getName(), savedProduct.getId());
        return savedProduct.getId();
    }
    
    @Override
    @Transactional
    public void updateProduct(Long id, Integer price, Integer stockQuantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + id));
        log.info("도서 상품 수정 완료: {} (ID: {})", product.getName(), product.getId());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

}
