package com.rashmita.product_service.mapper;
import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.product_service.entity.Product;
import com.rashmita.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Date;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductConsume {

    private final ProductRepository productRepository;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        log.info("Consumed OrderEvent: {}", orderEvent);
        updateProductFromOrder(orderEvent);
    }

    private void updateProductFromOrder(OrderEvent orderEvent) {
        String sku = orderEvent.getSku();
        Product product = productRepository.getProductBySku(sku);

        if (product != null) {
            product.setPrice(orderEvent.getPrice());
            product.setUpdatedAt(new Date());
            product.setProductType(orderEvent.getProductType());
            product.setStockQuantity(deductQuantity(orderEvent));
            product.setProductName(orderEvent.getProductName());
            product.setStatus("product updated");
            log.info("Product updated for SKU: {}", sku);

            productRepository.save(product);
        } else {
            log.warn("Product with SKU {} not found", sku);
        }
    }

    private int deductQuantity(OrderEvent orderEvent) {
        return productRepository.findBySku(orderEvent.getSku())
                .map(product -> product.getStockQuantity() - orderEvent .getStockQuantity())
                .orElse(0);
    }
}
