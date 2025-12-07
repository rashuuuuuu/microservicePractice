package com.rashmita.stock_service.service;

import com.rashmita.stock_service.model.ServerResponse;
import com.rashmita.stock_service.model.StockRequest;

public interface StockService {
    public ServerResponse getAllStock();
    public ServerResponse createStock(StockRequest stockRequest);
}
