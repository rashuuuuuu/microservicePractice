package com.rashmita.email_service.mapper;
import com.rashmita.base_domains.model.OrderEvent;
import com.rashmita.email_service.entity.Email;
import com.rashmita.email_service.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailMapper {
    @Autowired
    private EmailRepository emailRepository;
    public String saveEmail(OrderEvent orderEvent) {
        Email email = new Email();
        email.setSendFrom("${spring.mail.username}");
        email.setSendTo(orderEvent.getEmail());
        email.setCreatedAt(new Date());
        emailRepository.save(email);
        return ("email_success");
    }
}
