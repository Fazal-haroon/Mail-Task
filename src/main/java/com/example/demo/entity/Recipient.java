package com.example.demo.entity;

import com.example.demo.util.ListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;

    //save list data as string separated by comma(,) make DDL for this with mediumText not varchar
    @Column(name = "TO_Email")
    private String toEmail;

    /*
    //save list data as string separated by comma(,)
    private String CC;

    //save list data as string separated by comma(,)
    private String BCC;

    //save list data as string separated by comma(,)
    private String attach;

    //save list data as string separated by comma(,)
    private String filenames;

    //save list data as string separated by comma(,)
    private String embeddedImages;
    */

    @Column(name = "RECIETPNAME")
    private String recietpname;

    @Column(name = "SENT")
    private boolean sent; // true if sent, false otherwise

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt; // Time when the email was created
}