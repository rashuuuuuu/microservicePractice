package com.rashmita.stock_service.mapper;

import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.stock_service.entity.Stock;
import com.rashmita.stock_service.model.StockResponse;
import com.rashmita.stock_service.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockMapper {
    @Autowired
    private StockRepository stockRepository;
    private ModelMapper modelMapper;
    private final KafkaTemplate<String,OrderEvent> kafkaTemplate;

    public List<StockResponse> findAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map((stock) -> modelMapper.map(stock, StockResponse.class)).collect(Collectors.toList());
    }
    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        log.info("Consumed OrderEvent: {}", orderEvent);
        createStock(orderEvent);
    }
    public void createStock(OrderEvent orderEvent) {
        Stock stock = new Stock();
        stock.setOrderId(orderEvent.getOrderId());
        stock.setProductName(orderEvent.getProductName());
        stock.setQty(orderEvent.getStockQuantity());
        stock.setStatus("stock_reserved");
        stockRepository.save(stock);
        orderEvent.setOrderStatus("stock_reserved");
        log.info("Stock saved for orderId: {}", orderEvent.getOrderId());
        kafkaTemplate.send("stock-topic", orderEvent.getOrderId(), orderEvent);
    }
}