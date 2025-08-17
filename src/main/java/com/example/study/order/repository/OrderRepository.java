package com.example.study.order.repository;

import com.example.study.order.model.constant.OrderStatus;
import com.example.study.order.model.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-08-07
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_Id(Long userId);
    List<Order> findByStatus(OrderStatus orderStatus);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
