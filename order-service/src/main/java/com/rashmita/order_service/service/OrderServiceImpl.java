package com.rashmita.order_service.service;

import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.base_domains.model.ResponseUtility;
import com.rashmita.base_domains.model.ServerResponse;
import com.rashmita.order_service.dto.Product;
import com.rashmita.order_service.dto.ProductPage;
import com.rashmita.order_service.interfacee.ProductInterface;
import com.rashmita.order_service.mapper.OrderMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
@Autowired
private OrderMapper orderMapper;
private final ProductInterface productInterface;

    public OrderServiceImpl(ProductInterface productInterface) {
        this.productInterface = productInterface;
    }
    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "getAllProductsFallback")
    public ServerResponse<?> getAllProducts(int page, int size) {
        return productInterface.getAllProducts(page, size);
    }

    // Fallback method must match params + Throwable at the end
    public ServerResponse<ProductPage> getAllProductsFallback(int page, int size, Throwable t) {
        List<Product> dummyProducts = List.of(
                new Product("shirt", "cloth", "11d11367-981e-4d67-bfac-2598c0efba37", 200.0),
                new Product("shirt", "cloth", "cfd4f5aa-2b1b-4d43-9ab5-d545be0578b6", 200.0)
        );

        ProductPage pageData = new ProductPage(dummyProducts, 1, 2, 10, 1, false);

        return ServerResponse.<ProductPage>builder()
                .httpStatus(HttpStatus.OK)
                .success(true)
                .message("Product Service unavailable — showing fallback products")
                .data(pageData)
                .statusCode(String.valueOf(HttpStatus.OK.value()))
                .build();
    }


    @Override
    public ServerResponse<?> createOrder(OrderEvent orderRequest) {
      orderMapper.createOrder(orderRequest);
      return ResponseUtility.getSuccessfulServerResponse("order created successfully");
    }
}
