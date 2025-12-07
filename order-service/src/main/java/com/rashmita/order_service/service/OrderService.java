package com.rashmita.order_service.service;

import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.base_domains.model.ServerResponse;

public interface OrderService {
    ServerResponse<?> createOrder(OrderEvent orderRequest);
    ServerResponse<?> getAllProducts(int page, int size);
}
