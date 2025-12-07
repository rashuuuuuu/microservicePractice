package com.rashmita.product_service.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    private String productName;
    private String ProductType;
    private String sku;
    private Double price;
    private int stockQuantity;
    private Double costPrice;
    private String status;
}
