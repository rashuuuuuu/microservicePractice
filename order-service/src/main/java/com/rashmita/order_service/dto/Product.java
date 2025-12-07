package com.rashmita.order_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String  name;

    private String  type;

    private String  sku;

    private Double  price;
}


