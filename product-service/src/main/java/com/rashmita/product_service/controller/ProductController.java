package com.rashmita.product_service.controller;
import com.rashmita.base_domains.exception.NotFoundException;
import com.rashmita.base_domains.model.ServerResponse;
import com.rashmita.product_service.model.ProductRequest;
import com.rashmita.product_service.model.ProductRequestCode;
import com.rashmita.product_service.model.ProductResponse;
import com.rashmita.product_service.model.ProductUpdateRequest;
import com.rashmita.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create")
    public ServerResponse<?> createProduct(@Valid @RequestBody ProductRequest productRequest) throws NotFoundException {
        return productService.createProduct(productRequest);
    }
    @PostMapping("/update")
    public ServerResponse<?> updateProduct(@Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
        return productService.updateProduct(productUpdateRequest);
    }
    @PostMapping("getByCode")
    public  ServerResponse<?> getProductByCode(@RequestBody ProductRequestCode productRequestCode) throws NotFoundException {
        return productService.getProductByCode(productRequestCode);
    }
    @PostMapping("delete")
    public ServerResponse<?> deleteProduct(@RequestBody ProductRequestCode productRequestCode) throws NotFoundException {
        return productService.deleteProduct(productRequestCode);
    }
    @GetMapping("getAll")
    public ServerResponse<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable);
    }

}
