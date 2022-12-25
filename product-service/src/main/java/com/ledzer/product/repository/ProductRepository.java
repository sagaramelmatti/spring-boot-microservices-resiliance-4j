package com.ledzer.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ledzer.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
