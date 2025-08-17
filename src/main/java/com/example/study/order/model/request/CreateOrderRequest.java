package com.example.study.order.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateOrderRequest {
    
    @NotNull(message = "회원 ID는 필수입니다.")
    private Long userId;
    
    @NotEmpty(message = "주문 상품은 최소 1개 이상이어야 합니다.")
    @Valid
    private List<OrderItemRequest> orderItems;
    
    @Getter
    @NoArgsConstructor
    public static class OrderItemRequest {
        
        @NotNull(message = "상품 ID는 필수입니다.")
        private Long productId;
        
        @NotNull(message = "수량은 필수입니다.")
        @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
        private Integer quantity;
    }
}
