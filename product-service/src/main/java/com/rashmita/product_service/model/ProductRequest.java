package com.rashmita.product_service.model;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String  productName;

    private String  productType;

    private Double  price;

    private Double  costPrice;

    private Integer stockQuantity;

}
