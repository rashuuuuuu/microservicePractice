package com.rashmita.stock_service.controller;
import com.rashmita.stock_service.model.ServerResponse;
import com.rashmita.stock_service.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("stock")
@Controller
@RequiredArgsConstructor
public class StockContoller {
    private StockService stockService;

    @GetMapping("/get")
    public ServerResponse<?> getStocks() {
        return stockService.getAllStock();
    }
}
