package com.example.study.common.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * @author : 변영우 byw1666@baecomm.com
 * @since : 2025-04-08
 */
public abstract class CustomQuerydslRepositorySupport extends QuerydslRepositorySupport {

    protected final JPAQueryFactory queryFactory;

    public CustomQuerydslRepositorySupport(Class<?> domainClass, JPAQueryFactory queryFactory) {
        super(domainClass);
        this.queryFactory = queryFactory;
    }
}
