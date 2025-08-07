package com.example.study.order.repository;

import com.example.study.order.model.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-08-07
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
}
