package com.backend.repository;

import com.backend.entity.ProductInOrOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInOrOutRepository extends JpaRepository<ProductInOrOut, Long> {
    List<ProductInOrOut> findByActive(boolean active);
}