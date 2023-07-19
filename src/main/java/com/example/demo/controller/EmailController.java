package com.example.demo.controller;

import com.example.demo.exception.CustomSMTPException;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public void sendInitialEmails() throws CustomSMTPException {
        emailService.sendEmails();
    }
}