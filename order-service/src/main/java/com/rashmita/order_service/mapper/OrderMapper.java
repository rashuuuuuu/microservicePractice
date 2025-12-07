package com.rashmita.order_service.mapper;
import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.order_service.entity.Order;
import com.rashmita.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.net.SocketException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
//@Slf4j
public class OrderMapper {
    @Autowired
    private OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Transactional
//    @Idempotent(
//            keyExpression = "#event.orderId", // EL expression for uniqueness
//            persistenceStore = "dynamoDbPersistenceStore"
//    )
    @Retryable(
            value = { TimeoutException.class, SocketException.class },
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public String createOrder(OrderEvent orderRequest) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setProductName(orderRequest.getProductName());
        order.setEmail(orderRequest.getEmail());
        order.setStockQuantity(orderRequest.getStockQuantity());
        order.setLocation(orderRequest.getLocation());
        order.setMobile(orderRequest.getMobile());
        order.setStatus("created");
        orderRepository.save(order);
//        System.out.println(10/0);
        orderRequest.setOrderId(order.getOrderId());
        kafkaTemplate.send("order_topics", orderRequest);
        return ("order-created");
    }

}
