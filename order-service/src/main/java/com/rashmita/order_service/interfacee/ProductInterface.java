package com.rashmita.order_service.interfacee;

import com.rashmita.base_domains.model.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="product-service",url="http://localhost:8087")
public interface ProductInterface {
    @GetMapping("product/getAll")
    ServerResponse<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    }

