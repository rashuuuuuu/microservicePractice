package com.rashmita.base_domains.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockEvent {
    private int qty;
    private String status;
    private String email;
}
