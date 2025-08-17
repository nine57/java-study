package com.example.study.order.repository;

import com.example.study.order.model.constant.ProductType;
import com.example.study.order.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(ProductType productType);
}
