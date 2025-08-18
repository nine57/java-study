package com.example.study.product.service;

import com.example.study.product.model.constant.ProductType;
import com.example.study.product.model.dto.ProductDto;
import com.example.study.product.model.request.CreateProductRequest;

import java.util.List;

public interface ProductService {
    boolean supports(ProductType productType);
    Long createProduct(CreateProductRequest request);
    void updateProduct(Long id, Integer price, Integer stockQuantity);
    List<ProductDto> getAllProducts();
}
