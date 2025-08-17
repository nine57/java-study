package com.example.study.order.controller;

import com.example.study.common.model.GlobalResponse;
import com.example.study.order.model.constant.ProductType;
import com.example.study.order.model.request.CreateProductRequest;
import com.example.study.order.model.dto.ProductDto;
import com.example.study.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final List<ProductService> productServices;
    
    @PostMapping
    public GlobalResponse<Long> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductService productService = findProductService(request.getProductType());
        
        Long productId = productService.createProduct(request);
        return GlobalResponse.success(productId);
    }
    
    @PutMapping("/{id}")
    public GlobalResponse<Void> updateProduct(
            @PathVariable Long id,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) Integer stockQuantity) {
        for (ProductService service : productServices) {
            try {
                service.updateProduct(id, price, stockQuantity);
                return GlobalResponse.success();
            } catch (Exception e) {
                continue;
            }
        }
        
        throw new IllegalArgumentException("상품을 찾을 수 없거나 수정할 수 없습니다. ID: " + id);
    }
    
    @GetMapping
    public GlobalResponse<List<ProductDto>> getAllProducts() {
        List<ProductDto> allProducts = productServices.stream()
                .flatMap(service -> service.getAllProducts().stream())
                .collect(java.util.stream.Collectors.toList());
        
        return GlobalResponse.success(allProducts);
    }
    
    @GetMapping("/type/{productType}")
    public GlobalResponse<List<ProductDto>> getProductsByType(@PathVariable String productType) {
        ProductType type = ProductType.valueOf(productType.toUpperCase());
        ProductService productService = findProductService(type);
        
        List<ProductDto> products = productService.getAllProducts();
        return GlobalResponse.success(products);
    }
    
    private ProductService findProductService(ProductType productType) {
        return productServices.stream()
                .filter(service -> service.supports(productType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 상품 타입입니다: " + productType));
    }
}
