package com.example.study.product.model.request;

import com.example.study.product.model.constant.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateProductRequest {
    
    @NotBlank(message = "상품명은 필수입니다.")
    private String name;
    
    @NotNull(message = "가격은 필수입니다.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private Integer price;
    
    @NotNull(message = "재고 수량은 필수입니다.")
    @Min(value = 0, message = "재고 수량은 0 이상이어야 합니다.")
    private Integer stockQuantity;
    
    @NotNull(message = "상품 종류는 필수입니다.")
    private ProductType productType;
}
