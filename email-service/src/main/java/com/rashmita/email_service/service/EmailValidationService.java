package com.rashmita.email_service.service;

import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.email_service.mapper.EmailMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailValidationService {
    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;
    private final EmailMapper emailMapper;

    @Value("${spring.mail.username}")
    private String sender;

    private static final Logger logger = LoggerFactory.getLogger(EmailValidationService.class);

    @KafkaListener(topics = "${spring.topic.stock}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event) {
        logger.info("Received event for email: {}", event.getEmail());
        if (isValidEmailAddress(event.getEmail())) {
            sendEmailWithVerificationLink(event);
        } else {
            logger.warn("Invalid email address received: {}", event.getEmail());
        }
    }

    public String sendEmailWithVerificationLink(OrderEvent event) {
        try {
            String email = event.getEmail();
            String subject = "Email Confirmation";

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject(subject);

            Map<String, Object> model = new HashMap<>();
            model.put("name", email);
            model.put("input", subject);

            Template template = freemarkerConfig.getTemplate("email-template.ftl");
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(htmlContent, true);

            // Use equals() for string comparison
            if ("stock_reserved".equals(event.getOrderStatus())) {
                javaMailSender.send(mimeMessage);
                emailMapper.saveEmail(event);
                logger.info("Email sent to {}", email);
                return "Mail Sent!";
            } else {
                logger.info("Order status is not 'stock_reserved', email not sent.");
                return "Email not sent: Order status is not stock_reserved.";
            }
        } catch (MailException | MessagingException | IOException | TemplateException e) {
            logger.error("Error sending email", e);
            return "Error in Sending Mail: " + e.getMessage();
        }
    }

    private boolean isValidEmailAddress(String email) {
        return email != null && email.contains("@");
    }
}
