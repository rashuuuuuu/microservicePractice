package com.rashmita.order_service.controller;

import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.base_domains.model.ServerResponse;
import com.rashmita.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ServerResponse<?> createOrder(@Valid @RequestBody OrderEvent orderEvent) {
        return orderService.createOrder(orderEvent);
    }

    @GetMapping("/products")
    public ServerResponse<?> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size
    ) {
        return orderService.getAllProducts(page, size);
    }


}
