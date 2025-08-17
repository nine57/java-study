package com.example.study.order.model.dto;

import com.example.study.order.model.constant.ProductType;
import com.example.study.order.model.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;
    private ProductType productType;
    
    public static ProductDto from(Product product) {
        return new ProductDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStockQuantity(),
            product.getProductType()
        );
    }
}
