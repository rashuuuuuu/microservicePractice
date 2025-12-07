package com.rashmita.product_service.service;

import com.rashmita.base_domains.model.ServerResponse;
import com.rashmita.product_service.model.ProductRequest;
import com.rashmita.base_domains.exception.NotFoundException;
import com.rashmita.product_service.model.ProductRequestCode;
import com.rashmita.product_service.model.ProductUpdateRequest;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ServerResponse createProduct(ProductRequest productRequest)throws NotFoundException;
    ServerResponse updateProduct(ProductUpdateRequest productUpdateRequest);
    ServerResponse deleteProduct(ProductRequestCode productRequestCode)throws NotFoundException;
    ServerResponse getProductByCode(ProductRequestCode productRequestCode) throws NotFoundException;
    ServerResponse getAllProducts(Pageable pageable);


}
