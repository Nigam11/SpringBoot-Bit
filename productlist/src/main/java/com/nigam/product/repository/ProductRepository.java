package com.nigam.product.repository;

import jakarta.persistence.Entity;
import com.nigam.product.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
