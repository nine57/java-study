package com.example.study.product.repository;

import com.example.study.product.model.constant.ProductType;
import com.example.study.product.model.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductType(ProductType productType);
}
