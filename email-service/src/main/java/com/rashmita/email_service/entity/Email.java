package com.rashmita.email_service.entity;

import com.rashmita.base_domains.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="email")
public class Email extends AbstractEntity {
    @Column(name="send_from",nullable = false)
    private String sendFrom;
    @Column(name="send_to",nullable = false)
    private String sendTo;
    private Date createdAt;
    private Date updatedAt;

}
