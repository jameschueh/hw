package com.example.hw.repository;

import com.example.hw.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer> {
    ProductsEntity getProductByName(String name);
}

