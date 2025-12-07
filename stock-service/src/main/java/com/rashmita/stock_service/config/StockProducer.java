package com.rashmita.stock_service.config;
import com.rashmita.base_domains.model.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class StockProducer {
    private static final Logger logger = LoggerFactory.getLogger(StockProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    public StockProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(OrderEvent stockEvent) {
        logger.info(String.format("Stock event=>%s",stockEvent.toString()));
        Message<OrderEvent> message = MessageBuilder
                .withPayload(stockEvent)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
