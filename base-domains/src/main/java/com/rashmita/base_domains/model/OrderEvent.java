package com.rashmita.base_domains.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
    private String email;
    private String mobile;
    private String location;
    private String orderId;
    private String orderStatus;
    private String sku;
    private int stockQuantity;
    private Double price;
    private String productType;
    private String productName;


}
