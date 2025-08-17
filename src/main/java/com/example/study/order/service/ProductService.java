package com.example.study.order.service;

import com.example.study.order.model.constant.ProductType;
import com.example.study.order.model.request.CreateOrderRequest;
import com.example.study.order.model.request.CreateProductRequest;
import com.example.study.order.model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    boolean supports(ProductType productType);
    Long createProduct(CreateProductRequest request);
    void updateProduct(Long id, Integer price, Integer stockQuantity);
    List<ProductDto> getAllProducts();
    void validateOrder(List<CreateOrderRequest.OrderItemRequest> items);
    void preProcessOrder(List<CreateOrderRequest.OrderItemRequest> items);
    void postProcessOrder(List<CreateOrderRequest.OrderItemRequest> items);
}
