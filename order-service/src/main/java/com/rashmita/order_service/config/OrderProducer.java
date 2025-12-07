package com.rashmita.order_service.config;

import com.rashmita.base_domains.model.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProducer {
    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;
    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional("kafkaTransactionManager")
    public void sendMessage(OrderEvent orderEvent) {
        logger.info(String.format("Order event=>%s",orderEvent.toString()));
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}