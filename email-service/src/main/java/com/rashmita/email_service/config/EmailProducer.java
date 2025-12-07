//package com.rashmita.email_service.config;
//
//import com.rashmita.base_domains.EmailEvent;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailProducer {
//    private static final Logger logger = LoggerFactory.getLogger(EmailProducer.class);
//    private NewTopic topic;
//    private KafkaTemplate<String, EmailEvent> kafkaTemplate;
//    public EmailProducer(NewTopic topic, KafkaTemplate<String, EmailEvent> kafkaTemplate) {
//        this.topic = topic;
//        this.kafkaTemplate = kafkaTemplate;
//    }
//    public void sendMessage(EmailEvent emailEvent) {
//        logger.info(String.format("Email event=>%s",emailEvent.toString()));
//        Message<EmailEvent> message = MessageBuilder
//                .withPayload(emailEvent)
//                .setHeader(KafkaHeaders.TOPIC,topic.name())
//                .build();
//        kafkaTemplate.send(message);
//    }
//}
