package com.rashmita.product_service.entity;

import com.rashmita.base_domains.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="product")
public class Product extends AbstractEntity {
    @Column(name="product_name",nullable = false)
    private String  productName;
    @Column(name="Product_type",nullable = false)
    private String  ProductType;
    @Column(name="sku",nullable = false)
    private String  sku;
    @Column(name ="price",nullable = false)
    private Double  price;
    @Column(name="cost_price")
    private Double  costPrice;
    @Column(name="stock_quantity",nullable = false)
    private Integer stockQuantity;
    @Column(name="status",nullable = false)
    private String  status;
    private Date createdAt;
    private Date updatedAt;
}
