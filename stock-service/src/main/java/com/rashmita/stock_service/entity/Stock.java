package com.rashmita.stock_service.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="stock")
public class Stock extends AbstractEntity{
    @Column(name="order_id")
    private String orderId;
    @Column(name="product_name")
    private String productName;
    @Column(name="qty")
    private int qty;
    @Column(name="status")
    private String status;
}
