//package com.rashmita.stock_service.service;
//
//import com.rashmita.stock_service.mapper.StockMapper;
//import com.rashmita.stock_service.model.ResponseUtility;
//import com.rashmita.stock_service.model.ServerResponse;
//import com.rashmita.stock_service.model.StockRequest;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StockServiceImpl implements StockService {
//
//    private StockMapper stockMapper;
//    @Override
//    public ServerResponse createStock(StockRequest stockRequest) {
//        stockMapper.createStock(stockRequest);
//        return  ResponseUtility.getSuccessfulServerResponse("create stock successfully");
//    }
//    public ServerResponse getAllStock(){
//        stockMapper.findAllStocks();
//        return ResponseUtility.getSuccessfulServerResponse("get all stocks");
//    }
//}
