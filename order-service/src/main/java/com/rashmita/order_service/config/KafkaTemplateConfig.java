package com.rashmita.order_service.config;

import com.rashmita.base_domains.model.OrderEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaTemplateConfig {
    @Bean
    public KafkaTemplate<String, OrderEvent> kafkaTemplate(ProducerFactory<String, OrderEvent> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
//        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "spring-kafka-tx-id");
//        return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    public KafkaTransactionManager<String, String> kafkaTransactionManager(
//            ProducerFactory<String, String> producerFactory) {
//        return new KafkaTransactionManager<>(producerFactory);
//    }
}
