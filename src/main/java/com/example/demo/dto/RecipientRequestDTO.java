package com.example.demo.dto;

import com.example.demo.util.ListConverter;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RecipientRequestDTO {

    private Long id;

    private String subject;

    private String messageContent;

    private List<String> toEmail;

    private String recietpname;

    private boolean sent;

    private LocalDateTime createdAt;

}