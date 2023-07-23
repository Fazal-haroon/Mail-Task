package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RECIPIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String subject;

    private String body;

    private boolean sent; // true if sent, false otherwise

    @Column(nullable = false)
    private LocalDateTime createdAt; // Time when the email was created
}