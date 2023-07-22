package com.example.demo.config;

import com.example.demo.exception.CustomSMTPException;
import com.example.demo.service.EmailService;
import com.example.demo.util.EmailSenderUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Log4j2
public class EmailScheduler {

    @Autowired
    EmailService emailService;

    @Scheduled(cron = "0 0/2 * * * *") // Every 5 minutes
    public void scheduleResendFailedEmails() {
        log.info("<------scheduleResendFailedEmails() Method Called----->");
//        emailService.resendEmails();
    }
}
