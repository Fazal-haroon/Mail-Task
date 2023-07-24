package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RecipientResponseDTO {

    private Long id;

    private String subject;

    private String messageContent;

    private List<String> toEmailList;

    private String recietpname;

    private boolean sent;

    private LocalDateTime createdAt;
}
