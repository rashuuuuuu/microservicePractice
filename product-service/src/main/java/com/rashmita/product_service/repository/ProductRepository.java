package com.rashmita.product_service.repository;

import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.product_service.entity.Product;
import com.rashmita.product_service.model.ProductRequestCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductBySku(String sku);

    Optional<Product> findBySku(ProductRequestCode productRequestCode);

    Optional<Product> findBySku(String sku);
}
