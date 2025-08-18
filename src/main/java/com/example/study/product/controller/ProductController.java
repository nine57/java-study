package com.example.study.product.controller;

import com.example.study.common.model.GlobalResponse;
import com.example.study.product.model.constant.ProductType;
import com.example.study.product.model.domain.Product;
import com.example.study.product.model.dto.ProductDto;
import com.example.study.product.model.request.CreateProductRequest;
import com.example.study.product.model.request.UpdateProductRequest;
import com.example.study.product.repository.ProductRepository;
import com.example.study.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final List<ProductService> productServices;
    private final ProductRepository productRepository;

    private ProductService findProductService(ProductType productType) {
        return productServices.stream()
                .filter(service -> service.supports(productType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 상품 타입입니다: " + productType));
    }

    @PostMapping
    public GlobalResponse<Long> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductService productService = findProductService(request.getProductType());
        
        Long productId = productService.createProduct(request);
        return GlobalResponse.success(productId);
    }
    
    @PutMapping("/{id}")
    public GlobalResponse<Void> updateProduct(
        @PathVariable Long id,
        @Valid @RequestBody UpdateProductRequest request
    ) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. Id=" + id));
            ProductService productService = findProductService(product.getProductType());
            productService.updateProduct(id, request.getPrice(), request.getStockQuantity());
            return GlobalResponse.success();
        } catch (IllegalArgumentException e) {
            return GlobalResponse.fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping
    public GlobalResponse<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productServices.stream()
                .flatMap(service -> service.getAllProducts().stream())
                .collect(java.util.stream.Collectors.toList());
        
        return GlobalResponse.success(allProducts);
    }
}
