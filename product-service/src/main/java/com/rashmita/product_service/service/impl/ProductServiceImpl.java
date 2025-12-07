package com.rashmita.product_service.service.impl;
import com.rashmita.base_domains.exception.NotFoundException;
import com.rashmita.base_domains.model.PagingResponse;
import com.rashmita.base_domains.model.ResponseUtility;
import com.rashmita.base_domains.model.ServerResponse;
import com.rashmita.product_service.entity.Product;
import com.rashmita.product_service.mapper.ProductMapper;
import com.rashmita.product_service.model.ProductRequest;
import com.rashmita.product_service.model.ProductRequestCode;
import com.rashmita.product_service.model.ProductResponse;
import com.rashmita.product_service.model.ProductUpdateRequest;
import com.rashmita.product_service.repository.ProductRepository;
import com.rashmita.product_service.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ServerResponse createProduct(ProductRequest productRequest){
        productMapper.createProduct(productRequest);
        return ResponseUtility.getSuccessfulServerResponse("product created successfully");
    }

    @Override
    public ServerResponse updateProduct(ProductUpdateRequest productUpdateRequest) {
    productMapper.updateProduct(productUpdateRequest);
    return ResponseUtility.getSuccessfulServerResponse("product updated successfully");
    }

    @Override
    public ServerResponse deleteProduct(ProductRequestCode productRequestCode) {
     productMapper.deleteProduct(productRequestCode);
     return ResponseUtility.getSuccessfulServerResponse("product deleted successfully");
    }

    @Override
    public ServerResponse getProductByCode(ProductRequestCode productRequestCode) throws NotFoundException {
      productMapper.getProductByCode(productRequestCode);
      return ResponseUtility.getSuccessfulServerResponse("product found successfully");
    }

    @Override
    public ServerResponse<?> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll( pageable);
        List<ProductResponse> productResponses = productPage.stream()
                .map(product -> modelMapper.map(product,ProductResponse.class))
                .collect(Collectors.toList());

        return ResponseUtility.getSuccessfulServerResponse(
                new PagingResponse<>(
                        productResponses,
                        productPage.getTotalPages(),
                        productPage.getTotalElements(),
                        productPage.getSize(),
                        productPage.getNumber(),
                        productPage.isEmpty()
                ), "All products Found");
    }

}
