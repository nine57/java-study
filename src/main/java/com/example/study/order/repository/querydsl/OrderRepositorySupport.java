package com.example.study.order.repository.querydsl;

import com.example.study.common.querydsl.CustomQuerydslRepositorySupport;
import com.example.study.order.model.constant.OrderStatus;
import com.example.study.order.model.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.example.study.order.model.domain.QOrder.*;

/**
 * @author : 변영우 byw1666@wjcompass.com
 * @since : 2025-08-07
 */

@Repository
public class OrderRepositorySupport extends CustomQuerydslRepositorySupport {

    public OrderRepositorySupport(JPAQueryFactory queryFactory) {
        super(Order.class, queryFactory);
    }

    public Order findByName(OrderStatus status) {
        return queryFactory
                .selectFrom(order)
                .where(order.status.eq(status))
                .fetchOne();
    }
}
