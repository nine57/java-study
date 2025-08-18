package com.example.study.order.service.impl;

import com.example.study.order.model.request.CreateOrderRequest;
import com.example.study.order.model.domain.Order;
import com.example.study.order.model.domain.OrderItem;
import com.example.study.order.model.dto.OrderDto;
import com.example.study.order.repository.OrderRepository;
import com.example.study.order.service.OrderService;
import com.example.study.product.model.constant.ProductType;
import com.example.study.product.model.domain.Product;
import com.example.study.product.repository.ProductRepository;
import com.example.study.product.service.ProductService;
import com.example.study.user.model.domain.User;
import com.example.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final List<ProductService> productServices;

    private ProductService findProductService(ProductType productType) {
        return productServices.stream()
                .filter(service -> service.supports(productType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 상품 타입입니다: " + productType));
    }

    @Override
    @Transactional
    public Long createOrder(CreateOrderRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + request.getUserId()));
        
        Order order = new Order(user);
        
        Map<ProductType, List<CreateOrderRequest.OrderItemRequest>> productTypeGroups = request.getOrderItems().stream()
                .collect(Collectors.groupingBy(itemRequest -> {
                    Product product = productRepository.findById(itemRequest.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + itemRequest.getProductId()));
                    return product.getProductType();
                }));
        
        log.info("주문 상품 타입별 분류: {}", productTypeGroups.keySet());
        
        for (Map.Entry<ProductType, List<CreateOrderRequest.OrderItemRequest>> entry : productTypeGroups.entrySet()) {
            ProductType productType = entry.getKey();
            List<CreateOrderRequest.OrderItemRequest> items = entry.getValue();
            
            log.info("{} 타입 상품 처리 시작: {}개", productType, items.size());
            for (CreateOrderRequest.OrderItemRequest itemRequest : items) {
                Product product = productRepository.findById(itemRequest.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + itemRequest.getProductId()));
                product.decreaseStock(itemRequest.getQuantity());
                OrderItem orderItem = new OrderItem(product, product.getPrice(), itemRequest.getQuantity());
                order.addOrderItem(orderItem);
                
                log.info("{} 타입 상품 주문: {} ({}), 수량: {}, 가격: {}", 
                    productType, product.getName(), product.getProductType(), itemRequest.getQuantity(), product.getPrice());
            }
        }
        
        Order savedOrder = orderRepository.save(order);
        log.info("주문 완료: 주문 ID: {}, 상품 타입 수: {}, 총 상품 수: {}", 
            savedOrder.getId(), productTypeGroups.size(), request.getOrderItems().size());
        
        return savedOrder.getId();
    }
    
    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다. ID: " + orderId));
        order.cancel();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrderHistory(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }
}
