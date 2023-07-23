package com.example.demo.controller;

import com.example.demo.dto.RecipientDTO;
import com.example.demo.exception.CustomSMTPException;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/create-recipient")
    public void addRecipient(@RequestBody RecipientDTO recipientDTO) {
        emailService.saveRecipient(recipientDTO);
    }

}