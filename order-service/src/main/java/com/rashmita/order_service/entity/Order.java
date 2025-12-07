package com.rashmita.order_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractEntity {
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name="stock_quantity",nullable = false)
    private Integer stockQuantity;
    @Column(name = "qty", nullable = false)
    private int stock;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "mobile", nullable = false)
    private String mobile;
    @Column(name="status",nullable = false)
    private String status;
    @Column(name="order_id",nullable = false)
    private String orderId;
}
