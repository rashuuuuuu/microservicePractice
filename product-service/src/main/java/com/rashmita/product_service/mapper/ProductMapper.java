package com.rashmita.product_service.mapper;

import com.rashmita.base_domains.exception.NotFoundException;
import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.product_service.entity.Product;
import com.rashmita.product_service.model.ProductRequest;
import com.rashmita.product_service.model.ProductRequestCode;
import com.rashmita.product_service.model.ProductResponse;
import com.rashmita.product_service.model.ProductUpdateRequest;
import com.rashmita.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductMapper {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setSku(UUID.randomUUID().toString());
        product.setCreatedAt(new Date());
        product.setStatus("product created");
        product.setProductType(productRequest.getProductType());
        product.setCostPrice(productRequest.getCostPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        productRepository.save(product);
    }

    public void updateProduct(ProductUpdateRequest productUpdateRequest) {
        String sku = productUpdateRequest.getSku();
        Product product = productRepository.getProductBySku(sku);
        if (sku != null) {
            product.setPrice(productUpdateRequest.getPrice());
            product.setUpdatedAt(new Date());
            product.setCostPrice(productUpdateRequest.getCostPrice());
            product.setProductType(productUpdateRequest.getProductType());
            product.setStockQuantity(productUpdateRequest.getStockQuantity());
            product.setProductName(productUpdateRequest.getProductName());
            product.setStatus("product updated");
        }
        productRepository.save(product);
    }

    public void getProductByCode(ProductRequestCode productRequestCode) throws NotFoundException {
        Product product = productRepository.findBySku(productRequestCode)
                .orElseThrow(() -> new NotFoundException("product not found"));

        if (product.getStatus().equals("deleted") || product.getSku() == null) {
            throw new NotFoundException("product is inactive or missing bank code");
        }
        modelMapper.map(product, ProductResponse.class);
    }


    public void deleteProduct(ProductRequestCode productRequestCode) {
        Optional<Product> productOptional = productRepository.findBySku(productRequestCode);

        if (productOptional.isPresent()) {
            Product foundBank = productOptional.get();
            foundBank.setStatus("deleted");
            productRepository.save(foundBank);
        } else {
            System.out.println("Product does not exist");
        }
    }


}
