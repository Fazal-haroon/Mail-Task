package com.example.demo.config;

import com.example.demo.exception.CustomSMTPException;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0/5 * * * *") // Every 5 minutes
    public void resendFailedEmails() throws CustomSMTPException {
        emailService.sendEmails();
    }
}
